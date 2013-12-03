package de.berlin.hu.uima.util;

import opennlp.uima.Sentence;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.uimafit.util.JCasUtil;

import de.berlin.hu.types.PubmedDocument;

import java.util.ArrayList;
import java.util.Iterator;

public class OpenNLPToUCompareSentenceConverterAE extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		for (PubmedDocument document : JCasUtil.iterate(aJCas, PubmedDocument.class)) {
			Iterator<Sentence> sentenceIterator = JCasUtil.selectCovered(aJCas, Sentence.class, document).iterator();
			
			ArrayList<Sentence> sentencesToRemove = new ArrayList<Sentence>();
			
			if (!sentenceIterator.hasNext()) {
				//System.out.println("Document does not have any sentences. Adding sentence for entire document.");
				org.u_compare.shared.syntactic.Sentence ucompareSentence = new org.u_compare.shared.syntactic.Sentence(aJCas);
				ucompareSentence.setBegin(0);
				ucompareSentence.setEnd(aJCas.getDocumentText().length());
				ucompareSentence.addToIndexes();
			}
			
			Sentence sentence = null;
			while (sentenceIterator.hasNext()) {
				sentence = sentenceIterator.next();
				org.u_compare.shared.syntactic.Sentence ucompareSentence = new org.u_compare.shared.syntactic.Sentence(aJCas);
				ucompareSentence.setBegin(sentence.getBegin());
				ucompareSentence.setEnd(sentence.getEnd());
				ucompareSentence.addToIndexes();
				sentencesToRemove.add(sentence);
			}
			if (sentence != null && sentence.getEnd() < document.getEnd() && !document.getCoveredText().substring(sentence.getEnd()+1 - document.getBegin()).matches("\\s*")) {
				//System.out.println("Sentences do not cover entire document. Adding sentence add the end.");
				org.u_compare.shared.syntactic.Sentence ucompareSentence = new org.u_compare.shared.syntactic.Sentence(aJCas);
				ucompareSentence.setBegin(sentence.getEnd()+1);
				ucompareSentence.setEnd(aJCas.getDocumentText().length());
				ucompareSentence.addToIndexes();
			}
			
			for (Sentence sentence2 : sentencesToRemove) {
				sentence2.removeFromIndexes(aJCas);
			}
		}
	}

}
