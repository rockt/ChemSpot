package de.berlin.hu.uima.ae.feature;

import java.util.HashSet;
import java.util.Set;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import de.berlin.hu.uima.ae.feature.FeatureTokenGenerator.ChemSpot_Feature;

public class FeatureToken extends Annotation {
	private Set<ChemSpot_Feature> features = null;
	
	public FeatureToken(JCas aJCas) {
		super(aJCas);
		intialize();
	}
	
	public FeatureToken(JCas aJCas, int begin, int end) {
		super(aJCas, begin, end);
		intialize();
	}
	
	private void intialize() {
		features = new HashSet<ChemSpot_Feature>();
	}

	public Set<ChemSpot_Feature> getFeatures() {
		return features;
	}
	
	public boolean hasFeature(ChemSpot_Feature feature) {
		return features.contains(feature);
	}
	
	public void addFeature(ChemSpot_Feature feature) {
		features.add(feature);
	}
	
	public void removeFeature(ChemSpot_Feature feature) {
		features.remove(feature);
	}
	
	public void removeAllFeatures() {
		features.clear();
	}
}
