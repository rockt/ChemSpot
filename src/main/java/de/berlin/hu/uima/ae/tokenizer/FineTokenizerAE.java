package de.berlin.hu.uima.ae.tokenizer;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.u_compare.shared.syntactic.Token;

/**
 * A fine-grained tokenizer. It also splits at number-letter-changes.
 */
public class FineTokenizerAE extends JCasAnnotator_ImplBase {
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		String text = aJCas.getDocumentText();
		char[] chars = text.toCharArray();
		int start = 0;
		for (int i = 0; i < chars.length; i++) {
			char ch = chars[i];
            //FIXME: find a better way
			char nch = '¬'; //dummy character
			if (i < chars.length - 1) nch = chars[i+1];
			if (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r') {
				start++;				
			}
			else {
				if (Character.isDigit(ch) && !Character.isDigit(nch)) {
					createNewTokenAnnotation(aJCas, start, i);
					start = i+1;
				} else if (Character.isLetter(ch) && !Character.isLetter(nch)) {
					createNewTokenAnnotation(aJCas, start, i);
					start = i+1;
				} else if (!(Character.isDigit(ch) || Character.isLetter(ch))) {
					createNewTokenAnnotation(aJCas, start, i);
					start = i+1;
				}				 
			}
		}		
	}
	
	private void createNewTokenAnnotation(JCas aJCas, int begin, int end) {
		Token token = new Token(aJCas);
		token.setBegin(begin);
		token.setEnd(end+1);
		token.addToIndexes();
		
		// tokens for OpenNLP POS tagger
		opennlp.uima.Token token2 = new opennlp.uima.Token(aJCas);
		token2.setBegin(begin);
		token2.setEnd(end+1);
		token2.addToIndexes();
	}
}
