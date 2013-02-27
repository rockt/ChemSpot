package de.berlin.hu.uima.ae.feature;

import java.util.HashSet;
import java.util.Set;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import de.berlin.hu.uima.ae.feature.FeatureTokenGenerator.ChemSpot_Feature;

public class FeatureToken extends Annotation {
	private Set<String> features = null;
	
	public FeatureToken(JCas aJCas) {
		super(aJCas);
		intialize();
	}
	
	public FeatureToken(JCas aJCas, int begin, int end) {
		super(aJCas, begin, end);
		intialize();
	}
	
	private void intialize() {
		features = new HashSet<String>();
	}

	public Set<String> getFeatures() {
		return features;
	}
	
	public boolean hasFeature(String feature) {
		return features.contains(feature);
	}
	
	public boolean hasFeature(ChemSpot_Feature feature) {
		return hasFeature(feature.toString());
	}
	
	public void addFeature(String feature) {
		features.add(feature);
	}
	
	public void addFeature(ChemSpot_Feature feature) {
		features.add(feature.toString());
	}
	
	public void removeFeature(String feature) {
		features.remove(feature);
	}
	
	public void removeFeature(ChemSpot_Feature feature) {
		features.remove(feature);
	}
	
	public void removeAllFeatures() {
		features.clear();
	}
}
