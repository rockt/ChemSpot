package de.berlin.hu.uima.ae.filter;

import de.berlin.hu.util.Constants;
import opennlp.uima.Token;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.u_compare.shared.semantic.NamedEntity;

import java.util.*;


public class PosFilter  extends JCasAnnotator_ImplBase {
	
	private String[] posTags;
	
	private final String[] invalidPOSTags = {
			"FW",
			"LS"
			};
	
	private int numberOfFilteredEntities = 0;
	private List<NamedEntity> invalidChemicals = null;
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		FSIndex chemicalIndex = aJCas.getAnnotationIndex(NamedEntity.type);
		FSIndex tokenIndex = aJCas.getAnnotationIndex(Token.type);

		Iterator chemicalIterator = chemicalIndex.iterator();
		Iterator tokenIterator = tokenIndex.iterator();

		posTags = new String[aJCas.getDocumentText().length()];

		while (tokenIterator.hasNext()) {
			Token token = (Token) tokenIterator.next();

			int begin = token.getBegin();
			int end = token.getEnd();
			String posTag = token.getPos();

			for (int i = begin; i < end; i++) {
				posTags[i] = posTag;
			}
		}
		
		invalidChemicals = new ArrayList<NamedEntity>();
		while (chemicalIterator.hasNext()) {
			NamedEntity chemical = (NamedEntity) chemicalIterator.next();

			int begin = chemical.getBegin();
			int end = chemical.getEnd();
			
			
			if (!Constants.GOLDSTANDARD.equals(chemical.getSource())) {
				if (isInvalid(begin, end)) {
					//System.out.println("Chemical filtered: " + chemical.getCoveredText() + "[" + getTag(begin, end) + "]");
					//can't remove from index while iterating
					invalidChemicals.add(chemical);
					numberOfFilteredEntities++;
				}
			}
		}
		
		for (NamedEntity invalidChemical : invalidChemicals) {
			invalidChemical.removeFromIndexes();
			
		}
		
		System.out.println("PosFilter: " + numberOfFilteredEntities);
	}

	
	
	public String getTag(int begin, int end) {
		Map<String, Integer> listOfTags = new HashMap<String, Integer>();
		
		for (int i = begin; i < end; i++) {
				String posTag = posTags[i];
				if (listOfTags.containsKey(posTag)) {
					listOfTags.put(posTag, listOfTags.get(posTag) + 1);
				} else {
					listOfTags.put(posTag, 1);
				}
		}
		
		List<Map.Entry> list = new ArrayList<Map.Entry>(listOfTags.entrySet());
		Collections.sort(list, new Comparator<Map.Entry>() {
			public int compare(Map.Entry e1, Map.Entry e2) {
				Integer i1 = (Integer) e1.getValue();
				Integer i2 = (Integer) e2.getValue();
				return i2.compareTo(i1);
			}
		});
		
		String tag = (String) list.get(0).getKey();
		return tag;
	}
	
	
	
	public boolean isInvalid(int begin, int end) {
		String posTag = getTag(begin, end);
		
//		if (posTag == null) {
//			System.out.println("No pos-tag availabe."); //TODO: find bug
//			return true;
//		}
		
		for (int i = 0; i < invalidPOSTags.length; i++) {
			if (invalidPOSTags[i].equals(posTag)) {
				return true;
			}
		}
		return false;
	}
}
