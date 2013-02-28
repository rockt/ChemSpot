package de.berlin.hu.uima.ae.feature;

import java.io.IOException;
import java.util.List;

import org.apache.uima.UIMAException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.uimafit.factory.JCasFactory;

import de.berlin.hu.chemspot.ChemSpot;
import de.berlin.hu.types.PubmedDocument;

public class FeatureGeneratorApp {
	private static ChemSpot chemspot = null;
	private static TypeSystemDescription typeSystem = null;
	
	public static List<FeatureToken> generateFeatureTokens(String text) {
		JCas jcas = null;
		try {
			jcas = JCasFactory.createJCas(typeSystem);
		} catch (UIMAException e) {
			e.printStackTrace();
		}
        jcas.setDocumentText(text);
        PubmedDocument pd = new PubmedDocument(jcas);
        pd.setBegin(0);
        pd.setEnd(text.length());
        pd.setPmid("");
        pd.addToIndexes(jcas);
        
        chemspot.tag(jcas);
        
        return chemspot.getFeatureTokenGenerator().getFeatureTokens(jcas);
	}
	
	public static void main(String[] args) throws InvalidXMLException, IOException {
		String pathToModelFile = "resources/banner/model.bin";
		String pathToSentenceFile = "resources/genia/SentDetectGenia.bin.gz";
		
		String pathToDictionaryFile = "../../ChemSpot/data/dict.zip";
	    String pathToIDsFile = "../../ChemSpot/data/ids.zip";
		
		chemspot = new ChemSpot(null, pathToDictionaryFile, null, pathToIDsFile);
		typeSystem = UIMAFramework.getXMLParser().parseTypeSystemDescription(new XMLInputSource(chemspot.getClass().getClassLoader().getResource("desc/TypeSystem.xml")));
        
		// String text = "We examined the effect of exogenous estradiol on the changes in serum steroid hormone levels. Induced by a nonlethal dose of Escherichia coli endotoxin in male rats and the deaths due to nonlethal and lethal doses of endotoxin.";
		//String text = "A serum of 18-bromo-12-butyl-11-chloro-4,8-diethyl-5-hydroxy-15-methoxy is great.";
		String text = "A serum of 18-bromo-12-butyl-11-bromo-4,8-diethyl-5-hydroxy is great for combination with aspirin and water.";
		
		List<FeatureToken> featureTokens = generateFeatureTokens(text);
        
        for (FeatureToken token : featureTokens) {
        	System.out.printf("%d-%d\t%s\t%s%n", token.getBegin(), token.getEnd(), token.getCoveredText(), token.getFeatures().toString());
        }
	}

}
