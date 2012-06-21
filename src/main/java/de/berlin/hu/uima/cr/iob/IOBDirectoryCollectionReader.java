/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package de.berlin.hu.uima.cr.iob;

import de.berlin.hu.types.PubmedDocument;
import de.berlin.hu.util.Constants;
import org.apache.uima.UIMAFramework;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.impl.XCASDeserializer;
import org.apache.uima.cas.impl.XmiCasDeserializer;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.*;
import org.u_compare.shared.semantic.NamedEntity;
import org.u_compare.shared.syntactic.Token;
import org.xml.sax.SAXException;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.zip.GZIPInputStream;

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
public class IOBDirectoryCollectionReader extends CollectionReader_ImplBase {
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
	 * Optional configuration parameter that specifies XCAS input files
	 */
	public static final String PARAM_XCAS = "XCAS";

	public static final String PARAM_LENIENT = "LENIENT";

	private ArrayList mFiles;

	private String mEncoding;

	private String mLanguage;

	private int mCurrentIndex;

	private boolean mTEXT;

	private String mXCAS;

	private boolean lenient;

	private static final String I_TYPE = "|I-";
	//	private static final String O_TYPE = "|O";
	private static final String B_TYPE = "|B-";

	private static final String ENTITY_TYPE = "IUPAC";

	public static final String PARAM_USE_GOLDSTANDARD_ANNOTATIONS = "UseGoldstandardAnnotations";
	public static final String PARAM_GOLDSTANDARD_TYPE_SUFFIX = "GoldstandardTypeSuffix";
	public static final String PARAM_INCLUDE_SUFFIXES = "IncludeSuffixes";

	private int currentDocument = 0;
	private int numDocuments = 0;

	private Map<String,Integer> typeCounts = new HashMap<String, Integer>();
	private int numberOfTokens;


	private boolean useGoldstandardAnnotations;
	private String goldstandardTypeSuffix;
	private int numberOfTokensInEntity;
	private StringBuffer docText;
	private int numberOfEntities;
	private int numberOfShortEntities;
	private HashSet<String> includeSuffixes;

	private final boolean provideGoldstandardTokenization = false; //TODO turn this into a parameter

	/**
	 * @see org.apache.uima.collection.CollectionReader_ImplBase#initialize()
	 */
	public void initialize() throws ResourceInitializationException {
		String dirPath = ((String) getConfigParameterValue(PARAM_INPUTDIR)).trim();
		File directory = new File(dirPath);
		mEncoding = (String) getConfigParameterValue(PARAM_ENCODING);
		mLanguage = (String) getConfigParameterValue(PARAM_LANGUAGE);
		mXCAS = (String) getConfigParameterValue(PARAM_XCAS);
		//XCAS parameter can be set to "xcas" or "xmi", as well as "true" (which for historical reasons
		//means the same as "xcas").  Any other value will cause the input file to be treated as a text document.
		mTEXT = !("xcas".equalsIgnoreCase(mXCAS) || "xmi".equalsIgnoreCase(mXCAS) || "true".equalsIgnoreCase(mXCAS));
		String mLenient = (String) getConfigParameterValue(PARAM_LENIENT);
		lenient = "true".equalsIgnoreCase(mLenient);

		mCurrentIndex = 0;


		includeSuffixes = new HashSet<String>(Arrays.asList((String[]) getConfigParameterValue(PARAM_INCLUDE_SUFFIXES)));

		
		
		// if input directory does not exist or is not a directory, throw exception
		if (!directory.exists()) {
			throw new ResourceInitializationException(ResourceConfigurationException.DIRECTORY_NOT_FOUND,
					new Object[] { PARAM_INPUTDIR, this.getMetaData().getName(), directory.getPath() });
		}
						
		// get list of files (not subdirectories) in the specified directory
		mFiles = new ArrayList();
		
		if (directory.isDirectory()) {		
			File[] files = directory.listFiles();
			for (int i = 0; i < files.length; i++) {
				boolean validSuffix = false;
				for (String suffix : includeSuffixes) {
					if (files[i].getAbsolutePath().endsWith(suffix)) validSuffix = true;
				}			
				if (!files[i].isDirectory() && validSuffix) {
					mFiles.add(files[i]);
				}
			}
		} else {
			mFiles.add(directory);
		}

		useGoldstandardAnnotations = ((Boolean) getConfigParameterValue(PARAM_USE_GOLDSTANDARD_ANNOTATIONS)).booleanValue();
		goldstandardTypeSuffix = (String) getConfigParameterValue(PARAM_GOLDSTANDARD_TYPE_SUFFIX);

		System.err.println("CR initialization complete. # files to process: " + mFiles.size());    
	}

