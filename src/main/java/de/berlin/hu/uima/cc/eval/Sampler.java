package de.berlin.hu.uima.cc.eval;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.u_compare.shared.semantic.NamedEntity;
import org.u_compare.shared.syntactic.Sentence;
import org.uimafit.util.JCasUtil;

//import de.berlin.hu.util.Evaluator;
import de.berlin.hu.wbi.common.research.Evaluator;


public class Sampler  extends CasConsumer_ImplBase{
	Map<NamedEntity, String> annotations = new HashMap<NamedEntity, String>();
	
	@Override
	public void initialize() throws ResourceInitializationException {
		super.initialize();
	}
	
	public void processCas(CAS aCAS) throws ResourceProcessException {
		
		JCas aJCas;
		try {
			aJCas = aCAS.getJCas();
		} catch (CASException e) {
			throw new ResourceProcessException(e);
		}
		
		FSIndex<Annotation> namedEntityIndex = aJCas.getAnnotationIndex(NamedEntity.type);
		Iterator<Annotation> namedEntityIterator = namedEntityIndex.iterator();
		
		while (namedEntityIterator.hasNext()) {
			NamedEntity namedEntity = (NamedEntity) namedEntityIterator.next();
			if ("goldstandard".equals(namedEntity.getSource())) {
				//do nothing
			} else {
				annotations.put(namedEntity, getSampleText(namedEntity, 50));
			}
		}
	}

	private String getSampleText(NamedEntity sample, int windowsize) {
		try {
			JCas jCas = sample.getCAS().getJCas();
			int begin = sample.getBegin();
			int end = sample.getEnd();
			
			Iterator<Sentence> sentenceIterator =  JCasUtil.iterator(jCas, Sentence.class);
			Sentence sentence = null;
			while (sentenceIterator.hasNext()) {
				sentence = sentenceIterator.next();
				if (sentence.getBegin() <= begin && end <= sentence.getEnd()) {
					break;
				}
			}
			
			StringBuilder sb = new StringBuilder();
			Iterator<NamedEntity> entityIterator = JCasUtil.iterator(sentence, NamedEntity.class, true, true);
			int lastEnd = Math.max(begin - windowsize, sentence.getBegin());
			while (entityIterator.hasNext()) {
				NamedEntity entity = entityIterator.next();
				int entityBegin = entity.getBegin();
				int entityEnd = entity.getEnd();

				if ((entityBegin > begin - windowsize) && (entityEnd < end + windowsize)) {
						sb.append(jCas.getDocumentText().subSequence(lastEnd, entityBegin));
						if ((begin == entity.getBegin()) && (end == entity.getEnd())) {
							sb.append("***" + entity.getCoveredText() + "***");
						} else {
							sb.append("*" + entity.getCoveredText() + "*");
						}
						lastEnd = entity.getEnd();
				}
			}

			sb.append(jCas.getDocumentText().subSequence(lastEnd, Math.min(end + windowsize, sentence.getEnd())));
			sb.append("\n");
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public void destroy() {
		List<NamedEntity> sampleAnnotations = new ArrayList<NamedEntity>(annotations.keySet());
		Collections.shuffle(sampleAnnotations);
		sampleAnnotations = sampleAnnotations.subList(0, Math.min(100,sampleAnnotations.size()));
		
		try {
			int i = 1;
			File file = new File("./sampledAnnotations.txt");
				file.createNewFile();
			FileWriter writer = new FileWriter(file);
			
			writer.write("Sampled Annotations:\n\n");
			for (NamedEntity sample : sampleAnnotations) {
				writer.write(i + "\t" + annotations.get(sample));
				i++;
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
	