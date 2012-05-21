package de.berlin.hu.uima.ae.filter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.u_compare.shared.semantic.NamedEntity;

import de.berlin.hu.util.ExtractAbbrev;


public class AbbreviationFilter extends JCasAnnotator_ImplBase {
	private ExtractAbbrev abbreviations = new ExtractAbbrev();
	private int numberOfFilteredEntities = 0;
	private List<NamedEntity> invalidChemicals = null;
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		FSIndex chemicalIndex = aJCas.getAnnotationIndex(NamedEntity.type);
		Iterator chemicalIterator = chemicalIndex.iterator();
		
		invalidChemicals = new ArrayList<NamedEntity>();
		while (chemicalIterator.hasNext()) {
			NamedEntity chemical = (NamedEntity) chemicalIterator.next();
			if (isInvalid(chemical.getCoveredText())) {
				invalidChemicals.add(chemical);
				numberOfFilteredEntities++;
			}
		}
		
		for (NamedEntity invalidChemical : invalidChemicals) {
			invalidChemical.removeFromIndexes();
		}
		
		System.out.println("AbbreviationFilter: " + numberOfFilteredEntities);
	}
	
	/**
	 * Checks whether an entity is an invalid abbreviation.
	 * @param entity String representation of the entity to be checked.
	 * @return An entity is considered invalid if it is an abbreviation and contains a digit or an opening bracket but none that closes.
	 *			However an abbreviation will not be filtered if it has been defined in the corpus.
	 */
	private boolean isInvalid(String entity) {
		if (isAbbrevation(entity) && (containsDigit(entity) || containsInvalidBracket(entity)) && !abbreviations.isValidAbbreviation(entity)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @return Checks whether the entity contains an opening bracket but none that closes.
	 */
	private boolean containsInvalidBracket(String entity) {
		boolean containsInvalidBracket = false;
		for (char character : entity.toCharArray()) {
			if (character == '(') {
				containsInvalidBracket = true;
			} else if (containsInvalidBracket && character == ')') {
				containsInvalidBracket = false;
			}
		}
		return containsInvalidBracket;
	}

	/**
	 * @return Checks if the entity contains a digit.
	 */
	private boolean containsDigit(String entity) {
		boolean containsDigit = false;
		for (char character : entity.toCharArray()) {
			if (Character.isDigit(character)) {
				containsDigit = true;
			}
		}
		return containsDigit;
	}

	/**
	 * @return An entity is considered abbreviation-like if it consists of maximal five characters and has at least two capital letters in it.
	 */
	private boolean isAbbrevation(String entity) {
		if (entity.length() <= 5) {
			int numberOfCapitalLetters = 0;
			for (char character : entity.toCharArray()) {
				if (Character.isUpperCase(character)) {
					numberOfCapitalLetters++;
				}
			}
			if (numberOfCapitalLetters >= 2) {
				return true;
			}
		}
		return false;
	}
}