	/**
	 * @see org.apache.uima.collection.CollectionReader#hasNext()
	 */
	public boolean hasNext() {
		return mCurrentIndex < mFiles.size();
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

		// open input stream to file
		File file = (File) mFiles.get(mCurrentIndex++);
		FileInputStream fis = new FileInputStream(file);
		
		if (mTEXT) {
			try {
				// if there's a CAS Initializer, call it
				if (getCasInitializer() != null) {
					getCasInitializer().initializeCas(fis, aCAS);
				} else // No CAS Initiliazer, so read file and set document text ourselves
				{
					String text = "";
					if (file.getAbsolutePath().endsWith("gz")) {
						GZIPInputStream is = new GZIPInputStream(fis);
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
						StringBuilder sb = new StringBuilder();
						String line = null;
						while ((line = bufferedReader.readLine()) != null) {
							sb.append(line + "\n");
						}
						bufferedReader.close();
						text = sb.toString();
					}
					else {
						text = FileUtils.file2String(file, mEncoding);      
					}
					// put document text in JCas
					//jcas.setDocumentText(text);
					buildCASFromIOBCorpus(jcas, text);
				}
			} finally {
				if (fis != null)
					fis.close();
			}

			// set language if it was explicitly specified as a configuration parameter
			if (mLanguage != null) {
				jcas.setDocumentLanguage(mLanguage);
			}

			// Also store location of source document in CAS. This information is critical
			// if CAS Consumers will need to know where the original document contents are located.
			// For example, the Semantic Search CAS Indexer writes this information into the
			// search index that it creates, which allows applications that use the search index to
			// locate the documents that satisfy their semantic queries.
			SourceDocumentInformation srcDocInfo = new SourceDocumentInformation(jcas);
			srcDocInfo.setUri(file.getAbsoluteFile().toURL().toString());
			srcDocInfo.setOffsetInSource(0);
			srcDocInfo.setDocumentSize((int) file.length());
			srcDocInfo.setLastSegment(mCurrentIndex == mFiles.size());
			srcDocInfo.addToIndexes();
		}
		// XCAS input files
		else {
			try {
				if (mXCAS.equalsIgnoreCase("xmi")) {
					XmiCasDeserializer.deserialize(fis, aCAS, lenient);
				}
				else {
					XCASDeserializer.deserialize(fis, aCAS, lenient);
				}
			} catch (SAXException e) {
				UIMAFramework.getLogger(IOBDirectoryCollectionReader.class).log(Level.WARNING,
						"Problem with XML input file: " + file.getAbsolutePath());
				throw new CollectionException(e);
			} finally {
				fis.close();
			}
		}  
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

	/**
	 * Parses and returns the descriptor for this collection reader. The descriptor is stored in the
	 * uima.jar file and located using the ClassLoader.
	 * 
	 * @return an object containing all of the information parsed from the descriptor.
	 * 
	 * @throws InvalidXMLException
	 *           if the descriptor is invalid or missing
	 */
	public static CollectionReaderDescription getDescription() throws InvalidXMLException {
		InputStream descStream = IOBDirectoryCollectionReader.class
				.getResourceAsStream("IOBDirectoryCollectionReader.xml");
		return UIMAFramework.getXMLParser().parseCollectionReaderDescription(
				new XMLInputSource(descStream, null));
	}

	public static URL getDescriptorURL() {
		return IOBDirectoryCollectionReader.class.getResource("IOBDirectoryCollectionReader.xml");
	}

	private void buildCASFromIOBCorpus(JCas jcas, String iobText) {
		Scanner lineScanner = new Scanner(iobText);
		lineScanner.useDelimiter("\n");
		int endOfCurrentToken = 0;
		docText = new StringBuffer();
		int beginOfCurrentEntity = -1;
		String currentLine = lineScanner.next();
		String nextLine = null;


		int beginOfCurrentAbstract = 0;

		//		boolean ignoreNextLine = false;

		boolean firstLine = true;
		boolean newAbstract = false;
		boolean currentTokenIsPMID = false;
		String currentPMID = "";

		int endOfLastTokenInCurrentAbstract = 0;

		while (currentLine != null) {
			if(lineScanner.hasNext()) {
				nextLine = lineScanner.next();
			} else {
				nextLine = null;
			}

			if (currentLine.startsWith("###")) {
				//FIXME: that doesn't make sense!
				if (firstLine) {
					firstLine = false;
					currentPMID = currentLine.substring(4);
				} else {
					docText.append("\n");
					newAbstract = true;

					if (beginOfCurrentAbstract != endOfCurrentToken) {
						PubmedDocument abstractAnnotation = new PubmedDocument(jcas);
						abstractAnnotation.setBegin(beginOfCurrentAbstract);
						abstractAnnotation.setEnd(endOfCurrentToken);
						abstractAnnotation.setPmid(currentPMID);
						abstractAnnotation.addToIndexes(jcas);					
					}

					beginOfCurrentAbstract = docText.length();		
					currentPMID = currentLine.substring(4);

					//FIXME!!!
					//check if it is the first token of an abstract
					//remove the pmid from the text!
				}
			} else if (!currentLine.trim().isEmpty()) {
				String[] columns = currentLine.split("\t");

				if (columns.length > 0) {
					numberOfTokens++;

					String currentTokenText = columns[0]; 

					if (newAbstract) {
						newAbstract = false;
						//first token is likely to be a PMID
						if (currentTokenText.matches("[0-9]+") && currentTokenText.length() > 4) {
							//replace with whitespaces
							currentTokenText = currentTokenText.replaceAll("[0-9]", " ");
							currentTokenIsPMID = true;
						}
					}

					int beginOfCurrentTokenInCurrentAbstract = Integer.valueOf(columns[1]).intValue();
					int endOfCurrentTokenInCurrentAbstract = Integer.valueOf(columns[2]).intValue();
					String currentTokenType = columns[columns.length - 1];

					int numberOfWhiteSpaces = beginOfCurrentTokenInCurrentAbstract - endOfLastTokenInCurrentAbstract;
					endOfLastTokenInCurrentAbstract = endOfCurrentTokenInCurrentAbstract;
					docText.append(insertNWhitespaces(numberOfWhiteSpaces));

					int beginOfCurrentToken = docText.length();
					docText.append(currentTokenText);

					endOfCurrentToken = docText.length();

					if (!currentTokenIsPMID && provideGoldstandardTokenization) {
						generateNewTokenAnnotation(beginOfCurrentToken, endOfCurrentToken, jcas);
					}

					if (columns.length <= 5 ) {
						if (currentTokenType.startsWith(B_TYPE)) {
							numberOfTokensInEntity++;
							beginOfCurrentEntity = beginOfCurrentToken;
							if(nextLine == null || !nextLine.matches(".*\\t\\" + I_TYPE + ".*")) {
								generateNewNamedEntityAnnotation(beginOfCurrentEntity, endOfCurrentToken, currentTokenType, jcas);
							}
						} else if (currentTokenType.startsWith(I_TYPE)) {
							numberOfTokensInEntity++;
							if(nextLine == null || !nextLine.matches(".*\\t\\" + I_TYPE + ".*")) {
								generateNewNamedEntityAnnotation(beginOfCurrentEntity, endOfCurrentToken, currentTokenType, jcas);
							}
						} 
					} else {
						System.err.println("Wrong corpus format: " + currentLine);
					}
				}
				//				ignoreNextLine = false;
				currentTokenIsPMID = false;
			} else {
				//				docText += "\n";
			}
			currentLine = nextLine;
		}
		if (beginOfCurrentAbstract != endOfCurrentToken) {
			PubmedDocument abstractAnnotation = new PubmedDocument(jcas);
			abstractAnnotation.setBegin(beginOfCurrentAbstract);
			abstractAnnotation.setEnd(endOfCurrentToken);
			abstractAnnotation.setPmid(currentPMID);
			abstractAnnotation.addToIndexes(jcas);					
		}
		jcas.setDocumentText(docText.toString());
	}

	private static String insertNWhitespaces(int n) {
		String whitespaces = "";
		for (int i = 0; i < n; i++) {
			whitespaces += " ";
		}
		return whitespaces;
	}

	private void generateNewTokenAnnotation(int begin,
			int end, JCas jcas) {
		Token token = new Token(jcas);
		token.setBegin(begin);
		token.setEnd(end);
		token.addToIndexes();
	}

	private void generateNewNamedEntityAnnotation(int begin,
			int end, String type, JCas jcas) {
		String typeString = type.split("-")[1];
		if (typeCounts.containsKey(typeString)) {
			typeCounts.put(typeString, typeCounts.get(typeString)+1);
		} else {
			typeCounts.put(typeString, 1);
		}

		//		System.out.println(typeString + "\t" + docText.substring(begin, end));


		if (useGoldstandardAnnotations && (goldstandardTypeSuffix.isEmpty() || type.endsWith(goldstandardTypeSuffix)) && 
				//filter Modifiers
				!type.endsWith("MODIFIER")
				//				&& (type.endsWith("IUPAC") && !type.endsWith("PARTIUPAC")) 
				//				//filter Part-IUPAC
				//				&& !type.endsWith("PARTIUPAC")
				//&& !type.endsWith("TRIVIALVAR")
				) {
			numberOfEntities++;
			NamedEntity namedEntity = new NamedEntity(jcas);
			namedEntity.setBegin(begin);
			namedEntity.setEnd(end);
			namedEntity.setEntityType(ENTITY_TYPE);
			namedEntity.setConfidence(1.0);
			namedEntity.setSource(Constants.GOLDSTANDARD);
			namedEntity.addToIndexes();
			if (end - begin < 3) {
				numberOfShortEntities++;
			}
		}
	}
}