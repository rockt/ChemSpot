/**
 * 
 */
package de.berlin.hu.uima.cr.ddi.parser;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.u_compare.shared.semantic.NamedEntity;
import org.uimafit.util.JCasUtil;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import de.berlin.hu.types.PubmedDocument;
import de.berlin.hu.util.Constants;
import sprint.uima.types.*;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * @author Tim Rocktäschel
 *
 */

//FIXME: probably not working anymore
public class DDICorpusContentHandlerImpl implements ContentHandler {
	
	// tags
	private static final String CORPUS_TAG = "corpus";	// not needed
	private static final String DOCUMENT_TAG = "document";
	private static final String SENTENCE_TAG = "sentence";
	private static final String SENTENCEANALYSES_TAG = "sentenceanalyses";
	private static final String TOKENIZATIONS_TAG = "tokenizations";
	private static final String TOKENIZATION_TAG = "tokenization";

	// single tags
	private static final String ENTITY_TAG = "entity";
	private static final String PAIR_TAG = "pair";
	private static final String TOKEN_TAG = "token";
	
	// attributes
	private static final String CHAR_OFFSET_ATTR = "charOffset";
	private static final String ID_ATTR = "id";
	private static final String TEXT_ATTR = "text";
	private static final String ENTITY_TYPE_ATTR = "type";
	private static final String ENTITY_1_ATTR = "e1";
	private static final String ENTITY_2_ATTR = "e2";
	private static final String INTERACTION_ATTR = "interaction";
	private static final String TOKENIZER_ATTR = "tokenizer";
	private static final String POS_ATTR = "POS";
	
	// TODO: add annotation
	// parses
	// parse
	// dependency 

	private enum ElementType {Ignore, Corpus, Document, Sentence, Sentenceanalyses, Tokenizations, Tokenization};
	
	private ElementType currentElementType;
	
	private JCas jcas;
	
	private StringBuffer documentTextStringBuffer;
	private int sentenceOffset;
	private int nextSentenceOffset;
	private String documentId;
	private Sentence currentSentence;
	private ArrayList<Pair> currentPairs;
	private boolean firstEntitySeen;

	
	public DDICorpusContentHandlerImpl(JCas jcas) {
		this.jcas = jcas;
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		// is not needed
		if (CORPUS_TAG.equalsIgnoreCase(qName)) {
			currentElementType = ElementType.Corpus;
		} else if (DOCUMENT_TAG.equalsIgnoreCase(qName)) {
			createDocumentAnnotation(atts);
			firstEntitySeen = false;
		} else if (SENTENCE_TAG.equalsIgnoreCase(qName)) {
			createSentenceAnnotation(atts);
		} else if (ENTITY_TAG.equalsIgnoreCase(qName)) {
			createEntityAnnotation(atts);
			firstEntitySeen = true;
		} else if (PAIR_TAG.equalsIgnoreCase(qName)) {
//			createPairAnnotation(atts);
		} else if (SENTENCEANALYSES_TAG.equalsIgnoreCase(qName)) {
			currentElementType = ElementType.Sentenceanalyses;
		} else if (TOKENIZATIONS_TAG.equalsIgnoreCase(qName)) {
			currentElementType = ElementType.Tokenizations;
		} else if (TOKENIZATION_TAG.equalsIgnoreCase(qName)) {
			String tokenizerName = atts.getValue(TOKENIZER_ATTR);
			
			// TODO make switch between Charniak-Lease and split possible
			if (tokenizerName.equals("Charniak-Lease")) {
//			if (tokenizerName.equals("split")) {
				currentElementType = ElementType.Tokenization;
			}
		} else if (TOKEN_TAG.equalsIgnoreCase(qName)) {
			if (currentElementType.equals(ElementType.Tokenization)) {
				createTokenAnnotation(atts);
			}
		} else {
			currentElementType = ElementType.Ignore;
		}
	}


	private void createDocumentAnnotation(Attributes atts) {
		currentElementType = ElementType.Document;
		documentTextStringBuffer = new StringBuffer();
		sentenceOffset = 0;
		nextSentenceOffset = 0;
		documentId = atts.getValue(ID_ATTR);
	}


	private void createSentenceAnnotation(Attributes atts) {
		currentElementType = ElementType.Sentence;
		String sentenceText = atts.getValue(TEXT_ATTR) + " ";
		documentTextStringBuffer.append(sentenceText);
		sentenceOffset = nextSentenceOffset;
		nextSentenceOffset += sentenceText.length();
		
		Sentence sentence = new Sentence(jcas, sentenceOffset, nextSentenceOffset - 1);
		sentence.setID(atts.getValue(ID_ATTR));
		sentence.addToIndexes();
		
		currentSentence = sentence;
		currentPairs = new ArrayList<Pair>();
	}


