package de.berlin.hu.uima.ae.feature;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.u_compare.shared.semantic.NamedEntity;
import org.u_compare.shared.syntactic.Token;
import org.uimafit.util.JCasUtil;

import de.berlin.hu.chemspot.Mention;
import de.berlin.hu.util.Constants;

public class FeatureTokenGenerator {
	public static enum Feature_Phase {
		PHASE1, // after all tagger components ran
		PHASE2, // after match expansion
		PHASE3, // after stopword filtering
		PHASE4  // after normalization
	};
	
	public static enum ChemSpot_Feature {
		CRF,
		DICTIONARY,
		SUM_TAGGER,
		ABBREV,
		CHEMSPOT,
		MATCH_EXPANSION,
		CRF_ME,
		DICTIONARY_ME,
		SUM_TAGGER_ME,
		ABBREV_ME,
		STOPWORD,
		CHID,
		CHEB,
		CAS, PUBC,
		PUBS,
		INCH,
		DRUG,
		HMBD,
		KEGG,
		KEGD,
		MESH
	};
		
	private static final ChemSpot_Feature[] DICTIONARY_FEATURES = {
		ChemSpot_Feature.CHID,
		ChemSpot_Feature.CHEB,
		ChemSpot_Feature.CAS,
		ChemSpot_Feature.PUBC,
		ChemSpot_Feature.PUBS,
		ChemSpot_Feature.INCH,
		ChemSpot_Feature.DRUG,
		ChemSpot_Feature.HMBD,
		ChemSpot_Feature.KEGG,
		ChemSpot_Feature.KEGD,
		ChemSpot_Feature.MESH
	};
	private List<FeatureToken> tokens = null;
	
	public FeatureTokenGenerator() {
		tokens = new ArrayList<FeatureToken>();
	}
	
	public void process(JCas aJCas, Feature_Phase phase) throws AnalysisEngineProcessException {
		switch (phase) {
		case PHASE1:
			tokens.clear();
			generateFeatureTokens(aJCas);
			break;
		case PHASE2:
			checkExpandedMentions(aJCas);
			break;
		case PHASE3:
			checkStopwords(aJCas);
			break;
		case PHASE4:
			checkNormalization(aJCas);
			break;
		}
	}
	
	private void generateFeatureTokens(JCas aJCas) {
		for (Token token : JCasUtil.iterate(aJCas, Token.class)) {
			FeatureToken ft = new FeatureToken(aJCas, token.getBegin(), token.getEnd());
			tokens.add(ft);
		}
		
		for (NamedEntity ne : JCasUtil.iterate(aJCas, NamedEntity.class)) {
			if (Constants.GOLDSTANDARD.equals(ne.getSource())) continue;
			
			for (FeatureToken token : getFeatureTokens(aJCas, ne)) {
				try {
					token.addFeature(ChemSpot_Feature.valueOf(ne.getSource().toUpperCase()));
				} catch (IllegalArgumentException e) {
					// do nothing
				}
			}
		}
	}
	
	public List<FeatureToken> getFeatureTokens(JCas aJCas) {
		return new ArrayList<FeatureToken>(tokens);
	}
	
	public List<FeatureToken> getFeatureTokens(JCas aJCas, Annotation container) {
		List<FeatureToken> result = new ArrayList<FeatureToken>();
		
		for (FeatureToken token : getFeatureTokens(aJCas)) {
			if (token.getBegin() > container.getEnd()) break;
			
			if (token.getBegin() >= container.getBegin() && token.getEnd() <= container.getEnd()) {
				result.add(token);
			}
		}
		
		return result;
	}
	
	private void checkExpandedMentions(JCas aJCas) {
		for (NamedEntity ne : JCasUtil.iterate(aJCas, NamedEntity.class)) {
			for (FeatureToken token : getFeatureTokens(aJCas, ne)) {
				if (Constants.GOLDSTANDARD.equals(ne.getSource())) continue;
				
				try {
					ChemSpot_Feature feature = ChemSpot_Feature.valueOf(ne.getSource().toUpperCase());
					if (!token.hasFeature(feature)) {
						token.addFeature(ChemSpot_Feature.valueOf(feature + "_ME"));
						token.addFeature(ChemSpot_Feature.MATCH_EXPANSION);
					}
				} catch (IllegalArgumentException e) {
					// do nothing
				}
			}
		}
	}
	
	private void checkStopwords(JCas aJCas) {
		List<FeatureToken> tokens = getFeatureTokens(aJCas);
		
		for (NamedEntity ne : JCasUtil.iterate(aJCas, NamedEntity.class)) {
			if (Constants.GOLDSTANDARD.equals(ne.getSource())) continue;
			
			for (FeatureToken token : getFeatureTokens(aJCas, ne)) {
				tokens.remove(token);
			}
		}
		
		for (FeatureToken token : tokens) {
			if (!token.getFeatures().isEmpty()) {
				token.addFeature(ChemSpot_Feature.STOPWORD);
			}
		}
	}
	
	private void checkNormalization(JCas aJCas) {
		for (NamedEntity ne : JCasUtil.iterate(aJCas, NamedEntity.class)) {
			if (Constants.GOLDSTANDARD.equals(ne.getSource())) continue;
			Mention mention = new Mention(ne);
			String[] ids = mention.getIds();
			
			for (FeatureToken token : getFeatureTokens(aJCas, ne)) {
				for (int i = 0; i < ids.length; i++) {
					if (ids[i] != null && !ids[i].isEmpty()) {
						token.addFeature(DICTIONARY_FEATURES[i]);
					}
				}
			}
		}
		
		printFeatureTokens(aJCas);
	}
	
	public void printFeatureTokens(JCas aJCas) {
		List<NamedEntity> nes = new ArrayList<NamedEntity>(JCasUtil.select(aJCas, NamedEntity.class));
			
		for (NamedEntity ne : new ArrayList<NamedEntity>(nes)) {
			if (Constants.GOLDSTANDARD.equals(ne.getSource())) nes.remove(ne);
		}
		
		int lastEnd = 0;
		for (FeatureToken token : getFeatureTokens(aJCas)) {
			
			while (!nes.isEmpty() && nes.get(0).getEnd() < token.getBegin()) {
				nes.remove(0);
			}
			
			if (!nes.isEmpty() && nes.get(0).getBegin() <= token.getBegin() && nes.get(0).getEnd() >= token.getEnd()) {
				NamedEntity ne = nes.remove(0);
				System.out.println(ne.getCoveredText());
			}
			
			if (!token.getFeatures().isEmpty()) {
				if (lastEnd != token.getBegin()) {
					System.out.println();
				}
				System.out.println("  " + token.getCoveredText() + " -> " + token.getFeatures());
			}
			
			lastEnd = token.getEnd();
		}
	}
}
