package de.berlin.hu.uima.ae.tagger.simple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.u_compare.shared.semantic.chemical.Chemical;

import de.berlin.hu.util.Constants;

public class ChemicalFormulaTagger extends JCasAnnotator_ImplBase {
	private static final String ELEMENT_PATTERN = "(Ac|Ag|Al|Am|Ar|As|At|Au|B|Ba|Be|Bh|Bi|Bk|Br|Ca|Cd|Ce|Cf|Cl|Cm|Cn|Co|Cr|Cs|Cu|C|Db|Ds|Dy|Er|Es|Eu|" +
			"Fe|Fm|Fr|F|Ga|Gd|Ge|He|Hf|Hg|Ho|Hs|H|In|Ir|I|Kr|K|La|Li|Lr|Lu|Md|Me|Mg|Mn|Mo|Mt|Na|Nb|Nd|Ne|Ni|No|Np|N|Os|O|Pa|Pb|Pd|Ph|Pm|Po|Pr|" +
			"Pt|Pu|P|Ra|Rb|Re|Rf|Rg|Rh|Rn|Ru|Sb|Sc|Se|Sg|Si|Sm|Sn|Sr|S|Ta|Tb|Tc|Te|Th|Ti|Tl|Tm|U|V|W|Xe|Yb|Y|Zn|Zr)";
	private static final String MOLECULE_PATTERN = "(" + ELEMENT_PATTERN + "\\\\d?\\\\d?[\\\\+\\\\-]?)";
	private static final Pattern FORMULA_PATTERN = Pattern.compile("(?<=[^a-zA-Z0-9\\-\\+\\)])((\\d\\d?|\\(\\d\\d?\\))?(%s|\\((%s+|\\d?\\d?[\\+\\-]|\\d\\d?[\\+\\-]?)\\)[0-9a-z]?)+)(?=[^a-zA-Z0-9\\-\\+\\(])".replaceAll("%s", MOLECULE_PATTERN));
	private static final Pattern MUST_CONTAIN = Pattern.compile("\\p{Alpha}.*[0-9]|[0-9].*\\p{Alpha}");
	private static final Pattern DOES_NOT_MATCH = Pattern.compile("\\p{Alpha}{2}[0-9]|.*1[5-9].*|.*[2-9]\\d.*|.*(\\D|^)1(\\D|$).*|[A-Z][a-z]?([5-9]|\\d{2,}|(\\(([5-9]|\\d{2,})\\)))|\\d+[A-Z]\\[a-z]?");
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		String text = aJCas.getDocumentText();
		
		Matcher matcher = FORMULA_PATTERN.matcher(text);
		while (matcher.find()) {
			String formula = matcher.group();
			
			int begin = matcher.start();
			int end = matcher.end();
			
			if (formula.startsWith("(") && formula.endsWith(")")) {
				begin++;
				end--;
				formula = formula.substring(1, formula.length()-1);
			}
			
			if (formula.replaceAll("\\(|\\)", "").length() > 2 && (MUST_CONTAIN.matcher(formula).find()) && !DOES_NOT_MATCH.matcher(formula).matches()) {
				createFormulaAnnotation(aJCas, begin, end, formula);
			}
		}
	}
	
	private Chemical createFormulaAnnotation(JCas aJCas, int begin, int end, String id) {
		Chemical formula = new Chemical(aJCas);
		formula.setBegin(begin);
		formula.setEnd(end);
        formula.setId(id);
		formula.setSource(Constants.SUM_TAGGER);
		formula.addToIndexes();
		return formula;
	}
}
