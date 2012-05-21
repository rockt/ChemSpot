package de.berlin.hu.wbi.common.research;

public class EvalMeasures {

	public static double getFMeasure(double p, double r) {
		return 2 * (p * r) / (p + r);
	}

	public static double getFMeasure(int tp, int fp, int fn) {
		double p = getPrecision(tp, fp);
		double r = getRecall(tp, fn);
		return getFMeasure(p, r);
	}

	public static double getPrecision(int tp, int fp) {
		if (tp + fp == 0) {
			return 0;
		}
		return 1d*tp/(tp + fp);
	}

	public static double getRecall(int tp, int fn) {
		if (tp + fn == 0) {
			return 0;
		}
		return 1d*tp/(tp + fn);
	}
	
	public static double getSebiMeasure(int tp, int fp, int fn) {
		return 1d*tp/(tp + fn + fp);
	}

}
