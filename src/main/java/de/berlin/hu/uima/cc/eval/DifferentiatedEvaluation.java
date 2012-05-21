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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class DifferentiatedEvaluation  extends CasConsumer_ImplBase{
	
	
	private int bothTP = 0;
	private int bothFP = 0;
	private int dictTP = 0;
	private int dictFP = 0;
	private int crfTP = 0;
	private int crfFP = 0;
	private int FN = 0;
	
	private int GA = 0;
	private int PA = 0;
	private int dictA = 0;
	private int crfA = 0;

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
					System.out.println("Annotation without an origin: " + namedEntity.getCoveredText());
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
			
			
			Evaluator<ComparableAnnotation,ComparableAnnotation> evaluator = new Evaluator<ComparableAnnotation,ComparableAnnotation>(dictAnnotationsComparable, crfAnnotationsComparable);
			evaluator.evaluate();
			
			Collection<ComparableAnnotation> both = evaluator.getTruePositives();
			Collection<ComparableAnnotation> dictWithoutCrf = evaluator.getFalsePositives();
			Collection<ComparableAnnotation> crfWithoutDict = evaluator.getFalseNegatives();
			
			Evaluator<ComparableAnnotation,ComparableAnnotation> bothEvaluator = new Evaluator<ComparableAnnotation,ComparableAnnotation>(both, goldAnnoationsComparable);
			Evaluator<ComparableAnnotation,ComparableAnnotation> dictEvaluator = new Evaluator<ComparableAnnotation,ComparableAnnotation>(dictWithoutCrf, goldAnnoationsComparable);
			Evaluator<ComparableAnnotation,ComparableAnnotation> crfEvaluator = new Evaluator<ComparableAnnotation,ComparableAnnotation>(crfWithoutDict, goldAnnoationsComparable);
			
			bothEvaluator.evaluate();
			bothTP += bothEvaluator.getTruePositives().size();
			bothFP += bothEvaluator.getFalsePositives().size();
			
			dictEvaluator.evaluate();
			dictTP += dictEvaluator.getTruePositives().size();
			dictFP += dictEvaluator.getFalsePositives().size();
			
			crfEvaluator.evaluate();
			crfTP += crfEvaluator.getTruePositives().size();
			crfFP += crfEvaluator.getFalsePositives().size();
	}
	
	@Override
	public void destroy() {
		System.out.println("#goldstandard:\t" + GA);
		System.out.println("#pipeline:\t" + PA);
		System.out.println("#dict:\t\t" + dictA);
		System.out.println("#crf:\t\t" + crfA);
		System.out.println();
		System.out.println("TP\tBOTH\t" + bothTP);
		System.out.println("TP\tDICT\t" + dictTP);
		System.out.println("TP\tCRF\t" + crfTP);
		System.out.println("FP\tBOTH\t" + bothFP);
		System.out.println("FP\tDICT\t" + dictFP);
		System.out.println("FP\tCRF\t" + crfFP);
		System.out.println();
		
	}
	
}
	