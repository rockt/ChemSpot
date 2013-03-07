package de.berlin.hu.uima.ae.feature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.u_compare.shared.semantic.NamedEntity;
import org.u_compare.shared.syntactic.Sentence;
import org.u_compare.shared.syntactic.Token;
import org.uimafit.util.JCasUtil;

import de.berlin.hu.chemspot.Mention;
import de.berlin.hu.util.Constants;

public class FeatureTokenGenerator {
	public enum Feature_Phase {
		PHASE1, // after all tagger components ran
		PHASE2, // after match expansion
		PHASE3, // after stopword filtering
		PHASE4  // after normalization
	};
	
	public enum ChemSpot_Feature {
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
		CHEB_CHILDREN,
		CHEMICAL_PREFIX,
		CHEMICAL_SUFFIX;
	};
		
	private final ChemSpot_Feature[] DICTIONARY_FEATURES = {
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
	
	private Map<Integer, List<FeatureToken>> tokens = null;
	
	private Map<String, Integer> chebiMinDepth = null;
	private Map<String, Integer> chebiAvgDepth = null;
	private Map<String, Integer> chebiMaxDepth = null;
	private Map<String, Integer> nrChildNodes = null;
	
	private List<String> prefixes = null;
	private List<String> suffixes = null;
	
	private Map<List<String>, String> phareData = null;
	
	private void loadChebiData(String file) throws IOException {
		chebiMinDepth = new HashMap<String, Integer>();
		chebiAvgDepth = new HashMap<String, Integer>();
		chebiMaxDepth = new HashMap<String, Integer>();
		nrChildNodes = new HashMap<String, Integer>();
		
		System.out.println("Loading chebi data from resource " + file + "...");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
		
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
	
	private void loadPrefixesSuffixes(String path) throws IOException {
		System.out.println("Loading prefixes and suffixes from resource directory " + path + "...");
		
		prefixes = new ArrayList<String>();
		suffixes = new ArrayList<String>();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(path + "prefixes.txt")));
		
		String line = null;
		while ((line = reader.readLine()) != null) {
			prefixes.add(line.split("\t")[0]);
		}
		reader.close();
		
		reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(path + "suffixes-filtered.txt")));
		
		while ((line = reader.readLine()) != null) {
			suffixes.add(line.split("\t")[0]);
		}
		reader.close();
		
		System.out.println("Done.");
	}
	
	private void loadPhareData(String file) throws IOException {
		System.out.println("Loading pharmagenomics relationship ontology data from resource " + file + "...");
		
		phareData = new HashMap<List<String>, String>();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
		
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] phare = line.split("\t");
			String label = phare[0];
			
			List<String> terms = new ArrayList<String>();
			for (String term : phare[1].split("\\|")) {
				terms.add(term);
			}
			
			phareData.put(terms, label);
		}
		reader.close();
		
		System.out.println("Done.");
	}
	
	public FeatureTokenGenerator() {
		System.out.println();
		System.out.println("Initializing feature generator.");
		
		tokens = new HashMap<Integer, List<FeatureToken>>();
		
		if (chebiMinDepth == null) {
			try {
				loadChebiData("/resources/chebi/chebi_ontology_fulldepth.txt");
			} catch (IOException e) {
				System.out.println("Error while loading chebi data");
				e.printStackTrace();
			}
		}
		
		if (prefixes == null) {
			try {
				loadPrefixesSuffixes("/resources/");
			} catch (IOException e) {
				System.out.println("Error while loading prefixes and suffixes");
				e.printStackTrace();
			}
		}
		
		if (phareData == null) {
			try {
				loadPhareData("/resources/phare.txt");
			} catch (IOException e) {
				System.out.println("Error while loading pharmagenomics relationship ontology data");
				e.printStackTrace();
			}
		}
		
		System.out.println("Feature generator initialized.");
		System.out.println();
	}
	
	public void process(JCas aJCas, Feature_Phase phase) throws AnalysisEngineProcessException {
		switch (phase) {
		case PHASE1:
			tokens.put(aJCas.getDocumentText().hashCode(), new ArrayList<FeatureToken>());
			generateFeatureTokens(aJCas);
			checkNormalization(aJCas);
			break;
		case PHASE2:
			checkExpandedMentions(aJCas);
			break;
		case PHASE3:
			checkStopwords(aJCas);
			break;
		case PHASE4:
			checkNormalization(aJCas);
			checkPrefixesSuffixes(aJCas);
			checkPhareData(aJCas);
			//printFeatureTokens(aJCas);
			break;
		}
	}
	
	public void clearFeatureTokens() {
		tokens.clear();
	}
	
	private void generateFeatureTokens(JCas aJCas) {
		List<FeatureToken> tokens = getFeatureTokens(aJCas);
		
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
		return tokens.get(aJCas.getDocumentText().hashCode());
	}
	
	public List<FeatureToken> removeFeatureTokens(JCas aJCas) {
		return tokens.remove(aJCas.getDocumentText().hashCode());
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
			if (Constants.GOLDSTANDARD.equals(ne.getSource())) continue;
			
			boolean was_expanded = false;
			List<FeatureToken> tokens = getFeatureTokens(aJCas, ne);
			for (FeatureToken token : tokens) {
				try {
					ChemSpot_Feature feature = ChemSpot_Feature.valueOf(ne.getSource().toUpperCase());
					if (!token.hasFeature(feature)) {
						token.addFeature(ChemSpot_Feature.valueOf(feature + "_ME"));
						token.addFeature(ChemSpot_Feature.MATCH_EXPANSION);
						was_expanded = true;
					}
				} catch (IllegalArgumentException e) {
					// do nothing
				}
			}
			
			if (was_expanded) {
				for (FeatureToken token : tokens) {
					try {
						ChemSpot_Feature feature = ChemSpot_Feature.valueOf(ne.getSource().toUpperCase());
						if (!token.hasFeature(feature + "_ME")) {
							token.addFeature(ChemSpot_Feature.valueOf(feature + "_ME"));
							token.addFeature(ChemSpot_Feature.MATCH_EXPANSION);
						}
					} catch (IllegalArgumentException e) {
						// do nothing
					}
				}
			}
		}
	}
	
	private void checkStopwords(JCas aJCas) {
		List<FeatureToken> tokens = new ArrayList<FeatureToken>(getFeatureTokens(aJCas));
		
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
	}
	
	private void checkPrefixesSuffixes(JCas aJCas) {
		for (FeatureToken token : getFeatureTokens(aJCas)) {
			for (String prefix : prefixes) {
				if (token.getCoveredText().toLowerCase().startsWith(prefix)) {
					token.addFeature(ChemSpot_Feature.CHEMICAL_PREFIX);
				}
			}
			
			for (String suffix : suffixes) {
				if (token.getCoveredText().toLowerCase().endsWith(suffix)) {
					token.addFeature(ChemSpot_Feature.CHEMICAL_SUFFIX);
				}
			}
		}
	}
	
	private void checkPhareData(JCas aJCas) {
		for (Sentence sentence : JCasUtil.iterate(aJCas, Sentence.class)) {
			String sentenceString = sentence.getCoveredText().toLowerCase();
			for (List<String> terms : phareData.keySet()) {
				for (String term : terms) {
					int index = sentenceString.indexOf(term.toLowerCase());
					if (index != -1
							&& (index - 1 < 0 || !Character.isLetter(sentenceString.charAt(index-1)))
							&& (index + term.length() >= sentenceString.length() || !Character.isLetter(sentenceString.charAt(index+term.length())))
							) 
					{
						for (FeatureToken token : getFeatureTokens(aJCas, sentence)) {
							if (token.getBegin() >= sentence.getBegin() + index && token.getEnd() <= sentence.getBegin() + index + term.length()) {
								token.addFeature(phareData.get(terms));
							}
						}
					}
				}
			}
		}
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
