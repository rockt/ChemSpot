package de.berlin.hu.uima.util;

import java.util.ArrayList;
import java.util.Iterator;

import opennlp.uima.Sentence;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

public class OpenNLPToUCompareSentenceConverterAE extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		FSIndex<Annotation> sentenceIndex = aJCas.getAnnotationIndex(Sentence.type);
		Iterator<Annotation> sentenceIterator = sentenceIndex.iterator();
		
		ArrayList<Sentence> sentencesToRemove = new ArrayList<Sentence>();
		
		while (sentenceIterator.hasNext()) {
			Sentence sentence = (Sentence) sentenceIterator.next();
			org.u_compare.shared.syntactic.Sentence ucompareSentence = new org.u_compare.shared.syntactic.Sentence(aJCas);
			ucompareSentence.setBegin(sentence.getBegin());
			ucompareSentence.setEnd(sentence.getEnd());
			ucompareSentence.addToIndexes();
			sentencesToRemove.add(sentence);
		}
		
		for (Sentence sentence : sentencesToRemove) {
			sentence.removeFromIndexes(aJCas);
		}
	}

}
