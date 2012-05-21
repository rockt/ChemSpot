package de.berlin.hu.uima.cr.ddi;

import de.berlin.hu.uima.cr.ddi.parser.DDICorpusContentHandlerImpl;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.DocumentAnnotation;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.*;
import java.util.ArrayList;

/**
 * A simple collection reader that reads documents from a directory in the filesystem. It can be
 * configured with the following parameters:
 * <ul>
 * <li><code>InputDirectory</code> - path to directory containing files</li>
 * <li><code>Encoding</code> (optional) - character encoding of the input files</li>
 * <li><code>Language</code> (optional) - language of the input documents</li>
 * </ul>
 * 
 * 
 */
public class DDICorpusCR extends CollectionReader_ImplBase {
	/**
	 * Name of configuration parameter that must be set to the path of a directory containing input
	 * files.
	 */
	public static final String PARAM_INPUTDIR = "InputDirectory";

	/**
	 * Name of configuration parameter that contains the character encoding used by the input files.
	 * If not specified, the default system encoding will be used.
	 */
	public static final String PARAM_ENCODING = "Encoding";

	/**
	 * Name of optional configuration parameter that contains the language of the documents in the
	 * input directory. If specified this information will be added to the CAS.
	 */
	public static final String PARAM_LANGUAGE = "Language";

	/**
	 * Name of optional configuration parameter that indicates including
	 * the subdirectories (recursively) of the current input directory.
	 */
	public static final String PARAM_SUBDIR = "BrowseSubdirectories";

	private ArrayList<File> mFiles;

	private String mEncoding;

	private String mLanguage;

	private Boolean mRecursive;

	private int mCurrentIndex;
	
	/**
	 * @see org.apache.uima.collection.CollectionReader_ImplBase#initialize()
	 */
	public void initialize() throws ResourceInitializationException {
		File directory = new File(((String) getConfigParameterValue(PARAM_INPUTDIR)).trim());
		mEncoding  = (String) getConfigParameterValue(PARAM_ENCODING);
		mLanguage  = (String) getConfigParameterValue(PARAM_LANGUAGE);
		mRecursive = (Boolean) getConfigParameterValue(PARAM_SUBDIR);
		if (null == mRecursive) { // could be null if not set, it is optional
			mRecursive = Boolean.FALSE;
		}
		mCurrentIndex = 0;

		// if input directory does not exist or is not a directory, throw exception
		if (!directory.exists() || !directory.isDirectory()) {
			throw new ResourceInitializationException(ResourceConfigurationException.DIRECTORY_NOT_FOUND,
					new Object[] { PARAM_INPUTDIR, this.getMetaData().getName(), directory.getPath() });
		}

		// get list of files in the specified directory, and subdirectories if the
		// parameter PARAM_SUBDIR is set to True
		mFiles = new ArrayList<File>();
		addFilesFromDir(directory);
	}

	/**
	 * This method adds files in the directory passed in as a parameter to mFiles.
	 * If mRecursive is true, it will include all files in all
	 * subdirectories (recursively), as well. 
	 * 
	 * @param dir
	 */
	private void addFilesFromDir(File dir) {
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			// needed because my filesystem is under version control
			if (!files[i].getName().endsWith(".svn")) {
				if (!files[i].isDirectory()) {
					mFiles.add(files[i]);
				} else if (mRecursive) {
					addFilesFromDir(files[i]);
				}
			}
		}
	}

	/**
	 * @see org.apache.uima.collection.CollectionReader#hasNext()
	 */
	public boolean hasNext() {
		return mCurrentIndex < mFiles.size();
	}

	private void parseCurrentCorpus(File file, JCas jcas) throws SAXException, IOException {
		ContentHandler ppiCorpusContentHandler = new DDICorpusContentHandlerImpl(jcas);
		
		XMLReader xmlReader = XMLReaderFactory.createXMLReader();
		xmlReader.setContentHandler(ppiCorpusContentHandler);
		
		Reader fileReader = new InputStreamReader(new FileInputStream(file));
		InputSource inputSource = new InputSource(fileReader);
		xmlReader.parse(inputSource);
	}

	/**
	 * @see org.apache.uima.collection.CollectionReader#getNext(org.apache.uima.cas.CAS)
	 */
	public void getNext(CAS aCAS) throws IOException, CollectionException {
		JCas jcas;
		try {
			jcas = aCAS.getJCas();
		} catch (CASException e) {
			throw new CollectionException(e);
		}
		
		File currentFile = mFiles.get(mCurrentIndex++);
		try {
			parseCurrentCorpus(currentFile, jcas);
		} catch (SAXException e) {
			throw new CollectionException(e);
		}

//		// open input stream to file
//		File file = (File) mFiles.get(mCurrentIndex++);
//		String text = FileUtils.file2String(file, mEncoding);
//		
//		// put document in CAS
//		jcas.setDocumentText(text);
//
		// set language if it was explicitly specified as a configuration parameter
		if (mLanguage != null) {
			((DocumentAnnotation) jcas.getDocumentAnnotationFs()).setLanguage(mLanguage);
		}

		// Also store location of source document in CAS. This information is critical
		// if CAS Consumers will need to know where the original document contents are located.
		// For example, the Semantic Search CAS Indexer writes this information into the
		// search index that it creates, which allows applications that use the search index to
		// locate the documents that satisfy their semantic queries.
		SourceDocumentInformation srcDocInfo = new SourceDocumentInformation(jcas);
//		srcDocInfo.setUri(currentFile.getAbsoluteFile().toURL().toString());
		srcDocInfo.setUri(currentFile.getAbsoluteFile().toURI().toString());
		
		srcDocInfo.setOffsetInSource(0);
		srcDocInfo.setDocumentSize((int) currentFile.length());
		srcDocInfo.setLastSegment(mCurrentIndex == mFiles.size());
		srcDocInfo.addToIndexes();
	}

	/**
	 * @see org.apache.uima.collection.base_cpm.BaseCollectionReader#close()
	 */
	public void close() throws IOException {
	}

	/**
	 * @see org.apache.uima.collection.base_cpm.BaseCollectionReader#getProgress()
	 */
	public Progress[] getProgress() {
		return new Progress[] { new ProgressImpl(mCurrentIndex, mFiles.size(), Progress.ENTITIES) };
	}

	/**
	 * Gets the total number of documents that will be returned by this collection reader. This is not
	 * part of the general collection reader interface.
	 * 
	 * @return the number of documents in the collection
	 */
	public int getNumberOfDocuments() {
		return mFiles.size();
	}

}
