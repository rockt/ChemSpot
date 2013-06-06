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

package de.berlin.hu.uima.ae.tagger.brics;

import de.berlin.hu.chemspot.Mention;
import de.berlin.hu.uima.ae.normalizer.Normalizer;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.AutomatonMatcher;
import dk.brics.automaton.RunAutomaton;
import dk.brics.automaton.State;
import dk.brics.automaton.StringUnionOperations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * User: Tim Rocktaeschel
 * Date: 7/2/12
 * Time: 2:20 PM
 */
public class BricsMatcher {
	private static final int TERMS_PER_AUTOMATON = 100000;
	
    private Collection<RunAutomaton> matchers = new ArrayList<RunAutomaton>();

    public BricsMatcher() throws IOException, ClassNotFoundException {
    	this(Normalizer.getIds().keySet(), TERMS_PER_AUTOMATON);
    }
    
    public BricsMatcher(Collection<String> chemicals) throws IOException, ClassNotFoundException {
    	System.out.print("Creating brics automaton...");
    	matchers.add(BricsMatcher.createAutomaton(chemicals));
    	System.out.println("Done.");
    }
    
    public BricsMatcher(Collection<String> chemicals, int termsPerAutomaton) throws IOException, ClassNotFoundException {
    	System.out.print("Creating brics automata");
    	matchers.addAll(BricsMatcher.createAutomata(chemicals, termsPerAutomaton));
    	
    	System.out.println("Created " + matchers.size() + " brics automata.");
    }
    
    /**
     * BricsMatcher loads a set of brics dictionary matchers packed in a zip file.
     * @param pathToZippedBinaries Path to the zip file containing a set of brics dictionary matchers.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public BricsMatcher(String pathToZippedBinaries) throws IOException, ClassNotFoundException {
    	if (pathToZippedBinaries.endsWith(".zip")) {
	        ZipFile zipFile = new ZipFile(pathToZippedBinaries);
	        Enumeration<? extends ZipEntry> entries = zipFile.entries();
	        while (entries.hasMoreElements()) {
	            ZipEntry entry = entries.nextElement();
	            matchers.add(RunAutomaton.load(zipFile.getInputStream(entry)));
	        }
    	} else {
    		 matchers.add(RunAutomaton.load(new FileInputStream(pathToZippedBinaries)));
    	}
        System.out.println("Loaded " + matchers.size() + " brics automata.");
    }
    
    public static List<RunAutomaton> createAutomata(Collection<String> chemicals, int termsPerAutomaton) {
    	List<RunAutomaton> result = new ArrayList<RunAutomaton>();
    	
    	int count = 0;
    	int total = 0;
    	List<String> terms = new ArrayList<String>();
    	for (String chemical : chemicals) {
    		terms.add(chemical);
    		
    		count++;
    		total++;
    		if (count >= termsPerAutomaton) {
    			result.add(createAutomaton(terms));
    			System.out.print(".");
    			terms.clear();
    			count = 0;
    		}
    	}
    	
    	if (!terms.isEmpty()) {
    		result.add(createAutomaton(terms));
    		System.out.println(" Done.");
			terms.clear();
			count = 0;
    	}
    	
    	return result;
    }
    
    public static RunAutomaton createAutomaton(Collection<String> chemicals) {
    	List<String> sortedList = new ArrayList<String>(chemicals);
		Collections.sort(sortedList, StringUnionOperations.LEXICOGRAPHIC_ORDER);
		String[] sortedArray = sortedList.toArray(new String[sortedList.size()]);
		sortedList = null;
		State state = StringUnionOperations.build(sortedArray);
		Automaton automaton = new Automaton();
		automaton.setInitialState(state);
		
		RunAutomaton runAutomaton = new RunAutomaton(automaton);
		
		return runAutomaton;
    }

    /**
     * Uses the set of brics dictionary matchers to extract mentions of chemical entities in natural language text.
     * @param text Input natural language text.
     * @return a collection of Mentions.
     */
    public Collection<Mention> match(String text) {
        Collection<Mention> matches = new HashSet<Mention>();
        for (RunAutomaton automat : matchers) {
            AutomatonMatcher matcher = automat.newMatcher(text);
            while (matcher.find()) {
                char left = ' ';
                char right = ' ';
                char nright = ' ';
                try {
                    left = text.charAt(matcher.start() - 1);
                } catch (ArrayIndexOutOfBoundsException e) {
                    //ignore
                } catch (StringIndexOutOfBoundsException e) {
                    //ignore
                }
                try {
                    right = text.charAt(matcher.end());
                } catch (ArrayIndexOutOfBoundsException e) {
                    //ignore
                } catch (StringIndexOutOfBoundsException e) {
                    //ignore
                }
                try {
                    nright = text.charAt(matcher.end() + 1);
                } catch (ArrayIndexOutOfBoundsException e) {
                    //ignore
                } catch (StringIndexOutOfBoundsException e) {
                    //ignore
                }
                String coveredText = text.substring(matcher.start(), matcher.end());

                //only add if not within a text and longer than two characters
                if (coveredText.length() > 2 && !Character.isLetter(left) && 
                		(!Character.isLetter(right) || (right == 's' && Character.isLetter(nright)))) {
                    matches.add(new Mention(matcher.start(), matcher.end() + (right == 's' ? 1 : 0), text.substring(matcher.start(), matcher.end())));
                }
            }
        }
        return matches;
    }}
