package de.berlin.hu.uima.cc.banner.trainer;

import banner.tagging.CRFTagger;
import banner.tagging.FeatureSet;
import banner.tagging.TagFormat;
import banner.types.EntityType;
import banner.types.Mention;
import banner.types.Mention.MentionType;
import banner.types.Sentence.OverlapOption;
import de.berlin.hu.banner.featuresets.KlingerLikeFeatureSet;
import de.berlin.hu.banner.util.ConfigUtil;
import de.berlin.hu.uima.util.Util;
import dragon.nlp.tool.Tagger;
import dragon.nlp.tool.lemmatiser.EngLemmatiser;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.u_compare.shared.semantic.NamedEntity;
import org.u_compare.shared.syntactic.Sentence;
import org.uimafit.util.JCasUtil;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Tim Rocktäschel
 *	
 *	This is an UIMA CAS Consumer for training BANNER. 
 *  It expects CAS objects containing documents with tokens, sentences and mentions.
 *  After all CAS objects are processed, it trains a CRF and writes the model to disk.
 */
public class BannerTrainer extends CasConsumer_ImplBase{
	private static final String BANNER_MODEL_OUTPUT_FILE_PARAM = "BannerModelOutputFile";
	private static final String BANNER_CONFIG_FILE_PARAM = "BannerConfigFile";
	
	private File bannerModelOutputFile;
	private File bannerConfigFile;
	
	private HierarchicalConfiguration config; 
	private FeatureSet featureSet;
	private TagFormat tagFormat;
	
	private Set<banner.types.Sentence> bannerSentences;
	
	private int documentCounter;
	private int numberOfEntities;

	private int crfOrder;
	
	@Override
	public void initialize() throws ResourceInitializationException {
		super.initialize();
		bannerSentences = new HashSet<banner.types.Sentence>();
		//get a handle to the file where the model should be stored
		bannerModelOutputFile = new File(getConfigParameterValue(
				BANNER_MODEL_OUTPUT_FILE_PARAM).toString());
		bannerConfigFile = new File(getConfigParameterValue(
				BANNER_CONFIG_FILE_PARAM).toString());
		
		try {
			config = new XMLConfiguration(bannerConfigFile);
		} catch (ConfigurationException e) {
			e.printStackTrace();
			throw new ResourceInitializationException(e);
		}
		
		tagFormat = ConfigUtil.getTagFormat(config);
		EngLemmatiser lemmatiser = ConfigUtil.getLemmatiser(config);
		Tagger posTagger = ConfigUtil.getPosTagger(config);
		Set<MentionType> mentionTypes = ConfigUtil.getMentionTypes(config);
		OverlapOption sameTypeOverlapOption = ConfigUtil.getSameTypeOverlapOption(config);
		OverlapOption differentTypeOverlapOption = ConfigUtil.getDifferentTypeOverlapOption(config);
		crfOrder = ConfigUtil.getCRFOrder(config);

		// Klinger et al. (2008) like feature set
		featureSet = new KlingerLikeFeatureSet(tagFormat, lemmatiser, posTagger, null, mentionTypes, sameTypeOverlapOption, differentTypeOverlapOption);

		documentCounter = 0;
		numberOfEntities = 0;
	}
	
