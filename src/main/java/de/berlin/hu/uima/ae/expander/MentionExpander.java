package de.berlin.hu.uima.ae.expander;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.u_compare.shared.semantic.NamedEntity;
import org.u_compare.shared.syntactic.Token;
import org.uimafit.util.JCasUtil;

import de.berlin.hu.util.Constants;

public class MentionExpander extends JCasAnnotator_ImplBase {

	private NamedEntity expandEntity(NamedEntity ne) {
		String text = ne.getCAS().getDocumentText();
		int begin = ne.getBegin();
		int end = ne.getEnd();
		
		boolean changed = true;
		while (changed) {
			changed = false;
			if (begin-1 > 0 && (text.charAt(begin-1) + "").matches("[^\\s/\\.]")) {
				begin--;
				changed = true;
			}
			if (end < text.length() && (text.charAt(end) + "").matches("[^\\s/\\.]")) {
				end++;
				changed = true;
			}
		}
		
		NamedEntity result = (NamedEntity)ne.clone();
		result.setBegin(begin);
		result.setEnd(end);
		
		return result;
	}
	
	private static int countChar(char c, String s) {
		int result = 0;
		
		for (char ch : s.toCharArray()) {
			if (c == ch) result++;
		}
		
		return result;
	}
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		for (NamedEntity ne : JCasUtil.iterate(aJCas, NamedEntity.class)) {
			if (Constants.CRF.equals(ne.getSource())) continue;
			
			NamedEntity expanded = expandEntity(ne);
			
			if (expanded.getBegin() == ne.getBegin() && expanded.getEnd() == ne.getEnd()) continue;
			
			int left = -2;
			int right = -2;
			int i = 0;
			List<Token> tokens = new ArrayList<Token>();
			for (Token token : JCasUtil.iterate(Token.class, expanded)) {
				tokens.add(token);
				
				if (token.getBegin() <= ne.getBegin()) {
					left = i;
				} 
				if (right == -2 && token.getEnd() >= ne.getEnd()) {
					right = i;
				}
				
				i++;
			}
			
			int initialLeft = left;
			while (left > 0) {
				Token t = tokens.get(left-1);
				String pos = t.getLabel();
				
				if (pos != null && left-1 == 0 && pos.startsWith("VB")) {
					//System.out.println("- not expanding " + t.getCoveredText());
					break;
				}
				
				left--;
			}
			while (!tokens.get(left).getCoveredText().matches("([^\\p{Punct}]|[\\(\\[])+") && left < initialLeft) left++;
						
			int initialRight = right;
			while (right < tokens.size()-1) {
				Token t = tokens.get(right+1);
				String pos = t.getLabel();
				
				if (pos != null && right+1 == tokens.size()-1 && (pos.startsWith("VB") || pos.startsWith("JJ") || pos.equals("IN"))) {
					//System.out.println("- not expanding " + t.getCoveredText());
					break;
				}
				
				right++;
			}
			while (!tokens.get(right).getCoveredText().matches("([^\\p{Punct}]|[\\)\\]])+") && right > initialRight) right--;

			expanded.setBegin(tokens.get(left).getBegin());
			expanded.setEnd(tokens.get(right).getEnd());
			
			String text = expanded.getCoveredText();
			if ((text.startsWith("(") && text.endsWith(")")) || (text.startsWith("[") && text.endsWith("]"))) {
				expanded.setBegin(expanded.getBegin()+1);
				expanded.setEnd(expanded.getEnd()-1);
				text = expanded.getCoveredText();
			}
			
			while((text.startsWith("(") && countChar('(', text) > countChar(')', text))
					|| (text.startsWith("[") && countChar('[', text) > countChar(']', text))) {
				expanded.setBegin(expanded.getBegin()+1);
				text = expanded.getCoveredText();
			}
			
			while((text.endsWith(")") && countChar(')', text) > countChar('(', text))
					|| (text.endsWith("]") && countChar(']', text) > countChar('[', text))) {
				expanded.setEnd(expanded.getEnd()-1);
				text = expanded.getCoveredText();
			}
			
			if (expanded.getBegin() != ne.getBegin() || expanded.getEnd() != ne.getEnd()) {
				//System.out.printf("+ Expanding %s to %s%n", ne.getCoveredText(), expanded.getCoveredText());
				ne.setBegin(expanded.getBegin());
				ne.setEnd(expanded.getEnd());
			}
		}
	}
}
