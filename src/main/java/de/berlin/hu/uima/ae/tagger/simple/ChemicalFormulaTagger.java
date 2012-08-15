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
			"Fe|Fm|Fr|F|Ga|Gd|Ge|H|He|Hf|Hg|Ho|Hs|In|Ir|I|Kr|K|La|Li|Lr|Lu|Md|Me|Mg|Mn|Mo|Mt|Na|Nb|Nd|Ne|Ni|No|Np|N|Os|O|Pa|Pb|Pd|Pm|Po|Pr|P|" +
			"Pt|Pu|Ra|Rb|Re|Rf|Rg|Rh|Rn|Ru|Sb|Sc|Se|Sg|Si|Sm|Sn|Sr|S|Ta|Tb|Tc|Te|Th|Ti|Tl|Tm|U|V|W|Xe|Yb|Y|Zn|Zr)";
	private static final String MOLECULE_PATTERN = "(" + ELEMENT_PATTERN + "\\\\d?\\\\d?[\\\\+\\\\-]?)";
	private static final Pattern FORMULA_PATTERN = Pattern.compile("(\\d\\d?|\\(\\d\\d?\\))?(%s|\\((%s+|\\d?\\d?[\\+\\-]|\\d\\d?[\\+\\-]?)\\)[0-9a-z]?)+".replaceAll("%s", MOLECULE_PATTERN));
	private static final Pattern MUST_CONTAIN = Pattern.compile("\\p{Alpha}.*[0-9]|[0-9].*\\p{Alpha}");
	private static final Pattern DOES_NOT_MATCH = Pattern.compile("\\p{Alpha}{2}[0-9]|.*[2-9]\\d.*");
	//private static final Pattern FORMULA_PATTERN = Pattern.compile("\\d?(%s\\d?|\\((%s\\d?)+\\)[0-9a-z]?)*(%s\\d|\\((%s\\d?)*(%s\\d)+(%s\\d?)*\\)[0-9a-z]?)(%s|\\(%s+\\)[0-9a-z]?)+".replaceAll("%s", ELEMENT_PATTERN));
	//private static final Pattern FORMULA_PATTERN = Pattern.compile("\\S*[\\(\\)A-Z]+[0-9]([A-Z][A-Z]?[0-9]?[\\(\\)]?)+\\S*", Pattern.CASE_INSENSITIVE);
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		String text = aJCas.getDocumentText();
		
		Matcher matcher = FORMULA_PATTERN.matcher(text);
		while (matcher.find()) {
			String match = matcher.group();
			if (match.replaceAll("\\(|\\)", "").length() > 2 && MUST_CONTAIN.matcher(match).find() && !DOES_NOT_MATCH.matcher(match).matches()) {
				int begin = matcher.start();
				int end = matcher.end();
				createFormulaAnnotation(aJCas, begin, end, match);
			}
		}
	}
	
	private Chemical createFormulaAnnotation(JCas aJCas, int begin, int end, String id) {
		Chemical formula = new Chemical(aJCas);
		formula.setBegin(begin);
		formula.setEnd(end);
        formula.setId("chemical substance: " + id);
		formula.setSource(Constants.DICTIONARY);
		formula.addToIndexes();
		return formula;
	}
}
