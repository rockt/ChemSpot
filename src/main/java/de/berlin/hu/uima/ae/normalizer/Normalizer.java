/*
 * Copyright (c) 2012. Humboldt-Universität zu Berlin, Dept. of Computer Science and Dept.
 * of Wissensmanagement in der Bioinformatik
 * -------------------------------
 *
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS COMMON PUBLIC
 * LICENSE ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM
 * CONSTITUTES RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.
 *
 * http://www.opensource.org/licenses/cpl1.0
 */

package de.berlin.hu.uima.ae.normalizer;

import de.berlin.hu.chemspot.ChemSpotConfiguration;
import de.berlin.hu.chemspot.ChemSpotConfiguration.Component;
import de.berlin.hu.util.Constants;
import de.berlin.hu.util.Constants.ChemicalID;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.u_compare.shared.semantic.NamedEntity;
import org.uimafit.util.JCasUtil;
import uk.ac.cam.ch.wwmm.opsin.NameToInchi;
import uk.ac.cam.ch.wwmm.opsin.NameToStructureException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * User: Tim Rocktaeschel
 * Date: 8/16/12
 * Time: 3:28 PM
 */
public class Normalizer extends JCasAnnotator_ImplBase {
    private HashMap<String,String[]> ids = new HashMap<String,String[]>();
    private NameToInchi nameToInChi;
    private static final String PATH_TO_IDS = "PathToIDs";

    private void writePrefixSuffixLists() throws IOException {
    	int prefixLength = 3;
    	int suffixLength = 3;
    	
    	Map<String, Integer> prefixes = new HashMap<String, Integer>();
    	Map<String, Integer> suffixes = new HashMap<String, Integer>();
    	
    	System.out.println("Writing prefix and suffix lists...");
    	
    	for (String chemical : ids.keySet()) {
    		if (chemical.startsWith("(")) chemical = chemical.substring(1, chemical.length());
    		if (chemical.endsWith(")")) chemical = chemical.substring(0, chemical.length() - 1);
    		
    		String prefix = chemical.length() >= prefixLength ? chemical.substring(0, prefixLength) : null;
    		String suffix = chemical.length() >= suffixLength ? chemical.substring(chemical.length() - suffixLength, chemical.length()) : null;
    		
    		if (prefix != null) {
    			if (!prefixes.containsKey(prefix)) {
    				prefixes.put(prefix, 0);
    			}
    			
    			prefixes.put(prefix, prefixes.get(prefix)+1);
    		}
    		
    		if (suffix != null) {
    			if (!suffixes.containsKey(suffix)) {
    				suffixes.put(suffix, 0);
    			}
    			
    			suffixes.put(suffix, suffixes.get(suffix)+1);
    		}
    	}
    	
    	List<String> prefixList = new ArrayList<String>(prefixes.keySet());
    	List<String> suffixList = new ArrayList<String>(suffixes.keySet());
    	
    	class IntegerMapComparator implements Comparator<String> {
    		private Map<String, Integer> map = null;
    		
    		public IntegerMapComparator(Map<String, Integer> map) {
    			this.map = map;
    		}
    		
			public int compare(String o1, String o2) {
				return map.get(o1) - map.get(o2);
			}
    		
    	};
    	
    	Collections.sort(prefixList, Collections.reverseOrder(new IntegerMapComparator(prefixes)));
    	Collections.sort(suffixList, Collections.reverseOrder(new IntegerMapComparator(suffixes)));
    	
    	BufferedWriter writer = new BufferedWriter(new FileWriter("prefixes.txt"));
    	for (String prefix : prefixList) {
    		writer.write(String.format("%s\t%d%n", prefix, prefixes.get(prefix)));
    	}
    	writer.close();
    	
    	writer = new BufferedWriter(new FileWriter("suffixes.txt"));
    	for (String suffix : suffixList) {
    		writer.write(String.format("%s\t%d%n", suffix, suffixes.get(suffix)));
    	}
    	writer.close();
    	
    	writer = new BufferedWriter(new FileWriter("suffixes-filtered.txt"));
    	for (String suffix : suffixList) {
    		if (suffix.matches(String.format("[a-z]{%d}", suffixLength))) {
    			writer.write(String.format("%s\t%d%n", suffix, suffixes.get(suffix)));
    		}
    	}
    	writer.close();
    	
    	System.out.println("Done.");
    }
        
