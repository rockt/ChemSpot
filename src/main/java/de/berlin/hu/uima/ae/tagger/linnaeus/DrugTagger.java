package de.berlin.hu.uima.ae.tagger.linnaeus;

import de.berlin.hu.util.Constants;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.u_compare.shared.semantic.chemical.Chemical;
import org.uimafit.util.JCasUtil;
import uk.ac.man.entitytagger.Mention;
import uk.ac.man.entitytagger.matching.matchers.AutomatonMatcher;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class DrugTagger extends JCasAnnotator_ImplBase {
	private static final String PATH_TO_DICTIONARY = "DrugBankMatcherDictionaryAutomat";
	private boolean matchExpansion = true;
	protected static final Pattern idPattern = Pattern.compile("(DB[0-9]+)");
    //list of invalid suffixes taken from Hettne et al. (2009)
	private Set<String> suffixes;
	private AutomatonMatcher matcher;

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
			
			File automatonFile = new File(aContext.getConfigParameterValue(PATH_TO_DICTIONARY).toString());				
			matcher = AutomatonMatcher.loadMatcher(automatonFile);
		} catch (FileNotFoundException e) {
			throw new ResourceInitializationException(e);
		} catch (IOException e) {
			throw new ResourceInitializationException(e);
		} 
	}


	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		String docText = aJCas.getDocumentText();
		List<Mention> matches = new ArrayList<Mention>();

		try {
			long start = System.currentTimeMillis();
			matches = matcher.match(docText);
			long time = System.currentTimeMillis() - start;
			//System.out.println("Tagging with LINNAEUS took " + time + "ms");
			System.out.println("Start post-processing...");
		} catch (Error e) {
			// TODO: what goes wrong here?
		} catch (Exception e) {
			// TODO: what goes wrong here?
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
			String id = mention.getIdsToString();

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
