package de.berlin.hu.uima.util;

import opennlp.uima.Token;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import java.util.Iterator;

public class OpenNLPToUCompareTokenConverterAE extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		FSIndex<Annotation> tokenIndex = aJCas.getAnnotationIndex(Token.type);
		Iterator<Annotation> tokenIterator = tokenIndex.iterator();

		while (tokenIterator.hasNext()) {
			Token token = (Token) tokenIterator.next();
			org.u_compare.shared.syntactic.Token ucompareToken = new org.u_compare.shared.syntactic.Token(aJCas);
			ucompareToken.setBegin(token.getBegin());
			ucompareToken.setEnd(token.getEnd());
			ucompareToken.addToIndexes();
		}
	}
}