    @Override
    public void initialize(UimaContext aContext) throws ResourceInitializationException {
        super.initialize(aContext);
        System.out.println("Initializing normalizer...");
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(aContext.getConfigParameterValue(PATH_TO_IDS).toString());
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                BufferedReader reader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry)));
                String line = reader.readLine();
                while (line != null) {
                    int splitAt = line.indexOf('\t');
                    ids.put(line.substring(0, splitAt), line.substring(splitAt+1).split("\t"));
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            throw new ResourceInitializationException(e);
        }
        if (ChemSpotConfiguration.useComponent(Component.OPSIN)) {
	        try {
	            //initializing OPSIN
	            nameToInChi = new NameToInchi();
	        } catch (NameToStructureException e) {
	            e.printStackTrace();
	        }
        }
        
        /*try {
			writePrefixSuffixLists();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
    }
    
    private String[] getBestMatch(String chemical, Map<String, String[]> ids) {
    	String[] result = null;
    	
    	List<String> substringMatches = new ArrayList<String>();
    	
    	String bestMatch = null;
    	float bestScore = 0;
    	
    	int i = 0;
    	for (String key : ids.keySet()) {
    		if (Math.abs(chemical.length() - key.length()) < 3){
    			
    		}
    		
    		float score = StringComparator.diceCoefficient(StringComparator.getNGrams(chemical, 2), StringComparator.getNGrams(key, 2));
			
			if (score > bestScore) {
				bestMatch = key;
				bestScore = score;
			}
    		
    		if (chemical.contains(key)) {
    			substringMatches.add(key);
    		}
    		
    		if (++i % 10000 == 0) {
    			System.out.print(".");
    		}
    	}
    	
    	if (bestScore > 0.7) {
    		result = ids.get(bestMatch);
    	} else if (!substringMatches.isEmpty()) {
    		Comparator<String> comparator = new Comparator<String>() {

    			public int compare(String o1, String o2) {
    				return o1.length() - o2.length();
    			}
        		
        	};
        	
        	Collections.sort(substringMatches, Collections.reverseOrder(comparator));
        	
        	String bestSubstringMatch = substringMatches.get(0);
        	
        	if (bestSubstringMatch.length() > 3) {
        		result = ids.get(bestMatch);
        	}
    	}
    	
    	return result;
    }
    
    @Override
    public void process(JCas jCas) throws AnalysisEngineProcessException {
        Iterator<NamedEntity> entities = JCasUtil.iterator(jCas, NamedEntity.class);
        int nE = 0;
        int nN = 0;
        
        while (entities.hasNext()) {
            NamedEntity entity = entities.next();
            String inchi = nameToInChi != null ? nameToInChi.parseToStdInchi(entity.getCoveredText()) : null;

            if (!Constants.GOLDSTANDARD.equals(entity.getSource())) {
                nE++;
                String[] normalized = ids.get(entity.getCoveredText().toLowerCase());
                
                /*if (normalized == null) {
                	normalized = getBestMatch(entity.getCoveredText().toLowerCase(), ids);
                }*/
                
                //if entity is contained in dictionary
                if (normalized != null) {
                    //FIXME: use a UIMA field instead of a String here
                    if (normalized.length >= ChemicalID.INCH.ordinal()) {
                        if (normalized[ChemicalID.INCH.ordinal()].isEmpty() && inchi != null) normalized[ChemicalID.INCH.ordinal()] = inchi;
                    } else {
                        if (inchi != null) {
                            String[] normalizedTemp = Arrays.copyOf(normalized, ChemicalID.INCH.ordinal() + 1);
                            normalizedTemp[ChemicalID.INCH.ordinal()] = inchi;
                            normalized = normalizedTemp;
                        }
                    }
                    nN++;
                } else {
                    if (inchi != null) {
                        String[] normalizedTemp = new String[ChemicalID.INCH.ordinal() + 1];
                        normalizedTemp[ChemicalID.INCH.ordinal()] = inchi;
                        normalized = normalizedTemp;
                        nN++;
                    }
                }
                entity.setId(Arrays.toString(normalized));
            }
        }
        //System.out.println(nN + "/" + nE);
    }
}
