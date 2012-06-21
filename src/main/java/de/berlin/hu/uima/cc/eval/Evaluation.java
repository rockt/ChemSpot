package de.berlin.hu.uima.cc.eval;

import de.berlin.hu.util.Constants;
import de.berlin.hu.wbi.common.research.Evaluator;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//import de.berlin.hu.util.Evaluator;


public class Evaluation  extends CasConsumer_ImplBase{
	
	
	private int GA = 0;
	private int PA = 0;
	private int TP = 0;
	private int FP = 0;
	private int FN = 0;
	private int GATest;
	private int PATest;
	private int numberOfSentences;

	@Override
	public void initialize() throws ResourceInitializationException {
		super.initialize();
	}
	
	public void processCas(CAS aCAS) throws ResourceProcessException {
		List<NamedEntity> goldAnnotations = new ArrayList<NamedEntity>();
		List<NamedEntity> pipelineAnnotations = new ArrayList<NamedEntity>();
		
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
			if (Constants.GOLDSTANDARD.equals(namedEntity.getSource())) {
				goldAnnotations.add(namedEntity);
			} else {
				pipelineAnnotations.add(namedEntity);
			}
		}
		
//		FSIndex<Annotation> sentenceIndex = aJCas.getAnnotationIndex(Sentence.type);
//		Iterator<Annotation> sentenceIterator = sentenceIndex.iterator();
//		
//		while (sentenceIterator.hasNext()) {
//			Sentence sentence = (Sentence) sentenceIterator.next();
//			numberOfSentences++;
//		}

