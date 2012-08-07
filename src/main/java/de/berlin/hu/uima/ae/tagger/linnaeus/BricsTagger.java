/*
 * Copyright (c) 2012. Humboldt-Universität zu Berlin, Dept. of Computer Science and Dept.
 * of Wissensmanagement in der Bioinformatik
 * -------------------------------
 *
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS COMMON PUBLIC
 * LICENSE ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM
 * CONSTITUTES RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.
 *
 * http://www.opensource.org/licenses/cpl1.0
 */

package de.berlin.hu.uima.ae.tagger.linnaeus;

import de.berlin.hu.chemspot.Mention;
import de.berlin.hu.util.Constants;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.u_compare.shared.semantic.Chemical;
import org.uimafit.util.JCasUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * User: Tim Rocktaeschel
 * Date: 7/2/12
 * Time: 2:11 PM
 */
public class BricsTagger extends JCasAnnotator_ImplBase {
	private static final String PATH_TO_DICTIONARY = "DrugBankMatcherDictionaryAutomat";
    //list of invalid suffixes taken from Hettne et al. (2009)
	private Set<String> suffixes; //FIXME: implement another AE for that
	private BricsMatcher matcher;

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		suffixes = new HashSet<String>();

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("resources/suffixes.txt")));
			String line = reader.readLine();
			while (line != null) {
				suffixes.add(line);
				line = reader.readLine();
			}
			matcher = new BricsMatcher(aContext.getConfigParameterValue(PATH_TO_DICTIONARY).toString());
		} catch (FileNotFoundException e) {
			throw new ResourceInitializationException(e);
		} catch (IOException e) {
			throw new ResourceInitializationException(e);
		} catch (ClassNotFoundException e) {
            throw new ResourceInitializationException(e);
        }
    }

