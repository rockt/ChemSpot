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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class SeparateEvaluation  extends CasConsumer_ImplBase{
	
	
	private int bothTP = 0;
	private int dictTP = 0;
	private int dictFP = 0;
	private int crfTP = 0;
	private int crfFP = 0;
	private int FN = 0;
	
	private int GA = 0;
	private int PA = 0;
	private int dictA = 0;
	private int crfA = 0;
	private int dictFN = 0;
	private int crfFN = 0;

	@Override
	public void initialize() throws ResourceInitializationException {
		super.initialize();
	}
	
	public void processCas(CAS aCAS) throws ResourceProcessException {
		List<NamedEntity> goldAnnotations = new ArrayList<NamedEntity>();
		List<NamedEntity> dictAnnotations = new ArrayList<NamedEntity>();
		List<NamedEntity> crfAnnotations = new ArrayList<NamedEntity>();
		
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
				goldAnnotations.add(namedEntity);
				GA++;
			} else {
				if ("linnaeus".equals(namedEntity.getSource())) {
					dictAnnotations.add(namedEntity);
					PA++;
					dictA++;
				} else if ("banner".equals(namedEntity.getSource())) {
					PA++;
					crfA++;
					crfAnnotations.add(namedEntity);
				} else {
					System.err.println("Annotation without an origin: " + namedEntity.getCoveredText());
					throw new ResourceProcessException();
				}
			}
		}

		//TODO evaluate in the destroy method, add to every ComparableAnnotation a aJCas ID
		evaluate(goldAnnotations, dictAnnotations, crfAnnotations, 0);
	}

	public void evaluate(List<NamedEntity> goldAnnotations, List<NamedEntity> dictAnnotations, List<NamedEntity> crfAnnotations, int offset) {
			List<ComparableAnnotation> goldAnnoationsComparable = new ArrayList<ComparableAnnotation>();
			List<ComparableAnnotation> dictAnnotationsComparable = new ArrayList<ComparableAnnotation>();
			List<ComparableAnnotation> crfAnnotationsComparable = new ArrayList<ComparableAnnotation>();
			
			for (NamedEntity namedEntity : goldAnnotations) {
				goldAnnoationsComparable.add(ComparableAnnotation.createInstance(namedEntity.getBegin(), namedEntity.getEnd(), namedEntity.getCoveredText(), offset));
			}
			for (NamedEntity namedEntity : dictAnnotations) {
				dictAnnotationsComparable.add(ComparableAnnotation.createInstance(namedEntity.getBegin(), namedEntity.getEnd(), namedEntity.getCoveredText(), offset));
			}
			for (NamedEntity namedEntity : crfAnnotations) {
				crfAnnotationsComparable.add(ComparableAnnotation.createInstance(namedEntity.getBegin(), namedEntity.getEnd(), namedEntity.getCoveredText(), offset));
			}
			
			
//			Evaluator<ComparableAnnotation,ComparableAnnotation> evaluator = new Evaluator<ComparableAnnotation,ComparableAnnotation>(dictAnnotationsComparable, crfAnnotationsComparable);
//			evaluator.evaluate();
//			
//			Collection<ComparableAnnotation> both = evaluator.getTruePositives();
//			Collection<ComparableAnnotation> dictWithoutCrf = evaluator.getFalsePositives();
//			Collection<ComparableAnnotation> crfWithoutDict = evaluator.getFalseNegatives();
			
			if (dictAnnotationsComparable.size() > 0 && crfAnnotationsComparable.size() > 0) {
				Evaluator<ComparableAnnotation,ComparableAnnotation> dictEvaluator = new Evaluator<ComparableAnnotation,ComparableAnnotation>(dictAnnotationsComparable, goldAnnoationsComparable);
				dictEvaluator.evaluate();
				dictTP += dictEvaluator.getTruePositives().size();
				dictFN += dictEvaluator.getFalseNegatives().size();
				
				Evaluator<ComparableAnnotation,ComparableAnnotation> crfEvaluator = new Evaluator<ComparableAnnotation,ComparableAnnotation>(crfAnnotationsComparable, goldAnnoationsComparable);
				crfEvaluator.evaluate();
				crfTP += crfEvaluator.getTruePositives().size();
				crfFN += crfEvaluator.getFalseNegatives().size();
				
				if (crfTP > 0 && dictTP > 0) {
					Evaluator<ComparableAnnotation,ComparableAnnotation> evaluator = new Evaluator<ComparableAnnotation,ComparableAnnotation>(crfEvaluator.getTruePositives(), dictEvaluator.getTruePositives());
					evaluator.evaluate();
					bothTP += evaluator.getTruePositives().size();
				} else {
					bothTP += 0;
				}
			} else if (dictAnnotationsComparable.size() > 0 && crfAnnotationsComparable.size() == 0) {
				System.out.println(dictAnnotationsComparable.size() + " " + goldAnnoationsComparable.size());
				Evaluator<ComparableAnnotation,ComparableAnnotation> dictEvaluator = new Evaluator<ComparableAnnotation,ComparableAnnotation>(dictAnnotationsComparable, goldAnnoationsComparable);
				dictEvaluator.evaluate();
				dictTP += dictEvaluator.getTruePositives().size();
				dictFN += dictEvaluator.getFalseNegatives().size();
				
				crfTP += 0;
				crfFN += goldAnnoationsComparable.size();
				bothTP += 0;
			} else if (dictAnnotationsComparable.size() == 0 && crfAnnotationsComparable.size() > 0) {
				Evaluator<ComparableAnnotation,ComparableAnnotation> crfEvaluator = new Evaluator<ComparableAnnotation,ComparableAnnotation>(crfAnnotationsComparable, goldAnnoationsComparable);
				crfEvaluator.evaluate();
				crfTP += crfEvaluator.getTruePositives().size();
				crfFN += crfEvaluator.getFalseNegatives().size();
				
				dictTP += 0;
				dictFN += goldAnnoationsComparable.size();
				bothTP += 0;
			} else {
				crfTP += 0;
				crfFN += goldAnnoationsComparable.size();
				dictTP += 0;
				dictFN += goldAnnoationsComparable.size();
				bothTP += 0;
			}
	}
	
	@Override
	public void destroy() {
		System.out.println("#goldstandard:\t" + GA);
		System.out.println("#pipeline:\t" + PA);
		System.out.println("#dict:\t\t" + dictA);
		System.out.println("#crf:\t\t" + crfA);
		System.out.println();
		System.out.println("TP\tCRF\t" + crfTP);
		System.out.println("FN\tCRF\t" + crfFN);
		System.out.println("R\tCRF\t" + (double)crfTP/((double)crfTP + crfFN));


		System.out.println("TP\tDICT\t" + dictTP);
		System.out.println("FN\tDICT\t" + dictFN);
		System.out.println("R\tDICT\t" + (double)dictTP/((double)dictTP + dictFN));
		
		System.out.println("TP\tBOTH\t" + bothTP);
		System.out.println();
		
	}
	
}
	