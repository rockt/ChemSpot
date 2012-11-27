package de.berlin.hu.uima.cr.xml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public abstract class XMLCollectionReader extends CollectionReader_ImplBase {
	/**
	 * Name of configuration parameter that must be set to the path of a directory containing input
	 * files.
	 */
	public static final String PARAM_INPUTDIR = "InputDirectory";
	
	protected String inputDirectory = null;
	protected int numDocuments = 0;
	protected int currentDocument = 0;
	protected List<File> files = null;
	
	protected abstract List<File> getfiles(String inputDir);
	
	@Override
	public void initialize() throws ResourceInitializationException {
		inputDirectory = (String)getConfigParameterValue(PARAM_INPUTDIR);
		files = getfiles(inputDirectory);
		numDocuments = files.size();
		currentDocument = 0;
	}
	
	protected Document getNextDocument() throws CollectionException, IOException {
		if (currentDocument >= numDocuments) throw new CollectionException();
		
		File file = files.get(currentDocument++);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new IOException(e);
		}
	    Document document;
		try {
			document = builder.parse(file);
		} catch (SAXException e) {
			throw new IOException(e);
		}
	    
	    return document;
	}
	
	@Override
	public abstract void getNext(CAS aCAS) throws IOException, CollectionException;

	@Override
	public boolean hasNext() {
		return currentDocument < numDocuments;
	}

	@Override
	public Progress[] getProgress() {
		return new Progress[] { new ProgressImpl(currentDocument, numDocuments, Progress.ENTITIES) };
	}

	@Override
	public void close() throws IOException {
		// nothing to do
	}

}
