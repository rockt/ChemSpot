package de.berlin.hu.uima.ae.tagger.drug;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.u_compare.shared.semantic.Chemical;
import org.u_compare.shared.syntactic.Sentence;
import org.uimafit.util.JCasUtil;

import scala.collection.Iterator;

import de.berlin.hu.eumed.ner.DrugTagger;
import de.berlin.hu.eumed.ner.Mention;
import de.berlin.hu.util.Constants;

public class DrugNERTagger extends JCasAnnotator_ImplBase {
	public static final String PATH_TO_DRUG_MODEL = "pathToDrugModel";
	
	private DrugTagger tagger = null;
	
	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		String drugModel = (String)aContext.getConfigParameterValue(PATH_TO_DRUG_MODEL);
		tagger = new DrugTagger("resources/genia/Tagger_Genia.bin", "resources/genia/SentDetectGenia.bin", drugModel);
		tagger.initialize();
	}
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		Iterable<Sentence> sentenceIterator = JCasUtil.iterate(aJCas, Sentence.class);
		
		for (Sentence sentence : sentenceIterator) {
	        Iterator<Mention> iter = tagger.tag(sentence.getCoveredText()).toIterator();
	        while (iter.hasNext()) {
	            Mention mention = iter.next();
	            
	            /*System.out.println(mention.toString());
	            System.out.println(mention.text() + "");
	            System.out.println(sentence.getBegin() + mention.start());
	            System.out.println(sentence.getBegin() + mention.end());
	            System.out.println(mention.typ());*/

	            //if ("Brand".equalsIgnoreCase(mention.typ()))
	            createDrugAnnotation(aJCas, sentence.getBegin() + mention.start(), sentence.getBegin() + mention.end());
	            //System.out.println();
	        }
		}
	}
	 
	
	private Chemical createDrugAnnotation(JCas aJCas, int begin, int end) {
   		Chemical drug = new Chemical(aJCas);
   		drug.setBegin(begin);
   		drug.setEnd(end+1);
   		drug.setSource(Constants.DRUG);
   		drug.addToIndexes();
   		//System.out.println("'" + drug.getCoveredText() + "'");
   		
   		return drug;
   	}
}
