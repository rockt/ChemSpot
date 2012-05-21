package de.berlin.hu.uima.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.cas.FSIndex;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.u_compare.shared.semantic.NamedEntity;
import org.u_compare.shared.syntactic.Sentence;
import org.uimafit.util.JCasUtil;

import banner.types.Token;

/**
 * @author Tim Rocktäschel
 *
 */
public class Util {
	
	/**
	 * @return all tokens that occur within the sentence
	 */
	public static List<org.u_compare.shared.syntactic.Token> getTokens(JCas aJCas,
			int sentenceBegin, int sentenceEnd) {
		List<org.u_compare.shared.syntactic.Token> tokensInSentence = new ArrayList<org.u_compare.shared.syntactic.Token>();
		FSIndex<Annotation> tokenIndex = aJCas.getAnnotationIndex(org.u_compare.shared.syntactic.Token.type);
		org.u_compare.shared.syntactic.Token dummyToken = new org.u_compare.shared.syntactic.Token(aJCas);
		dummyToken.setBegin(sentenceBegin - 1);
		dummyToken.setEnd(sentenceBegin - 1);
		Iterator<Annotation> tokenIterator = tokenIndex.iterator(dummyToken);

		while (tokenIterator.hasNext()) {
			org.u_compare.shared.syntactic.Token currentToken = (org.u_compare.shared.syntactic.Token) tokenIterator.next();
			int currentTokenBegin = currentToken.getBegin();
			int currentTokenEnd = currentToken.getEnd();
			
			if (currentTokenBegin < sentenceEnd && currentTokenEnd <= sentenceEnd) {
				tokensInSentence.add(currentToken);
			} else {
				break;
			}
		}
		return tokensInSentence;
	}
	
	public static List<org.u_compare.shared.syntactic.Token> getTokens(JCas aJCas,
			Sentence sentence) {
		List<org.u_compare.shared.syntactic.Token> tokensInSentence = new ArrayList<org.u_compare.shared.syntactic.Token>();
		Iterator<org.u_compare.shared.syntactic.Token> tokenIterator = JCasUtil.iterator(sentence, org.u_compare.shared.syntactic.Token.class, true, true);
		while (tokenIterator.hasNext()) {
			org.u_compare.shared.syntactic.Token currentToken = (org.u_compare.shared.syntactic.Token) tokenIterator.next();
			tokensInSentence.add(currentToken);
		}
		return tokensInSentence;
	}
	
	
	public static void tokenizeBannerSentence(banner.types.Sentence bannerSentence,
			List<org.u_compare.shared.syntactic.Token> tokensInSentence) {
		//get the absolute position of the first token in the document
		try {
			int offset = tokensInSentence.get(0).getBegin();
			for (org.u_compare.shared.syntactic.Token token : tokensInSentence) {
				//create a new token with the relative position in the sentence
				try {
					Token bannerToken = new Token(bannerSentence, token.getBegin() - offset, token.getEnd() - offset);
					bannerSentence.addToken(bannerToken);
				} catch (IllegalArgumentException e) {
					System.out.println(e);
					System.out.println(bannerSentence.getText());
					System.out.println("tokentext: [" + token.getCoveredText() + "]");
				}
			}
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
	}
}
