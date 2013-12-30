package de.berlin.hu.uima.cr.chemdner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.u_compare.shared.semantic.NamedEntity;

import de.berlin.hu.chemspot.Mention;
import de.berlin.hu.types.PubmedDocument;
import de.berlin.hu.util.Constants;
import de.berlin.hu.util.Constants.ChemicalType;

public class CHEMDNERReader  extends CollectionReader_ImplBase {
	public static final String PARAM_INPUTDIR = "InputDirectory";
	
	private File inputDirectory = null;
	private File documentsFile = null;
	private File annotationsFile = null;
	private BufferedReader reader = null;
	private String inputLine = null;
	private Map<String, List<Mention>> docIdToAnnotations = null;
	private String abstractId= null;
	private String abstractText = null;
	
	@Override
	public void initialize() throws ResourceInitializationException {
		inputDirectory = new File((String)getConfigParameterValue(PARAM_INPUTDIR));
		documentsFile = new File (inputDirectory.getAbsolutePath() + "/chemdner_abstracts.txt");
		annotationsFile = new File (inputDirectory.getAbsolutePath() + "/chemdner_annotations.txt");
		
		try {
			if (annotationsFile.exists()) {
				reader = new BufferedReader(new FileReader(annotationsFile));
			}
		} catch (FileNotFoundException e) {
			throw new ResourceInitializationException(String.format("annotations file '%s' could not be found", annotationsFile.getAbsolutePath()), null, e);
		}
		
		String line = null;
		docIdToAnnotations = new HashMap<String, List<Mention>>();
		try {
			while (reader != null && (line = reader.readLine()) != null) {
				String[] data = line.split("\t");
				String docId = data[0];
				String section = data[1];
				int begin = Integer.parseInt(data[2]);
				int end = Integer.parseInt(data[3]);
				String text = data[4];
				String type = data[5];
				
				Mention mention = new Mention(begin, end, text);
				mention.setType(ChemicalType.fromString(type));
				mention.setSource(Constants.GOLDSTANDARD);
				
				String key = docId + ":" + section;
				if (!docIdToAnnotations.containsKey(key)) {
					docIdToAnnotations.put(key, new ArrayList<Mention>());
				}
				
				docIdToAnnotations.get(key).add(mention);
			}
		} catch (IOException e) {
			throw new ResourceInitializationException(String.format("could not read annotations file '%s'", annotationsFile.getAbsolutePath()), null, e);
		}
		
		try {
			reader = new BufferedReader(new FileReader(documentsFile));
		} catch (FileNotFoundException e) {
			throw new ResourceInitializationException(String.format("documents file '%s' could not be found", documentsFile.getAbsolutePath()), null, e);
		}
	}

	@Override
	public void getNext(CAS aCAS) throws IOException, CollectionException {
		JCas jcas;
		try {
			jcas = aCAS.getJCas();
		} catch (CASException e) {
			throw new CollectionException(e);
		}
		
		String id = null;
		String text = null;
		String key = null;
		
		if (abstractText != null) {
			id = abstractId;
			text = abstractText;
			key = id + ":A";
			
			abstractId = null;
			abstractText = null;
		} else {
			String[] data = inputLine.split("\t");
			
			id = data[0];
			text = data[1];
			key = id + ":T";
			
			abstractId = id;
			abstractText = data[2];
		}
			
		jcas.setDocumentText(text);
		
		PubmedDocument pubmedDoc = new PubmedDocument(jcas);
		pubmedDoc.setBegin(0);
		pubmedDoc.setEnd(text.length());
		pubmedDoc.setPmid(key);
		pubmedDoc.addToIndexes(jcas);
		
		if (docIdToAnnotations.containsKey(key)) {
			for (Mention mention : docIdToAnnotations.get(key)) {
				NamedEntity entity = new NamedEntity(jcas);
				entity.setBegin(mention.getStart());
				entity.setEnd(mention.getEnd());
				entity.setEntityType(mention.getType().toString());
				entity.setSource(Constants.GOLDSTANDARD);
				entity.setEntityType(mention.getType().toString());
				entity.addToIndexes();
				//System.out.println(entity.getCoveredText());
			}
		}
	}

	@Override
	public boolean hasNext() throws IOException, CollectionException {
		return abstractText != null || (inputLine = reader.readLine()) != null && !inputLine.isEmpty();
	}

	@Override
	public Progress[] getProgress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
