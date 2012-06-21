package de.berlin.hu.util;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

//FIXME not working
public class DictionaryBuilder {

	private static final boolean GENERATE_SMALL_CORPUS = false;
//	private static final boolean GENERATE_SMALL_CORPUS = true;
	public static final boolean USE_CHID_ONLY = true;

	public static void main(String[] args) throws IOException {
		String path =  "./ChemlistV1_2.ontology";
		File file = new File(path);
		FileReader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader);
		
		HashSet<DictionaryEntry> entries = new HashSet<DictionaryEntry>();
		HashSet<String> terms = null;
		HashSet<String> ids = null;
		int smallTerms = 0;
		
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			boolean add = false;
			if (line.equalsIgnoreCase("--")) {
				if (USE_CHID_ONLY) {
					if (ids != null) {
						for (String string : ids) {
							if (string.contains("CHID")) {
								add = true;
							}
						}
					}
					
					if (add) {
						entries.add(new DictionaryEntry(terms, ids));
					}
					
					terms = new HashSet<String>();
					ids = new HashSet<String>();
					add = false;
				} else {
					entries.add(new DictionaryEntry(terms, ids));
					terms = new HashSet<String>();
					ids = new HashSet<String>();
				}
			}
			
			if (line.startsWith("TM")) {
				String termList = line.split("\t")[0].substring(3);
				String[] splits = termList.split(";");
				for (String term : splits) {
					if (!term.isEmpty() && term.length() > 2) {
						terms.add(term);
					} else {
						//ignore
					}

					if (term.length() < 3) {
						smallTerms++;
					}
					
				}
			}
			
			if (line.startsWith("DB")) {
				ids.add(line.split("\t")[0].substring(3));
			}
		}
		
		System.out.println("number of small terms: " + smallTerms);
		
		String fileName = "";
		if (GENERATE_SMALL_CORPUS) {
			fileName = "dic/hettne.dict_small";
		} else {
			fileName = "dic/hettne.dict";
		}
		
		FileWriter writer = new FileWriter(new File(fileName));
		
		int counter = 0; 
		
		for (DictionaryEntry entry : entries) {
			counter++;
			if (GENERATE_SMALL_CORPUS) {
				if (counter < 100) {
					if (entry != null) {
						writeEntryToFile(writer, entry);
					}
				}
			} else {
				if (entry != null) {
					writeEntryToFile(writer, entry);
				}
			}
		}
		writer.close();
	}

	private static void writeEntryToFile(FileWriter writer, DictionaryEntry entry)
			throws IOException {
		String temp = entry.convertToLinnaeusFormat();
		if (temp != null) {
			writer.write(temp);

			String[] lines = temp.split("\n");
			for (String string : lines) {
				if (string.split("\t").length != 2) {
					System.out.println(Arrays.toString(string.split("\t")));
				}
			}
		}
	}
}
