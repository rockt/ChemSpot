package de.berlin.hu.uima.cr.craft;

import java.io.IOException;
import java.util.Iterator;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.examples.xmi.XmiCollectionReader;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.tcas.DocumentAnnotation;
import org.u_compare.shared.semantic.NamedEntity;

import de.berlin.hu.types.PubmedDocument;
import de.berlin.hu.util.Constants;
import edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation;

public class CraftCR extends XmiCollectionReader {
	private void createNewNamedEntityAnnotation(int begin, int end, String id, JCas jcas) {
		NamedEntity namedEntity = new NamedEntity(jcas);
		namedEntity.setBegin(begin);
		namedEntity.setEnd(end);
		namedEntity.setId(id);
		namedEntity.setEntityType("CHEBI");
		namedEntity.setConfidence(1.0);
		namedEntity.setSource(Constants.GOLDSTANDARD);
		namedEntity.addToIndexes();
	}
	
	public void getNext(CAS aCAS) throws IOException, CollectionException {
		super.getNext(aCAS);
		
		this.getProcessingResourceMetaData().getSourceUrl().getFile();
		
		try {
			JCas jcas = aCAS.getJCas();
			
			DocumentAnnotation documentAnnotation = (DocumentAnnotation)jcas.getDocumentAnnotationFs();
			int docBegin = documentAnnotation.getBegin();
			int docEnd = documentAnnotation.getEnd();
			
			PubmedDocument abstractAnnotation = new PubmedDocument(jcas);
			abstractAnnotation.setBegin(docBegin);
			abstractAnnotation.setEnd(docEnd);
			abstractAnnotation.addToIndexes(jcas);		
			
			/*for (Annotation a : jcas.getAnnotationIndex()) {
				StringBuffer buffer = new StringBuffer();
				a.prettyPrint(0, 0, buffer, true);
				System.out.println();
				System.out.println(buffer);
			}*/
			
			for (Annotation a : jcas.getAnnotationIndex(CCPTextAnnotation.type)) {
				CCPTextAnnotation textAnnotation = (CCPTextAnnotation)a;
				int begin = textAnnotation.getBegin();
				int end = textAnnotation.getEnd();
				String id = textAnnotation.getClassMention().getMentionName();
				
				createNewNamedEntityAnnotation(begin, end, id, jcas);
			}
		} catch (CASException e) {
			throw new CollectionException(e);
		}
	}
}
