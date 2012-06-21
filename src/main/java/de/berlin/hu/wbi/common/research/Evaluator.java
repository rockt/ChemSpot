package de.berlin.hu.wbi.common.research;

import java.io.Serializable;
import java.util.*;

/**
 * FIXME: translate to english
 * Generische Klasse zum Berechnen von Precision, Recall und anderen Massen,
 * die als Eingabe einen Goldstandard und ein Ergebnis, welches mit diesem
 * verglichen werden soll, erwartet.
 * 
 * @author arzt
 *
 * @param <ResultType> Typ eines zu vergleichenden Elementes
 */
public class Evaluator<ResultType, StandardType> implements Serializable {
	private static final long serialVersionUID = 3009808474569044273L;
	private ArrayList<ResultType> truePositives;
	private ArrayList<ResultType> falsePositives;
	private ArrayList<StandardType> falseNegatives;
	
	private transient Set<? extends ResultType> result;
	private transient Set<? extends StandardType> standard;
	
	private double precision;
	private double recall;
	private double fMeasure;
	
	public Evaluator() {
		super();
		this.precision = Double.NaN;
		this.recall = Double.NaN;
		this.fMeasure = Double.NaN;
	}

	public void trim() {
		if (truePositives != null) truePositives.trimToSize();
		if (falseNegatives != null) falseNegatives.trimToSize();
		if (falsePositives != null) falsePositives.trimToSize();
		result = null;
		standard = null;
	}
	
	/**
	 * @param result predictions
	 * @param standard goldstandard
	 */
	public Evaluator(Collection<? extends ResultType> result, Collection<? extends StandardType> standard) {
		this();
		setResultPositives(result);
		setStandardPositives(standard);
	}
	
	public Evaluator<ResultType, StandardType> setResultPositives(Collection<? extends ResultType> result) {
		assert this.result == null : "Do not overwrite the result collection!";
		assert result != null : "Your result collection is null!";
		assert result.size() > 0 : "Your result collection is empty!";
		this.result = new HashSet<ResultType>(result);
		return this;
	}
	
	public Evaluator<ResultType, StandardType> setStandardPositives(Collection<? extends StandardType> standard) {
		assert this.standard == null : "Do not overwrite the standard collection!";
		assert standard != null : "Your standard collection is null!";
		assert standard.size() > 0 : "Your standard collection is empty!";
		this.standard = new HashSet<StandardType>(standard);
		return this;
	}
	
	public Evaluator<ResultType, StandardType> evaluate() {
		assert result != null : "You forgot to set the result collection!";
		assert standard != null : "You forgot to set the standard collection!";
		
		this.truePositives = new ArrayList<ResultType>(result.size());
		this.falsePositives = new ArrayList<ResultType>(result.size());
		this.falseNegatives = new ArrayList<StandardType>(standard.size());
		//Compute TP and FP
		for (ResultType resultSample : result) {
			boolean contains = standard.contains(resultSample);
			if (contains) {
				truePositives.add(resultSample);
			} else {
				falsePositives.add(resultSample);
			}
		}
		//Compute FN
		for (StandardType standardSample : standard) {
			if (!result.contains(standardSample)) {
				falseNegatives.add(standardSample);
			}
		}
		return this;
	}

	private void computeFMeasure() {
		double p = getPrecision();
		double r = getRecall();
		double fM = EvalMeasures.getFMeasure(p, r);
		fMeasure = fM;
	}
	
	private void computeRecall() {
		assert truePositives != null && falseNegatives != null : "You forgot to call evaluate() first!";
		int tp = truePositives.size();
		int fn = falseNegatives.size();
		double r = EvalMeasures.getRecall(tp, fn);
		recall = r;
	}

	private void computePrecision() {
		assert truePositives != null && falsePositives != null : "You forgot to call evaluate() first!";
		int tp = truePositives.size();
		int fp = falsePositives.size();
		precision = EvalMeasures.getPrecision(tp, fp);
	}

	public double getPrecision() {
		if (Double.isNaN(precision)) computePrecision();
		return precision;
	}


	public double getRecall() {
		if (Double.isNaN(recall)) computeRecall();
		return recall;
	}

	public double getFMeasure() {
		if (Double.isNaN(fMeasure)) computeFMeasure();
		return fMeasure;
	}

	public Collection<ResultType> getTruePositives() {
		if (truePositives == null) 	evaluate();
		return Collections.unmodifiableCollection(truePositives);
	}

	public Collection<ResultType> getFalsePositives() {
		if (falsePositives == null) evaluate();
		return Collections.unmodifiableCollection(falsePositives);
	}

	public Collection<StandardType> getFalseNegatives() {
		if (falseNegatives == null) evaluate();
		return Collections.unmodifiableCollection(falseNegatives);
	}
	
	public static <R, S> Evaluator<R, S> create(Collection<R> result, Collection<S> standard) {
		return new Evaluator<R, S>(result, standard);
	}
	
	public static <R, S> Evaluator<R, S> create() {
		return new Evaluator<R, S>();
	}
	
	public static <T> double getPrecision(Collection<T> result, Collection<T> standard) {
		return Evaluator.create(result, standard).getPrecision();
	}
	
	public static <T extends Comparable<? super T>> double getRecall(Collection<T> result, Collection<T> standard) {
		return Evaluator.create(result, standard).getRecall();
	}
	
	public static <T> double getFMeasur(Collection<T> result, Collection<T> standard) {
		return Evaluator.create(result, standard).getFMeasure();
	}
	
	public static <T> Collection<T> getTruePositives(Collection<T> result, Collection<T> standard) {
		return Evaluator.create(result, standard).getTruePositives();
	}
	
	public static <T> Collection<T> getFalsePositives(Collection<T> result, Collection<T> standard) {
		return Evaluator.create(result, standard).getFalsePositives();
	}
	
	public static <T> Collection<T> getFalseNagatives(Collection<T> result, Collection<T> standard) {
		return Evaluator.create(result, standard).getFalsePositives();
	} 
	
	public int getNumberOfTP() {
		return truePositives.size();
	}
	
	public int getNumberOfFP() {
		return falsePositives.size();
	}
	
	public int getNumberOfFN() {
		return falseNegatives.size();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Evaluator [truePositives=");
		builder.append(truePositives.size());
		builder.append(", falsePositives=");
		builder.append(falsePositives.size());
		builder.append(", falseNegatives=");
		builder.append(falseNegatives.size());
		builder.append("]");
		return builder.toString();
	}
}