/*    @Override
    public void process(JCas jCas) throws AnalysisEngineProcessException {
        String docText = jCas.getDocumentText();
        Collection<Mention> matches = matcher.match(docText);

        for (Mention match : matches) {
            Chemical annotation = new Chemical(jCas);
            annotation.setBegin(match.getStart());
            annotation.setEnd(match.getEnd());
            annotation.setSource(Constants.DICTIONARY);
            annotation.addToIndexes();
        }
    }   */


    @Override
   	public void process(JCas aJCas) throws AnalysisEngineProcessException {
   		String docText = aJCas.getDocumentText();
   		List<Mention> matches = new ArrayList<Mention>();

   		try {
   			long start = System.currentTimeMillis();
   			matches = (List<Mention>) matcher.match(docText);
   			long time = System.currentTimeMillis() - start;
   			//System.out.println("Tagging with LINNAEUS took " + time + "ms");
   			System.out.println("Start post-processing...");
   		} catch (Error e) {
   			throw new AnalysisEngineProcessException(e);
   		} catch (IllegalStateException e) {
               if (e.toString().contains("Automaton matched the empty string")); //FIXME: What goes wrong here?
               else throw new AnalysisEngineProcessException(e);
   		}

   		Comparator<Mention> comp = new Comparator<Mention>() {
   			public int compare(Mention m1, Mention m2) {
   				return m1.getStart() - m2.getStart();
   			}
   		};

   		//sort mentions by start position
   		Collections.sort(matches, comp);
   		Mention lastMention = null;
   		Chemical lastDrug = null;

   		for (Mention mention : matches) {
   			int begin = mention.getStart();
   			int end = mention.getEnd();
   			//String id = mention.getIdsToString();
   			String id = "";

   			//filter mentions
   			if (!filter(mention)) {
   				//only keep mention if it is not included in the previous one
   				if (!overlaps(lastMention, mention)) {
   					if (overlaps(mention, lastMention)) {
   						lastDrug.removeFromIndexes();
   						lastMention = null;
   					}
   					lastDrug = processMention(aJCas, docText, lastDrug, begin, end, id);
   				}
   				lastMention = mention;
   			}
   		}


   		//test whether overlaps were resolved correctly!
   		Comparator<Chemical> comp2 = new Comparator<Chemical>() {
   			public int compare(Chemical m1, Chemical m2) {
   				return m1.getBegin() - m2.getBegin();
   			}
   		};

   		List<Chemical> entities = new ArrayList<Chemical>(JCasUtil.select(aJCas, Chemical.class));
   		Collections.sort(entities, comp2);

   		List<Chemical> chemicalsToRemove = new ArrayList<Chemical>();

   		Chemical lastChemical = null;
   		for (Chemical chemical : entities) {
   			if (Constants.DICTIONARY.equals(chemical.getSource())) {
   				//if they cross
   				if (lastChemical != null && (
   						lastChemical.getBegin() <= chemical.getBegin() && chemical.getEnd() <= lastChemical.getEnd()
   						|| lastChemical.getBegin() <= chemical.getBegin() && chemical.getBegin() <= lastChemical.getEnd()
   						)) {
   					//keep the longer one
   					if (lastChemical.getCoveredText().length() > chemical.getCoveredText().length()) {
   						chemicalsToRemove.add(chemical);
   					} else {
   						chemicalsToRemove.add(lastChemical);
   					}
   					//throw new IllegalStateException(lastChemical.getCoveredText() + " overlaps " + chemical.getCoveredText());
   				}
   				lastChemical = chemical;
   			}
   		}

   		for (Chemical chemical : chemicalsToRemove) {
   			chemical.removeFromIndexes(aJCas);
   		}
   	}

       //FIXME: implement match expansion and boundary correction in a separate AE
   	private Chemical processMention(JCas aJCas, String docText, Chemical lastDrug,
   			int begin, int end, String id) {
   		int originalBegin = begin;
   		int originalEnd = end;
        boolean matchExpansion = true;
   		//expand mentions (by simulating a coarse tokenizer)
   		if (matchExpansion) {
   			begin = findLeftBorder(docText, begin);
   			end = findRightBorder(docText, end);
   		}

   		//remove erroneous last character
   		if ((docText.charAt(end-1)+"").matches("[.,;:]")) {
   			end--;
   		}

   		if (docText.charAt(end-1) == '(') {
   			end--;
   		}
   		if (docText.charAt(end-1) == '[') {
   			end--;
   		}

   		if (docText.charAt(begin) == ')') {
   			begin++;
   		}
   		if (docText.charAt(begin) == ']') {
   			begin++;
   		}

   		if (docText.charAt(begin) == '(' && docText.charAt(end-1) == ')') {
   			begin++;
   			end--;
   		}

   		if (docText.charAt(begin) == '[' && docText.charAt(end-1) == ']') {
   			begin++;
   			end--;
   		}

   		int stack = 0;
   		for (int i = begin; i < end; i++) {
   			char c = docText.charAt(i);
   			if (c == '(') {
   				stack++;
   			} else if (c == ')') {
   				stack--;
   			}
   		}

   		if (stack > 0 && docText.charAt(begin) == '(') {
   			begin++;
   		}

   		if (stack < 0 && docText.charAt(end-1) == ')') {
   			end--;
   		}

   		int stack2 = 0;
   		for (int i = begin; i < end; i++) {
   			char c = docText.charAt(i);
   			if (c == '[') {
   				stack2++;
   			} else if (c == ']') {
   				stack2--;
   			}
   		}

   		if (stack2 > 0 && docText.charAt(begin) == '[') {
   			begin++;
   		}

   		if (stack2 < 0 && docText.charAt(end-1) == ']') {
   			end--;
   		}

   		String mentionText = docText.substring(begin, end);
   		for (String suffix : suffixes) {
   			if (mentionText.endsWith(suffix)) {
   				end = end - suffix.length();
   				break;
   			}
   		}

   		boolean borderHasChanged = (originalBegin != begin) || (originalEnd != end);

   		//create new annotation
   		if (lastDrug == null || lastDrug.getBegin() != begin) {
   			lastDrug = createDrugAnnotation(aJCas, begin, end, id, borderHasChanged);
   		}
   		return lastDrug;
   	}


   	private Chemical createDrugAnnotation(JCas aJCas, int begin, int end, String id, boolean borderHasChanged) {
   		Chemical drug = new Chemical(aJCas);
   		drug.setBegin(begin);
   		drug.setEnd(end);
           //only keep ChemIDplus ID if the entity matched exactly
   		if (borderHasChanged) {
   			drug.setId("");
   		} else {
   			id = id.replaceAll("CHID_[0]*", "");
   			drug.setId(id);
   		}
   		drug.setSource(Constants.DICTIONARY);
   		drug.addToIndexes();
   		return drug;
   	}


   	private int findRightBorder(String docText, int end) {
   		for (int i = end; i < docText.length(); i++) {
   			if (docText.charAt(i) == ' '
   					|| docText.charAt(i) == '\n'
   					|| docText.charAt(i) == '\r'
   					|| docText.charAt(i) == '\t') {
   				return i;
   			}
   		}
   		return docText.length();
   	}

   	private int findLeftBorder(String docText, int begin) {
   		for (int i = begin; i > 0; i--) {
   			if (docText.charAt(i) == ' '
   					|| docText.charAt(i) == '\n'
   					|| docText.charAt(i) == '\r'
   					|| docText.charAt(i) == '\t') {
   				return i+1;
   			}
   		}
   		return 0;
   	}


   	private boolean filter(Mention mention) {
   		//forget about ambiguous one or two letter entities
   		if (mention.getEnd() - mention.getStart() < 3) {
   			return true;
   		}

   		//test if it is a real number only
   		if (mention.getText().matches("[-0-9]+[.,]+[0-9.,]+|[0-9,]+")) {
   			return true;
   		}
   		return false;
   	}

   	private boolean overlaps(Mention lastMention, Mention mention) {
   		if (lastMention == null || mention == null) {
   			return false;
   		}

   		if (lastMention.getStart() <= mention.getStart() && mention.getEnd() <= lastMention.getEnd()) {
   			return true;
   		} else {
   			return false;
   		}
   	}
}