		//TODO evaluate in the destroy method, add to every ComparableAnnotation a aJCas ID
		evaluate(goldAnnotations, pipelineAnnotations, 0);
	}

	public void evaluate(List<NamedEntity> goldAnnotations, List<NamedEntity> pipelineAnnotations, int offset) {
			List<ComparableAnnotation> goldAnnoationsComparable = new ArrayList<ComparableAnnotation>();
			List<ComparableAnnotation> pipelineAnnotationsComparable = new ArrayList<ComparableAnnotation>();
			
			GATest += goldAnnotations.size();
			PATest += pipelineAnnotations.size();
			
			for (NamedEntity namedEntity : goldAnnotations) {
				goldAnnoationsComparable.add(ComparableAnnotation.createInstance(namedEntity.getBegin(), namedEntity.getEnd(), namedEntity.getCoveredText(), offset, namedEntity.getCAS()));
			}
			for (NamedEntity namedEntity : pipelineAnnotations) {
				pipelineAnnotationsComparable.add(ComparableAnnotation.createInstance(namedEntity.getBegin(), namedEntity.getEnd(), namedEntity.getCoveredText(), offset, namedEntity.getCAS()));
			}
			
			GA += goldAnnoationsComparable.size();
			PA += pipelineAnnotationsComparable.size();
			
			assert GATest == GA;
			assert PATest == PA;
			
			if (goldAnnoationsComparable.size() == 0) {
				FP += pipelineAnnotationsComparable.size();
			} else if (pipelineAnnotationsComparable.size() == 0) {
				FN += goldAnnoationsComparable.size();
			} else {
				//			Evaluator<ComparableAnnotation> evaluator = new Evaluator<ComparableAnnotation>(pipelineAnnotationsComparable, goldAnnoationsComparable);
				Evaluator<ComparableAnnotation,ComparableAnnotation> evaluator = new Evaluator<ComparableAnnotation,ComparableAnnotation>(pipelineAnnotationsComparable, goldAnnoationsComparable);
				evaluator.evaluate();
				//			evaluator.printResults();

				TP  += evaluator.getTruePositives().size();
				FP  += evaluator.getFalsePositives().size();
				FN += evaluator.getFalseNegatives().size();

//				for (ComparableAnnotation fp : evaluator.getFalsePositives()) {
//					System.out.println("FP\t" + fp.getText());
//				}
//
//				for (ComparableAnnotation fn : evaluator.getFalseNegatives()) {
//					System.out.println("FN\t" + fn.getText());
//				}

				Collection<ComparableAnnotation> FPs = evaluator.getFalsePositives();
				ArrayList<ComparableAnnotation> listFPs = new ArrayList<ComparableAnnotation>(FPs);
				Collections.shuffle(listFPs);
				List<ComparableAnnotation> sampleFPs = listFPs.subList(0, Math.min(50,listFPs.size()));

				Collection<ComparableAnnotation> FNs = evaluator.getFalseNegatives();
				ArrayList<ComparableAnnotation> listFNs = new ArrayList<ComparableAnnotation>(FNs);
				Collections.shuffle(listFNs);
				List<ComparableAnnotation> sampleFNs = listFNs.subList(0, Math.min(50,listFNs.size()));

				try {
					writeToFile(sampleFPs, sampleFNs);
				} catch (CASException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	

	private void writeToFile(List<ComparableAnnotation> sampleFPs,
			List<ComparableAnnotation> sampleFNs) throws CASException, IOException {
		File file = new File("./errorAnalysisFPs.txt");
	    file.createNewFile();
		FileWriter writer = new FileWriter(file);
		
		int windowsize = 50;
		
		writer.write("50 Sampled False Positives:\n\n");
		for (ComparableAnnotation fp : sampleFPs) {
			writer.write(sampleAndPrintErrors(fp, windowsize) + "\n");
		}
		writer.close();
		
		File file2 = new File("./errorAnalysisFNs.txt");
	    file2.createNewFile();
		FileWriter writer2 = new FileWriter(file2);
		
		writer2.write("50 Sampled False Negatives:\n\n");
		for (ComparableAnnotation fn : sampleFNs) {
			writer2.write(sampleAndPrintErrors(fn, windowsize) + "\n");
		}
		writer2.close();
	}

	private String sampleAndPrintErrors(ComparableAnnotation fn, int windowsize)
			throws CASException {
		
		JCas jCas = fn.getCAS().getJCas();
		int begin = fn.getBegin();
		int end = fn.getEnd();
		
		Iterator<Sentence> sentenceIterator =  JCasUtil.iterator(jCas, Sentence.class);
		Sentence sentence = null;
		while (sentenceIterator.hasNext()) {
			 sentence = sentenceIterator.next();
			 if (sentence.getBegin() <= begin && end <= sentence.getEnd()) {
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("TRUE\t");
		
		try {
			Iterator<NamedEntity> entityIterator = JCasUtil.iterator(sentence, NamedEntity.class, true, true);
			int lastEnd = Math.max(begin - windowsize, sentence.getBegin());
			while (entityIterator.hasNext()) {
				NamedEntity entity = entityIterator.next();
				int entityBegin = entity.getBegin();
				int entityEnd = entity.getEnd();

				if ((entityBegin > begin - windowsize) && (entityEnd < end + windowsize)) {
					if (Constants.GOLDSTANDARD.equals(entity.getSource())) {
						sb.append(jCas.getDocumentText().subSequence(lastEnd, entityBegin));
						if ((begin == entity.getBegin()) && (end == entity.getEnd())) {
							sb.append("***" + entity.getCoveredText() + "***");
						} else {
							sb.append("*" + entity.getCoveredText() + "*");
						}

						lastEnd = entity.getEnd();
					}
				}
			}

			sb.append(jCas.getDocumentText().subSequence(lastEnd, Math.min(end + windowsize, sentence.getEnd())));
			sb.append("\n");
			sb.append("FALSE\t");

			Iterator<NamedEntity> entityIterator2 = JCasUtil.iterator(sentence, NamedEntity.class, true, true);
			lastEnd = Math.max(begin - windowsize, sentence.getBegin());
			while (entityIterator2.hasNext()) {
				NamedEntity entity = entityIterator2.next();
				int entityBegin = entity.getBegin();
				int entityEnd = entity.getEnd();

				if ((entityBegin > begin - windowsize) && (entityEnd < end + windowsize)) {
					if (!Constants.GOLDSTANDARD.equals(entity.getSource())) {
						//System.out.println("###From " + lastEnd + " to " + entityBegin);
						sb.append(jCas.getDocumentText().subSequence(lastEnd, entityBegin));
						if ((begin == entity.getBegin()) && (end == entity.getEnd())) {
							sb.append("***" + entity.getCoveredText() + "***");
						} else {
							sb.append("*" + entity.getCoveredText() + "*");
						}

						lastEnd = entity.getEnd();
					}
				}
			}

			sb.append(jCas.getDocumentText().subSequence(lastEnd, Math.min(end + windowsize, sentence.getEnd())));
			sb.append("\n");
		} catch (NullPointerException e) {
			//ignore
		} catch (StringIndexOutOfBoundsException e) {
			//FIXME what happens here?
		}
		return sb.toString();
	}

	@Override
	public void destroy() {
		System.out.println("#Sentences:" + numberOfSentences);
		System.out.println("#Goldstandard Annotations:" + GA + "->" + GATest);
		System.out.println("#Pipeline Annotations:" + PA + "->" + PATest);
		System.out.println("### RESULTS ###");
		System.out.format("TP:\t%d\nFP:\t%d\nFN:\t%d\n", TP, FP, FN);
		System.out.println();
		double precision = (double) TP / ((double) TP + FP);
		double recall = (double) TP / ((double) TP + FN);
		double fscore = 2 * (precision * recall) / (precision + recall);
		System.out.format("precision:\t%f\nrecall:\t%f\nf-score:\t%f\n", precision, recall, fscore);
		System.out.println();
	}
	
}
	