package de.berlin.hu.uima.ae.feature;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private static Map<Long, JCas> jCases = new HashMap<Long, JCas>();
	
	public static void initialize(String pathToDictionaryFile, String pathToIDsFile) {
		chemspot = new ChemSpot(null, pathToDictionaryFile, null, pathToIDsFile);
		try {
			typeSystem = UIMAFramework.getXMLParser().parseTypeSystemDescription(new XMLInputSource(chemspot.getClass().getClassLoader().getResource("desc/TypeSystem.xml")));
		} catch (InvalidXMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<FeatureToken> generateFeatureTokens(String text) {
		// get JCas object for currently executed thread
    	long threadId = Thread.currentThread().getId();
    	
    	// create new jcas if necessary (i.e. a thread calls this method for the first time)
    	if (!jCases.containsKey(threadId)) {
    		synchronized (jCases) {
    			try {
					jCases.put(threadId, JCasFactory.createJCas(typeSystem));
				} catch (UIMAException e) {
					throw new RuntimeException(e);
				}
    		}
    	}
    	// get jcas
    	JCas jcas = jCases.get(threadId);
    	jcas.reset(); 		
		
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
		String pathToDictionaryFile = "../../ChemSpot/data/dict.zip";
	    String pathToIDsFile = "../../ChemSpot/data/ids.zip";
	    
	    // String text = "We examined the effect of exogenous estradiol on the changes in serum steroid hormone levels. Induced by a nonlethal dose of Escherichia coli endotoxin in male rats and the deaths due to nonlethal and lethal doses of endotoxin.";
	 	// String text = "A serum of 18-bromo-12-butyl-11-chloro-4,8-diethyl-5-hydroxy-15-methoxy is great.";
	 	String text = "A serum of 18-bromo-12-butyl-11-bromo-4,8-diethyl-5-hydroxy is great for combination with aspirin and water.";
		
	    initialize(pathToDictionaryFile, pathToIDsFile);
		
		List<FeatureToken> featureTokens = generateFeatureTokens(text);
        
        for (FeatureToken token : featureTokens) {
        	System.out.printf("%d-%d\t%s\t%s%n", token.getBegin(), token.getEnd(), token.getCoveredText(), token.getFeatures().toString());
        }
	}

}