	public void processCas(CAS aCas) throws ResourceProcessException {
		JCas aJCas = null;
		try {
			aJCas = aCas.getJCas();
		} catch (CASException e) {
			throw new ResourceProcessException(e);
		}

		Iterator<Sentence> sentenceIterator = JCasUtil.iterator(aJCas, Sentence.class);
		
		int sentenceCounter = 0;
		while (sentenceIterator.hasNext()) {
			Sentence sentence = (Sentence) sentenceIterator.next();
			
			//convert every sentence to a training example for BANNER
			banner.types.Sentence bannerSentence = new banner.types.Sentence(sentenceCounter+"", documentCounter+"", sentence.getCoveredText());
			
			//pointer to the start and end of the sentence within the document
		    int sentenceBegin = sentence.getBegin();
		    int sentenceEnd = sentence.getEnd();
		    
		    //get all tokens that cover the current sentence
		    List<org.u_compare.shared.syntactic.Token> tokensInSentence = Util.getTokens(aJCas, sentenceBegin, sentenceEnd);
		    Util.tokenizeBannerSentence(bannerSentence, tokensInSentence);
		   
		    assert tokensInSentence.size() == bannerSentence.getTokens().size();
		    
		    Iterator<NamedEntity> entityIterator = JCasUtil.iterator(sentence, NamedEntity.class, true, true);
		    
			NamedEntity lastEntity = null;
			
			//convert every entity into a BANNER mention
			while (entityIterator.hasNext()) {
				NamedEntity currentEntity = (NamedEntity) entityIterator.next();

				if (!overlaps(lastEntity, currentEntity)) {
					int currentEntityBegin = currentEntity.getBegin();
					int currentEntityEnd = currentEntity.getEnd();

					//check whether the current entity is within the sentence
					if (currentEntityBegin < sentenceEnd && currentEntityEnd <= sentenceEnd) {
						//get the position within the sentence
						int tokenPositionBegin = getTokenPositionBegin(currentEntityBegin, tokensInSentence);
						int tokenPositionEnd = getTokenPositionEnd(currentEntityEnd, tokensInSentence);
				
						//add every mention to the training sentence
						Mention mention = new Mention(bannerSentence, tokenPositionBegin, tokenPositionEnd + 1, EntityType.getType(currentEntity.getEntityType()), MentionType.Required);
//						mention.setProbability(1.0);
						
						bannerSentence.addMention(mention);
						numberOfEntities++;
					} else {
						break;
					}
				} else {
					System.out.println("Probable annotation error: " +  lastEntity.getCoveredText() + " overlaps " + currentEntity.getCoveredText());
				}
				lastEntity = currentEntity;
			}
			
			//add to training examples
		    bannerSentences.add(bannerSentence);
		    sentenceCounter++;
		}
		documentCounter++;
	}
	
	private boolean overlaps(NamedEntity lastEntity,
			NamedEntity currentEntity) {
		if (lastEntity != null) {
			if (currentEntity.getBegin() >= lastEntity.getBegin()
					&& currentEntity.getEnd() <= lastEntity.getEnd()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the index of the token denoting the begin of the entity
	 */
	private int getTokenPositionBegin(int currentEntityBegin,
			List<org.u_compare.shared.syntactic.Token> tokensInSentence) {
		for (int i = 0; i < tokensInSentence.size(); i++) {
			org.u_compare.shared.syntactic.Token token = tokensInSentence.get(i);
			if (token.getBegin() <= currentEntityBegin && currentEntityBegin < token.getEnd()) {
				return i;
			}
		}
		
		System.out.println(currentEntityBegin);
		
		for (org.u_compare.shared.syntactic.Token token : tokensInSentence) {
			System.out.println(token.getBegin() + "\t" + token.getEnd());
		}
		
		throw new IllegalArgumentException();
	}
	
	/**
	 * @return the index of the token denoting the end of the entity
	 */
	private int getTokenPositionEnd(int currentNamedEnd,
			List<org.u_compare.shared.syntactic.Token> tokensInSentence) {
		for (int i = 0; i < tokensInSentence.size(); i++) {
			org.u_compare.shared.syntactic.Token token = tokensInSentence.get(i);
			if (token.getBegin() < currentNamedEnd && currentNamedEnd <= token.getEnd()) {
				return i;
			}
		}
		throw new IllegalArgumentException();
	}

	//this method is invoked when all CAS objects passed through
	//FIXME: not true! use SourceDocumentAnnotation instead of this method
	@Override
	public void destroy() {
		System.out.println("Number of training sentences: " + bannerSentences.size());
		System.out.println("Number of entities: " + numberOfEntities);
        System.out.println("Training data loaded, starting training");
//        CRFTaggerStochasticGradient tagger;
        CRFTagger tagger;
		try {
//			tagger = CRFTaggerStochasticGradient.train(bannerSentences, crfOrder, tagFormat, featureSet);
			tagger = CRFTagger.train(bannerSentences, crfOrder, tagFormat, featureSet);
			System.out.println("Training complete, saving model");
			tagger.describe("model_describe.txt");
			tagger.write(bannerModelOutputFile);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
}