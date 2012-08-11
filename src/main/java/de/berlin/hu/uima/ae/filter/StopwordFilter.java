package de.berlin.hu.uima.ae.filter;

import de.berlin.hu.util.Constants;
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

/**
 * Filters annotations according to a stopword list generated from the Google-N-Gram corpus.
 */
public class StopwordFilter extends JCasAnnotator_ImplBase {
	private List<NamedEntity> invalidChemicals = null;	
	private HashSet<String> stopwords = new HashSet<String>();

    @Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
        InputStream stopwordFile = this.getClass().getClassLoader().getResourceAsStream("resources/chemspot_stop_words.txt");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stopwordFile));
			String line = "";
			while(null != (line = reader.readLine()) ) {
			  stopwords.add(line);
		    }
		} catch (FileNotFoundException e) {
			throw new ResourceInitializationException(e);
		} catch (IOException e) {
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
			if (!Constants.GOLDSTANDARD.equals(chemical.getSource())) {
				if (stopwords.contains(chemical.getCoveredText())) {
					invalidChemicals.add(chemical);
				}
			}
		}
		
		for (NamedEntity invalidChemical : invalidChemicals) {
			invalidChemical.removeFromIndexes();
		}
	}
}
