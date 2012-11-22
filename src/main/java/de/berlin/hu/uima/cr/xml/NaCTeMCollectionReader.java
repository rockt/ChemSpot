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

public class NaCTeMCollectionReader extends XMLCollectionReader {

	@Override
	protected List<File> getfiles(String inputDir) {
		List<File> result = new ArrayList<File>();
		
		File dir = new File(inputDir);
		for (File file : dir.listFiles()) {
			if (file.isFile() && file.getName().endsWith(".xml")) {
				result.add(file);
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

		String pmid = document.getElementsByTagName("PMID").item(0).getTextContent();
		Node titleNode = document.getElementsByTagName("ArticleTitle").item(0);
		String title = titleNode != null ? document.getElementsByTagName("ArticleTitle").item(0).getTextContent() : "";
		Node abstractNode = document.getElementsByTagName("AbstractText").item(0);
		String abstr = abstractNode != null ? abstractNode.getTextContent() : "";
		String text = title + "\n\n" + abstr;
		jcas.setDocumentText(text);
		
		SourceDocumentInformation srcDocInfo = new SourceDocumentInformation(jcas);
		srcDocInfo.setUri(document.getDocumentURI().toString());
		srcDocInfo.setOffsetInSource(0);
		srcDocInfo.setDocumentSize(text.length());
		srcDocInfo.setLastSegment(hasNext());
		srcDocInfo.addToIndexes();
		
		PubmedDocument abstractAnnotation = new PubmedDocument(jcas);
		abstractAnnotation.setBegin(0);
		abstractAnnotation.setEnd(text.length());
		abstractAnnotation.setPmid(pmid);
		abstractAnnotation.addToIndexes(jcas);	
		
		List<Node> nodes = new ArrayList<Node>();
		if (titleNode != null) {
			NodeList titleNodes = titleNode.getChildNodes();
			for (int i = 0; i < titleNodes.getLength(); i++) {
				nodes.add(titleNodes.item(i));
			}
		}
		if (abstractNode != null) {
			NodeList abstractNodes = abstractNode.getChildNodes();
			for (int i = 0; i < abstractNodes.getLength(); i++) {
				nodes.add(abstractNodes.item(i));
			}
		}
		
		int offset = 0;
		for (Node node : nodes) {
			if (("METABOLITE".equals(node.getNodeName()) || "ENZYME".equals(node.getNodeName())) && !node.getTextContent().trim().isEmpty()) {
				String chemical = node.getTextContent();
				Matcher matcher = Pattern.compile(Pattern.quote(chemical)).matcher(text.substring(offset));
				
				if (matcher.find()) {
					int begin = matcher.start();
					int end = matcher.end();
					
					NamedEntity namedEntity = new NamedEntity(jcas);
					namedEntity.setBegin(offset+begin);
					namedEntity.setEnd(offset+end);
					namedEntity.setConfidence(1.0);
					namedEntity.setSource(Constants.GOLDSTANDARD);
					namedEntity.addToIndexes();
					
					offset = end+1;
				}
			}
		}
		
		/*Pattern chemicalPattern = Pattern.compile("<ENZYME>[^<]*((?:(?:</?METABOLITE>)?[^<]+)+)</ENZYME>|<METABOLITE>([^<]+)</METABOLITE>");
		
		Matcher matcher = chemicalPattern.matcher(text);
		while (matcher.find()) {
			String match = null;
			match = matcher.group(1) != null ? matcher.group(1) : matcher.group(2);
			int begin = matcher.start();
			int end = matcher.end();
			
			match = match.replaceAll("<[^>]>", "");
			
			text = text.substring(0, begin) + match + text.substring(end);
			matcher = chemicalPattern.matcher(text);
		}*/
	}
}
