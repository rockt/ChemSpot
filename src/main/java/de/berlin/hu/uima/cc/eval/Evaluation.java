package de.berlin.hu.uima.cc.eval;

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

import java.util.*;

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

				Collection<ComparableAnnotation> FPs = evaluator.getFalsePositives();
				ArrayList<ComparableAnnotation> listFPs = new ArrayList<ComparableAnnotation>(FPs);
				Collections.shuffle(listFPs);
				List<ComparableAnnotation> sampleFPs = listFPs.subList(0, Math.min(50,listFPs.size()));

				Collection<ComparableAnnotation> FNs = evaluator.getFalseNegatives();
				ArrayList<ComparableAnnotation> listFNs = new ArrayList<ComparableAnnotation>(FNs);
				Collections.shuffle(listFNs);
				List<ComparableAnnotation> sampleFNs = listFNs.subList(0, Math.min(50,listFNs.size()));
			}
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
	