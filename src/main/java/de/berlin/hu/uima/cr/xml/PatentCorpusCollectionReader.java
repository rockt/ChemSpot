package de.berlin.hu.uima.cr.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.u_compare.shared.semantic.NamedEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.berlin.hu.types.PubmedDocument;
import de.berlin.hu.util.Constants;

public class PatentCorpusCollectionReader extends XMLCollectionReader {

	@Override
	protected List<File> getfiles(String inputDir) {
		List<File> result = new ArrayList<File>();
		
		File dir = new File(inputDir);
		for (File subdir : dir.listFiles()) {
			if (subdir.isDirectory()) {
				for (File file : subdir.listFiles()) {
					if (file.isFile() && "scrapbook.xml".equals(file.getName())) {
						result.add(file);
						break;
					}
				}
			}
		}
			
		return result;
	}

	@Override
	public void getNext(CAS aCAS) throws IOException, CollectionException {
		Document document = getNextDocument();
		
		JCas jcas = null;
		try {
			jcas = aCAS.getJCas();
		} catch (CASException e) {
			throw new CollectionException(e);
		}

		NodeList snippetlist = document.getElementsByTagName("snippet");
		List<Node> snippetNodes = new ArrayList<Node>();
		String text = "";
		for (int i = 0; i < snippetlist.getLength(); i++) {
			Node node = snippetlist.item(i);
			text += node.getTextContent().trim() + "\n\n";
			snippetNodes.add(node);
		}
		
		jcas.setDocumentText(text);
		
		SourceDocumentInformation srcDocInfo = new SourceDocumentInformation(jcas);
		srcDocInfo.setUri(document.getDocumentURI().replaceFirst("(/|\\\\)[^/\\\\]+$", ""));
		srcDocInfo.setOffsetInSource(0);
		srcDocInfo.setDocumentSize(text.length());
		srcDocInfo.setLastSegment(hasNext());
		srcDocInfo.addToIndexes();
		
		PubmedDocument abstractAnnotation = new PubmedDocument(jcas);
		abstractAnnotation.setBegin(0);
		abstractAnnotation.setEnd(text.length());
		abstractAnnotation.setPmid("");
		abstractAnnotation.addToIndexes(jcas);	
		
		int offset = 0;
		for (Node snippetNode : snippetNodes) {
			NodeList childNodes = snippetNode.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node node = childNodes.item(i);
				if ("ne".equals(node.getNodeName()) && !node.getTextContent().isEmpty()) {
					String chemical = node.getTextContent();
					Node chebiIdNode = node.getAttributes().getNamedItem("chebi-id");
					if (chebiIdNode == null) continue;
					String chebiID = chebiIdNode.getTextContent().replaceAll("W[^:]+:", "");
					Matcher matcher = Pattern.compile(Pattern.quote(chemical)).matcher(text.substring(offset));
					
					if (matcher.find()) {
						int begin = matcher.start();
						int end = matcher.end();
						
						NamedEntity namedEntity = new NamedEntity(jcas);
						namedEntity.setBegin(offset+begin);
						namedEntity.setEnd(offset+end);
						namedEntity.setConfidence(1.0);
						namedEntity.setId("," + chebiID);
						namedEntity.setSource(Constants.GOLDSTANDARD);
						namedEntity.addToIndexes();
						
						offset = end+1;
					}
				}
			}
		}
	}
}
