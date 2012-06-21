package de.berlin.hu.uima.ae.filter;

import de.berlin.hu.util.Constants;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.u_compare.shared.semantic.NamedEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SuffixFilter extends JCasAnnotator_ImplBase {

	private int numberOfFilteredEntities = 0;
	private List<NamedEntity> invalidChemicals = null;
	
	
	private String[] invalidSuffixes = {
			"ase",
//			"ine",
//			"ate",
//			"ide",
//			"tro",
//			"LDL",
//			"IC(",
//			"(2)",
//			"NCA",
//			"one",
//			"pha",
//			"lic",
//			"nes",
//			"NxE",
//			"HRH",
//			"HFR"
			};
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		FSIndex chemicalIndex = aJCas.getAnnotationIndex(NamedEntity.type);
		Iterator chemicalIterator = chemicalIndex.iterator();
		
		invalidChemicals = new ArrayList<NamedEntity>();
		while (chemicalIterator.hasNext()) {
			NamedEntity chemical = (NamedEntity) chemicalIterator.next();
			if (!Constants.GOLDSTANDARD.equals(chemical.getSource())) {
				if (isInvalid(chemical.getCoveredText())) {
					invalidChemicals.add(chemical);
					numberOfFilteredEntities++;
				}
			}
		}
		
		for (NamedEntity invalidChemical : invalidChemicals) {
			invalidChemical.removeFromIndexes();
		}
		
		System.out.println("SuffixFilter: " + numberOfFilteredEntities);
	}
	
	private boolean isInvalid(String entity) {
		if (entity.length() > 2) {
			String suffix = (String) entity.subSequence(entity.length() - 3, entity.length());
			if (isInvalidSuffix(suffix)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isInvalidSuffix(String suffix) {
		for (int i = 0; i < invalidSuffixes.length; i++) {
			if (invalidSuffixes[i].equals(suffix)) {
				return true;
			}
		}
		return false;
	}
}
