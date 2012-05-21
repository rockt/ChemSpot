package de.berlin.hu.uima.util;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import sprint.uima.types.Entity;
import sprint.uima.types.Sentence;
import sprint.uima.types.Token;

import java.util.Iterator;

public class DDIToUCompareConverter extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		//sentences
		FSIndex<Annotation> sentenceIndex = aJCas.getAnnotationIndex(Sentence.type);
		Iterator<Annotation> sentenceIterator = sentenceIndex.iterator();

		while (sentenceIterator.hasNext()) {
			Sentence sentence = (Sentence) sentenceIterator.next();
			org.u_compare.shared.syntactic.Sentence ucompareSentence = new org.u_compare.shared.syntactic.Sentence(aJCas);
			ucompareSentence.setBegin(sentence.getBegin());
			ucompareSentence.setEnd(sentence.getEnd());
			ucompareSentence.addToIndexes();
		}
		
		//tokens
		FSIndex<Annotation> tokenIndex = aJCas.getAnnotationIndex(Token.type);
		Iterator<Annotation> tokenIterator = tokenIndex.iterator();

		while (tokenIterator.hasNext()) {
			Token token = (Token) tokenIterator.next();
			org.u_compare.shared.syntactic.Token ucompareToken = new org.u_compare.shared.syntactic.Token(aJCas);
			ucompareToken.setBegin(token.getBegin());
			ucompareToken.setEnd(token.getEnd());
			ucompareToken.addToIndexes();
		}
		
		//entities
		FSIndex<Annotation> entitiyIndex = aJCas.getAnnotationIndex(Entity.type);
		Iterator<Annotation> entityIterator = entitiyIndex.iterator();

		while (entityIterator.hasNext()) {
			Entity sentence = (Entity) entityIterator.next();
			org.u_compare.shared.semantic.NamedEntity ucompareNamedEntity = new org.u_compare.shared.semantic.NamedEntity(aJCas);
			ucompareNamedEntity.setBegin(sentence.getBegin());
			ucompareNamedEntity.setEnd(sentence.getEnd());
			ucompareNamedEntity.setSource("goldstandard");
			ucompareNamedEntity.setConfidence(1.0);
			ucompareNamedEntity.addToIndexes();
		}
	}

}
