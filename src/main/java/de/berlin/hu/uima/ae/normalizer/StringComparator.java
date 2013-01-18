package de.berlin.hu.uima.ae.normalizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * A string comparator utility class.
 * 
 * @author Torsten Huber
 * @version 03.06.2012
 */
public class StringComparator {
	/**
	 * Calculates the dice coefficient for two strings using default n-grams (bigrams).
	 * 
	 * @param s1 the first string
	 * @param s2 the second string
	 * @return the dice coefficient
	 */
	public static float diceCoefficient(String name1, String name2) {
		return diceCoefficient(name1, name2, 2);
	}
	
	public static Set<String> getNGrams(String s, int n) {
		n = n < 2 ? 2 : n;
		
		s = s.trim().toLowerCase().replaceAll("\\s+", " ");
		
		char padChar = Character.MIN_VALUE;
		for (int i = 0 ; i < n-1; i++) {
			s = padChar + s + padChar;
		}
		
		// read n-grams
		Set<String> ngrams = new HashSet<String>(s.length() - n+2, (float)1.0);
		for (int i = 0 ; i < s.length() - n+1; i++) {
			ngrams.add(s.substring(i, i+n));
		}
		
		return ngrams;
	}
	
	/**
	 * Calculates the dice coefficient for two strings using n-grams.
	 * 
	 * @param s1 the first string
	 * @param s2 the second string
	 * @param n the "n" in n-gram
	 * @return the dice coefficient
	 */
	public static float diceCoefficient(Set<String> s1, Set<String> s2) {
		Set<String> intersection = new HashSet<String>(s1);
		intersection.retainAll(s2);
		
		// calculate dice coefficient
		return (float)2 * intersection.size() / (s1.size() + s2.size());
	}	
	
	/**
	 * Calculates the dice coefficient for two strings using n-grams.
	 * 
	 * @param s1 the first string
	 * @param s2 the second string
	 * @param n the "n" in n-gram
	 * @return the dice coefficient
	 */
	public static float diceCoefficient(String s1, String s2, int n) {
		n = n < 2 ? 2 : n;
		
		// read n-grams
		Set<String> ngrams1 = getNGrams(s1, n);
		Set<String> ngrams2 = getNGrams(s1, n);
		
		Set<String> intersection = new HashSet<String>(ngrams1);
		intersection.retainAll(ngrams2);
		
		// calculate dice coefficient
		return (float)2 * intersection.size() / (ngrams1.size() + ngrams2.size());
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			System.out.print("name 1: ");
			String name1 = reader.readLine();
			
			System.out.print("name 2: ");
			String name2 = reader.readLine();
			
			System.out.println("dice coefficient: " + diceCoefficient(name1, name2));
			System.out.println();
		}
	}
}
