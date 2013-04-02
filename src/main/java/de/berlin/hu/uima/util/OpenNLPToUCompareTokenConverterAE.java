package de.berlin.hu.uima.util;

import opennlp.uima.Token;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.uimafit.util.JCasUtil;

import java.util.Iterator;

public class OpenNLPToUCompareTokenConverterAE extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		Iterator<org.u_compare.shared.syntactic.Token> uCompareTokenIterator = JCasUtil.iterator(aJCas, org.u_compare.shared.syntactic.Token.class);
		for (Token token : JCasUtil.iterate(aJCas, opennlp.uima.Token.class)) {
			org.u_compare.shared.syntactic.Token uCompareToken = uCompareTokenIterator.next();
			
			uCompareToken.setLabel(token.getPos());
		}
		
		/*List<Annotation> oldTokens = new ArrayList<Annotation>();
		for (Annotation token : JCasUtil.iterate(aJCas, opennlp.uima.Token.class)) {
			oldTokens.add(token);
		}
		for (Annotation oldToken : oldTokens) {
			oldToken.removeFromIndexes();
		}*/
	}
}
