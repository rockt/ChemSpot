package de.berlin.hu.uima.ae.filter;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.u_compare.shared.semantic.NamedEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class StopwordFilter extends JCasAnnotator_ImplBase {

	private int numberOfFilteredEntities = 0;
	private List<NamedEntity> invalidChemicals = null;	
	private HashSet<String> stopwords = new HashSet<String>();
	
	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		// TODO Auto-generated method stub
		super.initialize(aContext);
		
		File stopwordFile = new File("./resources/chemspot_stop_words.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(stopwordFile));
			String line = "";
			while(null != (line = reader.readLine()) ) {
			  stopwords.add(line);
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ResourceInitializationException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ResourceInitializationException(e);
		}
	}
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		FSIndex chemicalIndex = aJCas.getAnnotationIndex(NamedEntity.type);
		Iterator chemicalIterator = chemicalIndex.iterator();
		
		invalidChemicals = new ArrayList<NamedEntity>();
		while (chemicalIterator.hasNext()) {
			NamedEntity chemical = (NamedEntity) chemicalIterator.next();
			if (!"goldstandard".equals(chemical.getSource())) {
				if (stopwords.contains(chemical.getCoveredText())) {
					invalidChemicals.add(chemical);
					numberOfFilteredEntities++;
				}
			}
		}
		
		for (NamedEntity invalidChemical : invalidChemicals) {
			//System.out.println("filtered: " + invalidChemical.getCoveredText());			
			invalidChemical.removeFromIndexes();
		}		
		//System.out.println("StopwordFilter: " + numberOfFilteredEntities);
	}
}
