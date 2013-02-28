package de.berlin.hu.uima.ae.tagger.banner;

import banner.tagging.CRFTagger;
import banner.types.Mention;
import de.berlin.hu.uima.util.Util;
import de.berlin.hu.util.Constants;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.u_compare.shared.semantic.NamedEntity;
import org.u_compare.shared.syntactic.Sentence;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Tim Rocktäschel
 *	
 *	This is an UIMA Analysis Engine for tagging with BANNER. 
 *  It loads a BANNER model file and tags tokenized sentences in the text of a CAS object.
 */

public class BannerTagger extends JCasAnnotator_ImplBase {

	private static final String BANNER_MODEL_FILE_PARAM = "BannerModelFile";
	private static final String BANNER_CONFIG_FILE_PARAM = "BannerConfigFile";
	private static final String THRESHOLD_PARAM = "Threshold";

	//FIXME: remove hardcoded switch to choose between loading model file from external resource or parameter
//	private static final boolean USE_RESOURCE = true; 
	private static final boolean USE_RESOURCE = false; 

	private CRFTagger tagger;
//	private CRFTaggerStochasticGradient tagger;

	private URL bannerModelFile;
	private File bannerConfigFile;

	// Confidence-Threshold for N-Best-Tagger
	private double threshold;
    // N parameter for N-Best-Tagger
	private static final int N = 10;


	private int documentCounter;
	private XMLConfiguration config;


    @Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		try {
            String pathToModelFile = aContext.getConfigParameterValue(BANNER_MODEL_FILE_PARAM).toString();
            String pathToConfigFile = aContext.getConfigParameterValue(BANNER_CONFIG_FILE_PARAM).toString();
            bannerModelFile = new URL(pathToModelFile);
            bannerConfigFile = new File(pathToConfigFile);
			try {
				config = new XMLConfiguration(bannerConfigFile);
			} catch (ConfigurationException e) {
				e.printStackTrace();
				throw new ResourceInitializationException(e);
			}			
			threshold = Double.parseDouble(aContext.getConfigParameterValue(THRESHOLD_PARAM).toString());
			tagger = CRFWrapper.load(bannerModelFile, null, null, null);
//			tagger = NBestCRFTagger.load(bannerModelFile, lemmatiser, posTagger, null, N, true);
//			tagger = NBestCRFTagger.load(bannerModelFile, LEMMATISER, POS_TAGGER, null, N, false); //then the sum is used for the same sequences
		} catch (IOException e) {
			throw new ResourceInitializationException(e);
		}
        documentCounter = 0;
	}

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		FSIndex<Annotation> sentenceIndex = aJCas.getAnnotationIndex(Sentence.type);
		Iterator<Annotation> sentenceIterator = sentenceIndex.iterator();
		int sentenceCounter = 0;
        Set<Mention> mentions = new HashSet<Mention>();
        String docText = aJCas.getDocumentText();

		long start = System.currentTimeMillis();
		while (sentenceIterator.hasNext()) {
			Sentence sentence = (Sentence) sentenceIterator.next();
			// convert every sentence into a BANNER sentence
			banner.types.Sentence bannerSentence = new banner.types.Sentence(sentenceCounter+"", documentCounter+"", sentence.getCoveredText());
			// get tokens covered by the sentence
			List<org.u_compare.shared.syntactic.Token> tokensInSentence = Util.getTokens(aJCas, sentence);
			Util.tokenizeBannerSentence(bannerSentence, tokensInSentence);
			
			assert sentence.getCoveredText().equals(bannerSentence.getText());
			assert tokensInSentence.size() == bannerSentence.getTokens().size() : (tokensInSentence.size() + " != " + bannerSentence.getTokens().size());							
			
			try {
				tagger.tag(bannerSentence);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println("ERROR!");
				System.err.println("Corpus:\t" + sentence.getCoveredText());
				System.err.println("BANNER:\t" + bannerSentence.getText());
			}

			// annotate found entities
            mentions.addAll(createAnnotations(aJCas, sentence.getBegin(), bannerSentence));
			sentenceCounter++;
		}

/*
        //propagate all findings...
        for (Mention mention : mentions) {
            String pattern = mention.getText();
            int index = docText.indexOf(pattern);
            while (index >= 0) {
                int beginning = index;
                int ending = beginning + pattern.length();
                if (beginning != mention.getStart()) {
                    NamedEntity entity = new NamedEntity(aJCas);
                    entity.setBegin(beginning);
                    entity.setEnd(ending);
                 	entity.setEntityType(mention.getEntityType().getText());
                 	entity.setConfidence(mention.getProbability());
                 	entity.setSource(Constants.CRF);
                 	entity.addToIndexes();
                    System.out.println(mention.getText() + " != " + entity.getCoveredText());
                    //TODO: check position
                }
                index = docText.indexOf(pattern, index + 1);
            }
        }
*/

		long time = System.currentTimeMillis() - start;
		//System.out.println("Tagging " + sentenceCounter + " sentences with CRF took " + time + "ms (" + (time/sentenceCounter) + "ms per sentence)");
		documentCounter++;
	}

	/**
	 * converts each mention into an UIMA annotation
	 */
	private Set<Mention> createAnnotations(JCas aJCas, int offset, banner.types.Sentence bannerSentence) {
        List<Mention> mentions = bannerSentence.getMentions();
		Set<Mention> mentionsToAdd = new HashSet<Mention>();		
		//FIXME: when using NBestCRFTagger: sort mentions first!
		Mention lastMention = null;
		for (Mention mention : mentions) {
			if (mention.getProbability() >= threshold) {
				// simple approach to resolve overlapping entities when using NBestCRFTagger
				if (lastMention != null && mention.overlaps(lastMention)) {
					if (mention.getProbability() > lastMention.getProbability()) {
						mentionsToAdd.remove(lastMention);
						mentionsToAdd.add(mention);
						lastMention = mention;
					}
				} else {
					mentionsToAdd.add(mention);
					lastMention = mention;
				}
			}
		}
		
		for (Mention mention : mentionsToAdd) {
			NamedEntity entity = new NamedEntity(aJCas);
			int startOffset = 0;
			int endOffset = 0;
			startOffset = bannerSentence.getTokens().get(mention.getStart()).getStart();
			endOffset = bannerSentence.getTokens().get(mention.getEnd() - 1).getEnd();

			entity.setBegin(offset + startOffset);
			entity.setEnd(offset + endOffset);	
			entity.setEntityType(mention.getEntityType().getText());
			entity.setConfidence(mention.getProbability());
			entity.setSource(Constants.CRF);
			entity.addToIndexes();
			
			assert entity.getCoveredText().equals(mention.getText());
		}
        return mentionsToAdd;
	}
}