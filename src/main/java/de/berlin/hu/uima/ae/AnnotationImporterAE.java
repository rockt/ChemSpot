/**
 * 
 */
package de.berlin.hu.uima.ae;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.u_compare.shared.semantic.chemical.Chemical;

import java.io.*;
import java.util.Arrays;

/**
 * @author trocktae
 *
 */
public class AnnotationImporterAE extends JCasAnnotator_ImplBase {

	/* (non-Javadoc)
	 * @see org.apache.uima.analysis_component.JCasAnnotator_ImplBase#process(org.apache.uima.jcas.JCas)
	 */
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		String path =  "./metamap_scai.txt";
		File file = new File(path);
		FileReader reader;
		try {
			reader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				String[] splits = line.split("\t");
				System.out.println(Arrays.toString(splits));
				createAnnotation(aJCas, Integer.valueOf(splits[1]).intValue(), Integer.valueOf(splits[2]).intValue());
			}
		} catch (FileNotFoundException e) {
			throw new AnalysisEngineProcessException(e);
		} catch (IOException e) {
			throw new AnalysisEngineProcessException(e);
		}
	}
	
	private void createAnnotation(JCas aJCas, int begin, int end) {
		Chemical annotation = new Chemical(aJCas);
		annotation.setBegin(begin);
		annotation.setEnd(end);
		annotation.setSource("metamap");
		annotation.addToIndexes();
	}
}
