package de.berlin.hu.uima.ae.feature;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		CAS,
		PUBC,
		PUBS,
		INCH,
		DRUG,
		HMBD,
		KEGG,
		KEGD,
		MESH,
		CHEB_MIN_DEPTH,
		CHEB_AVG_DEPTH,
		CHEB_MAX_DEPTH,
		CHEB_CHILDREN;
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
	private static Map<String, Integer> chebiMinDepth = null;
	private static Map<String, Integer> chebiAvgDepth = null;
	private static Map<String, Integer> chebiMaxDepth = null;
	private static Map<String, Integer> nrChildNodes = null;
	
	private static void loadChebiData(String file) throws IOException {
		chebiMinDepth = new HashMap<String, Integer>();
		chebiAvgDepth = new HashMap<String, Integer>();
		chebiMaxDepth = new HashMap<String, Integer>();
		nrChildNodes = new HashMap<String, Integer>();
		
		System.out.println("Loading chebi data from file " + file + "...");
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		String line = null;
		reader.readLine();
		while ((line = reader.readLine()) != null) {
			String[] chebi = line.split("\t");
			String chebiId = chebi [0];
			int children = Integer.valueOf(chebi[1]);
			String[] depths = chebi[2].split(",");
			int minDepth = Integer.MAX_VALUE;
			int avgDepth = 0;
			int maxDepth = 0;
			
			for (String depthString : depths) {
				int depth = Integer.valueOf(depthString.trim());
				minDepth = depth < minDepth ? depth : minDepth;
				maxDepth = depth > maxDepth ? depth : maxDepth;
				avgDepth += depth;
			}
			
			avgDepth = Math.round((float)avgDepth / (float)depths.length);
			
			chebiMinDepth.put(chebiId, minDepth);
			chebiAvgDepth.put(chebiId, avgDepth);
			chebiMaxDepth.put(chebiId, maxDepth);
			nrChildNodes .put(chebiId, children);
		}
		
		System.out.println("Done.");
		
		reader.close();
	}
	
	public FeatureTokenGenerator() {
		tokens = new ArrayList<FeatureToken>();
		
		if (chebiMinDepth == null) {
			try {
				loadChebiData("resources/chebi/chebi_ontology_fulldepth.txt");
			} catch (IOException e) {
				System.out.println("Error while loading chebi data");
				e.printStackTrace();
			}
		}
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
				token.addFeature(ChemSpot_Feature.CHEMSPOT);
				
				for (int i = 0; i < ids.length; i++) {
					if (ids[i] != null && !ids[i].isEmpty()) {
						token.addFeature(DICTIONARY_FEATURES[i]);
					}
				}
				
				String chebiId = mention.getCHEB();
				if (chebiId != null) {
					if (chebiAvgDepth.containsKey(chebiId)) {
						token.addFeature(ChemSpot_Feature.CHEB_AVG_DEPTH + "_" + chebiAvgDepth.get(chebiId));
					}
					if (chebiMinDepth.containsKey(chebiId)) {
						token.addFeature(ChemSpot_Feature.CHEB_MIN_DEPTH + "_" + chebiMinDepth.get(chebiId));
					}
					if (chebiMaxDepth.containsKey(chebiId)) {
						token.addFeature(ChemSpot_Feature.CHEB_MAX_DEPTH + "_" + chebiMaxDepth.get(chebiId));
					}
					if (nrChildNodes.containsKey(chebiId)) {
						token.addFeature(ChemSpot_Feature.CHEB_CHILDREN + "_" + nrChildNodes.get(chebiId));
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
		
		for (FeatureToken token : getFeatureTokens(aJCas)) {
			
			while (!nes.isEmpty() && nes.get(0).getEnd() < token.getBegin()) {
				nes.remove(0);
			}
			
			if (!nes.isEmpty() && nes.get(0).getBegin() <= token.getBegin() && nes.get(0).getEnd() >= token.getEnd()) {
				NamedEntity ne = nes.remove(0);
				System.out.println();
				System.out.println(ne.getCoveredText());
			}
			
			if (!token.getFeatures().isEmpty()) {				
				System.out.println("  " + token.getCoveredText() + " -> " + token.getFeatures());
			}
		}
	}
}