	private void createEntityAnnotation(Attributes atts) {
		String charOffset = atts.getValue(CHAR_OFFSET_ATTR);
		
		String[] boundaries = atts.getValue(CHAR_OFFSET_ATTR).split("-");
		// e.g. charOffset="0-2,5-5" gets to 0-5
		int begin = sentenceOffset + Integer.valueOf(boundaries[0]);
		int end = sentenceOffset + Integer.valueOf(boundaries[boundaries.length-1]);
		
		if (!firstEntitySeen) {
			end = end + 1;
		}
		
		Entity entity = new Entity(jcas, begin, end);
		entity.setID(atts.getValue(ID_ATTR));
		entity.setEntityType(atts.getValue(ENTITY_TYPE_ATTR));
		entity.setCharOffset(charOffset);
		entity.addToIndexes();
		
		NamedEntity namedEntity = new NamedEntity(jcas, begin, end);
		namedEntity.setId(atts.getValue(ID_ATTR));
		namedEntity.setEntityType(atts.getValue(ENTITY_TYPE_ATTR));
		namedEntity.setSource(Constants.GOLDSTANDARD);
		namedEntity.addToIndexes();
	}


	private void createPairAnnotation(Attributes atts) {
		Pair pair = new Pair(jcas);
		pair.setID(atts.getValue(ID_ATTR));
		
		pair.setInteraction(Boolean.parseBoolean(atts.getValue(INTERACTION_ATTR)));
		
		String entity1ID = atts.getValue(ENTITY_1_ATTR);
		String entity2ID = atts.getValue(ENTITY_2_ATTR);
		
		// TODO: use JCasUtil.iterator instead?
		Iterator<Entity> entityIterator = JCasUtil.iterate(jcas, Entity.class, currentSentence).iterator();
		Entity entity1 = null;
		Entity entity2 = null;
		while (entityIterator.hasNext()) {
			Entity currentEntity = entityIterator.next();
			if (entity1ID.equals(currentEntity.getID())) {
				entity1 = currentEntity;
			} else if (entity2ID.equals(currentEntity.getID())) {
				entity2 = currentEntity;
			}
		}
		
		pair.setEntity1(entity1);
		pair.setEntity2(entity2);

		if (entity1.getBegin() < entity2.getBegin()) {
			pair.setBegin(entity1.getBegin());
			pair.setEnd(entity2.getEnd());
		} else {
			pair.setBegin(entity2.getBegin());
			pair.setEnd(entity1.getEnd());
		}
		
		pair.addToIndexes();
		currentPairs.add(pair);
	}

	private void createTokenAnnotation(Attributes atts) {
		String[] boundaries = atts.getValue(CHAR_OFFSET_ATTR).split("-");
		int begin = sentenceOffset + Integer.valueOf(boundaries[0]);
		int end = sentenceOffset + Integer.valueOf(boundaries[1]) + 1;
		
		Token entity = new Token(jcas, begin, end);
		entity.setID(atts.getValue(ID_ATTR));
		entity.setPOS(atts.getValue(POS_ATTR));
		entity.addToIndexes();
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (CORPUS_TAG.equalsIgnoreCase(qName)) {
			//TODO
		} else if (DOCUMENT_TAG.equalsIgnoreCase(qName)) {
			jcas.setDocumentText(documentTextStringBuffer.toString());
			CorpusDocument corpusDocument = new CorpusDocument(jcas, 0, documentTextStringBuffer.length());
			corpusDocument.setID(documentId);
			corpusDocument.addToIndexes();
			PubmedDocument pubmedDocument = new PubmedDocument(jcas, 0, documentTextStringBuffer.length());
			pubmedDocument.setPmid(documentId);
			pubmedDocument.addToIndexes();
		} else if (SENTENCE_TAG.equalsIgnoreCase(qName)) {
			FSArray pairs = new FSArray(jcas, currentPairs.size());
			for (int j = 0; j < pairs.size(); j++) {
				pairs.set(j, currentPairs.get(j));
			}
			currentSentence.setPairs(pairs);
		} else if (SENTENCEANALYSES_TAG.equalsIgnoreCase(qName)) {
			//TODO
		} else if (TOKENIZATIONS_TAG.equalsIgnoreCase(qName)) {
			//TODO
		} else if (TOKENIZATION_TAG.equalsIgnoreCase(qName)) {
			currentElementType = ElementType.Sentenceanalyses;
		}
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#setDocumentLocator(org.xml.sax.Locator)
	 */
	public void setDocumentLocator(Locator locator) {
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startDocument()
	 */
	public void startDocument() throws SAXException {
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endDocument()
	 */
	public void endDocument() throws SAXException {
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String, java.lang.String)
	 */
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
	 */
	public void endPrefixMapping(String prefix) throws SAXException {
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#characters(char[], int, int)
	 */
	public void characters(char[] ch, int start, int length)
			throws SAXException {
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#ignorableWhitespace(char[], int, int)
	 */
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String, java.lang.String)
	 */
	public void processingInstruction(String target, String data)
			throws SAXException {
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
	 */
	public void skippedEntity(String name) throws SAXException {
	}

}
