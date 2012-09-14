package de.berlin.hu.uima.ae.tagger.abbrev;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.u_compare.shared.semantic.chemical.Chemical;

import de.berlin.hu.chemspot.Mention;
import de.berlin.hu.util.Constants;

public class AbbreviationTagger extends JCasAnnotator_ImplBase {
	ExtractAbbrev abbrevTagger = null;
	
	public AbbreviationTagger() {
		abbrevTagger = new ExtractAbbrev();
	}

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		String text = aJCas.getDocumentText();
		List<Mention> abbreviations = abbrevTagger.getMentions(text);
		
		for (Mention abbr : abbreviations) {
			if (abbr.getText().length() < 2) {
				continue;
			}
			
			//createAbbreviationAnnotation(aJCas, abbr.getStart(), abbr.getEnd(), abbr.getId());
			
			Pattern pattern = Pattern.compile("(?<=\\b)" + Pattern.quote(abbr.getText()) + "(?=\\b)");
			Matcher matcher = pattern.matcher(text);
			while (matcher.find()) {
				int begin = matcher.start();
				int end = matcher.end();
				
				createAbbreviationAnnotation(aJCas, begin, end, abbr.getId());
			}
		}
	}
	
	private Chemical createAbbreviationAnnotation(JCas aJCas, int begin, int end, String id) {
		Chemical abbreviation = new Chemical(aJCas);
		abbreviation.setBegin(begin);
		abbreviation.setEnd(end);
        abbreviation.setId(id);
		abbreviation.setSource(Constants.ABBREV);
		abbreviation.addToIndexes();
		return abbreviation;
	}
}
