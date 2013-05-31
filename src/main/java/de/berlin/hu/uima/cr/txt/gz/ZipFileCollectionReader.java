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

package de.berlin.hu.uima.cr.txt.gz;

import de.berlin.hu.types.PubmedDocument;
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

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
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

public class ZipFileCollectionReader extends CollectionReader_ImplBase {


	/**
	 * Name of configuration parameter that must be set to the path of a directory containing input
	 * files.
	 */
	public static final String PARAM_INPUTDIR = "InputDirectory";

	/**
	 * Name of configuration parameter that contains the character encoding used by the input files.
	 * If not specified, the default system encoding will be used.
	 */
	//public static final String PARAM_ENCODING = "Encoding";

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

	//private String mEncoding;

	private String mLanguage;

	private Boolean mRecursive;

	private int mCurrentIndex;

	/**
	 * @see org.apache.uima.collection.CollectionReader_ImplBase#initialize()
	 */
	public void initialize() throws ResourceInitializationException {
		File directory = new File(((String) getConfigParameterValue(PARAM_INPUTDIR)).trim());
		//mEncoding  = (String) getConfigParameterValue(PARAM_ENCODING);
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
			if (!files[i].isDirectory() && files[i].getName().endsWith(".txt.gz")) {
				mFiles.add(files[i]);
			} else if (files[i].isDirectory() && mRecursive) {
				addFilesFromDir(files[i]);
			}
		}
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
		
		System.out.println("Reading file: " + file.getAbsolutePath());

		//read zipped file
		String text;   
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						new GZIPInputStream(
								new FileInputStream(file)) ) );

		StringBuffer textBuffer = new StringBuffer();
		Integer currindex = -1;
		while(reader.ready()){
			PubmedDocument pmdoc = new PubmedDocument(jcas);

			String s = reader.readLine();
			//System.out.println(s);

			//split line into pmid and text		
			String[] two = new String[2];
			two = splitFirst(s, "\t");		
			pmdoc.setPmid(two[0]);

			String annot = new String(two[1]);
			//append text
			textBuffer.append(annot + "\n");
			//pmdoc.setBegin(currindex + two[0].length() + 1);
			pmdoc.setBegin(currindex + 1);
			Integer len = annot.length();
			currindex = currindex + len + 1;
			pmdoc.setEnd(currindex);	

			//	    System.out.println(	"pmid: "+two[0] + "\t" + 
			//	    					"[begin/end]:"+ pmdoc.getBegin() + "/" + pmdoc.getEnd() + "\t" +
			//	    					"annot:" + annot
			//	    					);

			//System.out.println(annot.substring(pmdoc.getBegin(), pmdoc.getEnd()));
			pmdoc.addToIndexes();
		}

		text = textBuffer.toString();
		//System.out.println(text);



		//old File to String Method
		//String text = FileUtils.file2String(file, mEncoding);



		// put document in CAS
		jcas.setDocumentText(text);

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
		srcDocInfo.setUri(file.getAbsoluteFile().toURI().toString());
		srcDocInfo.setOffsetInSource(0);
		srcDocInfo.setDocumentSize((int) file.length());
		srcDocInfo.setLastSegment(mCurrentIndex == mFiles.size());
		srcDocInfo.setBegin(0);
		srcDocInfo.setEnd(currindex);
		srcDocInfo.addToIndexes();

	}

	//  private void buildCASFromDocument(JCas jcas, String text) {
	//  
	//  }
	//  





	/**
	 * Split the source into two strings at the first occurrence of the splitter Subsequent occurrences are not treated specially, and may be part of the second string.
	 * 
	 * @param source
	 *        The string to split
	 * @param splitter
	 *        The string that forms the boundary between the two strings returned.
	 * @return An array of two strings split from source by splitter.
	 */
	public static String[] splitFirst(String source, String splitter)
	{
		// hold the results as we find them
		Vector<String> rv = new Vector<String>();
		int last = 0;
		int next = 0;

		// find first splitter in source
		next = source.indexOf(splitter, last);
		if (next != -1)
		{
			// isolate from last thru before next
			rv.add(source.substring(last, next));
			last = next + splitter.length();
		}

		if (last < source.length())
		{
			rv.add(source.substring(last, source.length()));
		}

		// convert to array
		return (String[]) rv.toArray(new String[rv.size()]);
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
