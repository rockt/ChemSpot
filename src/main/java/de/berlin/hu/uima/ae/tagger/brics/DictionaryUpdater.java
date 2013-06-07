package de.berlin.hu.uima.ae.tagger.brics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;

import de.berlin.hu.chemspot.ChemSpotConfiguration;
import de.berlin.hu.chemspot.Mention;
import de.berlin.hu.uima.ae.normalizer.Normalizer;
import de.berlin.hu.uima.ae.tagger.abbrev.ExtractAbbrev;
import de.berlin.hu.util.Constants;
import de.berlin.hu.util.Constants.ChemicalID;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RunAutomaton;
import dk.brics.automaton.State;
import dk.brics.automaton.StringUnionOperations;

public class DictionaryUpdater {
	
	private static final Pattern SDF_KEY_PATTERN = Pattern.compile("^>\\s*<([^>]+)>");
	private static final Pattern DOSAGE_PATTERN = Pattern.compile("%"/*"\\b(%|mg|mm|g)\\b"*/);
	private static final Pattern SYNTACTIC_INVERSION_PATTERN = Pattern.compile("^(.+[^\\-]),\\s+(.+)$");
	private static final Pattern ABBREVIATION_PATTERN = Pattern.compile("([^\\(]+) \\(([^\\)]*)\\)");
	private static final List<String> EXCLUDE_LIST = new ArrayList<String>(Arrays.asList(
			"not otherwise specified",
			"not specified",
			"unspecified",
			", NOS",
			"(NOS)",
			"[NOS]",
			"deprecated",
			"unknown",
			"obsolete",
			"miscellaneous"
	));
	private static final List<String> EXCLUDE_PREFIXES = new ArrayList<String>(Arrays.asList(
			"(rel)-",
			"rel-",
			"(rel)",
			"rel"
	));
	private static final List<String> CHEBI_KEYS = new ArrayList<String>(Arrays.asList(
			"chebi id", 
			"chebi name",
			"formulae",
			"inchi",
			"pubchem database links",
			"iupac names",
			"synonyms"
	));
	
	private static String dictFilePath = null;
	private static String idsFilePath = null;
	private static URL chebiSDFURL = null;
	private static boolean chebiMustContainFormula = false;
	
	private static ExtractAbbrev extractAbbrev = null;
	private static Set<String> filteredList = null;
	private static String outputLocation = null;
	private static File mergeLogFile = null;
	private static File conflictLogFile = null;
	private static File rewriteLogFile = null;
	private static File deletedLogFile = null;
	private static BufferedWriter rewriteLog = null;
	private static BufferedWriter deletedLog = null;

	static {
		try {
			InputStream in = DictionaryUpdater.class.getResourceAsStream("resources/conf/update.cfg");
			if (in != null) {
				ChemSpotConfiguration.initialize(in);
			}
			ChemSpotConfiguration.initialize();
		} catch (IOException e) {
				e.printStackTrace();
		}
		
		outputLocation = ChemSpotConfiguration.getUpdateOutputPath();
		chebiSDFURL = ChemSpotConfiguration.getChEBISDFUpdateURL();
		dictFilePath = ChemSpotConfiguration.getDictionaryUpdatePath();
		idsFilePath = ChemSpotConfiguration.getIdsFileUpdatePath();
		chebiMustContainFormula = ChemSpotConfiguration.isChEBIUpdateMustContainFormula();
		
		extractAbbrev = new ExtractAbbrev();
		filteredList = new HashSet<String>();
		
		mergeLogFile = new File(outputLocation + "mergers.log");
		conflictLogFile = new File(outputLocation + "conflicts.log");
		rewriteLogFile = new File(outputLocation + "rewritten.log");
		deletedLogFile = new File(outputLocation + "deleted.log");
	}
	
	public static Map<String, String[]> readIdsFile(File idsFile) throws IOException {
    	Map<String, String[]> result = new HashMap<String, String[]>();
    	
    	System.out.print("Loading ids file... ");
    	
    	ZipFile zipFile = new ZipFile(idsFile);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        Map<String, String[]> duplicates = new HashMap<String, String[]>();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry)));
    	
	        String line = reader.readLine();
	        
	        while (line != null) {
	            int splitAt = line.indexOf('\t');
	            String chem = line.substring(0, splitAt).toLowerCase();
	            String identifiers = line.substring(splitAt+1);
	            
	            if (!result.containsKey(chem)) {
	            	result.put(chem, identifiers.split("\t"));
	            } else {
	            	duplicates.put(chem, identifiers.split("\t"));
	            }
	            
	            line = reader.readLine();
	        }
        }
        
        
        if (!duplicates.isEmpty()) {
        	System.out.println("IDs file contains duplicate entries.");
        	result = mergeIDs(result, duplicates, false);
        }
        
        System.out.println("Done.");
        
	    return result;
    }
	
	public static Map<String, String[]> mergeIDs(Map<String, String[]> d1, Map<String, String[]> d2, boolean overwrite) {
		int[] conflicts = new int[Constants.ChemicalID.values().length];
		int[] mergers = new int[Constants.ChemicalID.values().length];
		int exactDuplicates = 0;
		int added = 0;
		int updated = 0;
		
		BufferedWriter conflictLog = null;
		BufferedWriter mergeLog = null;
		
		for (String chemical : d2.keySet()) {
			if (d1.containsKey(chemical)) {
				updated++;
				
				String[] ids1 = d1.get(chemical);
				String[] ids2 = d2.get(chemical);
				
				if (ids1.length == ids2.length) {
					if (ids1.length > ids2.length) {
						ids2 = Arrays.copyOf(ids2, ids1.length);
					} else {
						ids1 = Arrays.copyOf(ids1, ids2.length);
					}
				}
				
				boolean wasUpdated = false;
				String conflictLogEntry = "";
				String mergeLogEntry = "";
				for (int i = 0; i < Math.min(ids1.length, ids2.length) && i < Constants.ChemicalID.values().length; i++) {
					String chemicalId = Constants.ChemicalID.values()[i].toString();
					
					if (ids2[i] != null && !ids2[i].isEmpty() && (ids1[i] == null || ids1[i].isEmpty())) {
						mergeLogEntry += String.format("%n  %s: %s<-%s", chemicalId, ids1[i], ids2[i]);
						ids1[i] = ids2[i];
						mergers[i]++;
						wasUpdated = true;
					} else if (ids1[i] != null && !ids1[i].isEmpty() && (ids2[i] == null || ids2[i].isEmpty())) {
						mergeLogEntry += String.format("%n  %s: %s->%s", chemicalId, ids1[i], ids2[i]);
						ids2[i] = ids1[i];
					} else if (ids1[i] != null && ids2[i] != null) {
						if (!ids1[i].equals(ids2[i])) {
							conflicts[i]++;
							mergeLogEntry += String.format("%n  %s: %s%s%s", chemicalId, ids1[i], overwrite ? "<-" : "->", ids2[i]);
							conflictLogEntry += String.format("%n  %s: %s%s%s", chemicalId, ids1[i], overwrite ? "<-" : "->", ids2[i]);
							
							if (overwrite) {
								ids1[i] = ids2[i];
							}
							wasUpdated = true;
						}
					}
				}
				
				if (wasUpdated) {
					if (!mergeLogEntry.isEmpty()) {
						mergeLogEntry = chemical + mergeLogEntry;
						
						try {
							if (mergeLog == null && mergeLogFile != null) {
								mergeLog = new BufferedWriter(new FileWriter(mergeLogFile, true));
							}
							
							mergeLog.write(mergeLogEntry);
							mergeLog.newLine();mergeLog.newLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
					if (!conflictLogEntry.isEmpty()) {
						conflictLogEntry = chemical + conflictLogEntry;
						
						try {
							if (conflictLog == null && conflictLogFile != null) {
								conflictLog = new BufferedWriter(new FileWriter(conflictLogFile, true));
							}
							
							conflictLog.write(conflictLogEntry);
							conflictLog.newLine();conflictLog.newLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else {
					exactDuplicates++;
				}
			} else {
				if (!chemical.matches("[\\w]+")) {
					d1.put(chemical, d2.get(chemical));
					added++;
				}
			}
		}
		
		updated -= exactDuplicates;
		String report = added != 0 ? added + " entries added" : "";
		report += updated != 0 ? (report.isEmpty() ? "" : ", ") + updated + " entries updated" : "";
		report += exactDuplicates != 0 ? (report.isEmpty() ? "" : ", ") + exactDuplicates + " exact duplicates removed" : "";
		System.out.println("  " + report + ".");
		
		String mergersReport = "";
		for (ChemicalID id : Constants.ChemicalID.values()) {
			if (mergers[id.ordinal()] > 0) {
				mergersReport += (mergersReport.isEmpty() ? "" : ", ") + id.toString() + ": " + mergers[id.ordinal()];
			}
		}
		if (!mergersReport.isEmpty()) System.out.println("  merged ids: " + mergersReport +".");
		
		String conflictsReport = "";
		for (ChemicalID id : Constants.ChemicalID.values()) {
			if (conflicts[id.ordinal()] > 0) {
				conflictsReport += (conflictsReport.isEmpty() ? "" : ", ") + id.toString() + ": " + conflicts[id.ordinal()];
			}
		}
		if (!conflictsReport.isEmpty()) {
			System.out.println("  conflicting ids: " + conflictsReport + ".");
			System.out.println("  conflicting ids were " + (overwrite ? "" : "not ") + "overwritten.");
		}
		
		if (mergeLog != null) {
			try {
				mergeLog.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conflictLog != null) {
			try {
				conflictLog.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return d1;
	}
	
	public static File downloadFile(URL url, File outputLocation) throws IOException {
		System.out.print("Downloading file from: " + url.toString() + "... ");
		
		URLConnection connection = url.openConnection();
		
		String dateStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		outputLocation = new File(outputLocation.toString().replaceAll("((\\.\\w{1,5})+)$", "-" + dateStamp + "$1"));
		
		outputLocation.getParentFile().mkdirs();
		if (outputLocation.exists()) {
			System.out.println("File already exists.");
			return outputLocation;
		}
		
		FileOutputStream outputStream = new FileOutputStream(outputLocation);
		IOUtils.copy(connection.getInputStream(), outputStream);
		
		outputStream.close();
		System.out.println("Done.");
		
		return outputLocation;
	}
	
	public static List<Map<String, String>> readSDFFile(File pathToFile) throws IOException {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		
		System.out.print("Reading SDF file: " + pathToFile + "... ");
		
		InputStream inputStream = new FileInputStream(pathToFile);
		if (pathToFile.getName().endsWith(".gz")) {
			inputStream = new GZIPInputStream(inputStream);
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		
		String line = null;
		Map<String, String> data = new HashMap<String, String>();
		
		Pattern whiteSpacePattern = Pattern.compile("\\s+");
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("$$$$")) {
				result.add(data);
				data = new HashMap<String, String>();
			}
			
			if (line.startsWith(">")) {
				Matcher m = SDF_KEY_PATTERN.matcher(line);
				if (m.matches()) {
					String key = m.group(1).toLowerCase();
					
					if (CHEBI_KEYS.contains(key)) {
						String value = "";
						while ((line = reader.readLine()) != null && !line.isEmpty() && !line.startsWith("$$$$")) {
							if (!value.isEmpty()) value += "|";
							value += line.trim();
						}
						
						data.put(key, value);
					}
				}
			}
		}
		
		result.add(data);
		reader.close();
		
		System.out.println("Done.");
		System.out.println("  " + result.size() + " entries loaded.");
		
		return result;
	}
	
	public static Map<String, String[]> convertChEBISDFToDict(List<Map<String, String>> chebiSDF) {
		Map<String, String[]> result = new HashMap<String, String[]>();
		
		System.out.print("Converting ChEBI SDF data to dictionary format... ");
		
		for (Map<String, String> data : chebiSDF) {
			if (!(data.containsKey("chebi id") && data.containsKey("chebi name"))) {
				continue;
			}
			
			String chebId = data.get("chebi id").replace("CHEBI:", "");
			String name = data.get("chebi name").toLowerCase();
			
			if (chebiMustContainFormula && !(data.containsKey("formulae") && data.get("formulae") != null && !data.get("formulae").isEmpty() && data.get("formulae").length() > 2)) {
				filteredList.add(name);
				try {
					appendToFilterLog(name);
				} catch (IOException e) {
					e.printStackTrace();
				}
				continue;
			}
			
			String[] ids = new String[Constants.ChemicalID.values().length];
			ids[Constants.ChemicalID.CHEB.ordinal()] = chebId;
			
			if (data.containsKey("inchi")) {
				ids[Constants.ChemicalID.INCH.ordinal()] = data.get("inchi");
			}
			
			if (data.containsKey("pubchem database links")) {
				ids[Constants.ChemicalID.PUBC.ordinal()] = data.get("pubchem database links").split("\\|")[0];
			}
			
			result.put(name, ids);
			
			if (data.containsKey("iupac names")) {
				for (String iupacName : data.get("iupac names").split("\\|")) {
					result.put(iupacName.toLowerCase(), ids);
				}
			}
			
			if (data.containsKey("synonyms")) {
				for (String synonym : data.get("synonyms").split("\\|")) {
					if (synonym.length() > 10 && !synonym.toLowerCase().matches("[\\w ]+")) {
						result.put(synonym.toLowerCase(), ids);
					}
				}
			}
			
			/*if (result.containsKey("voltage")) {
				System.out.println("now");
			}*/
		}
		
		try {
			appendToFilterLog(String.format("%n%n%n%n%n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Done.");
		System.out.println("  " + result.size() + " chemicals successfully converted.");
		
		return result;
	}
	
	public static void appendToFilterLog(String s) throws IOException {
		if (deletedLog == null) {
			deletedLog = new BufferedWriter(new FileWriter(deletedLogFile, true));
		}
		
		deletedLog.write(s);
		deletedLog.newLine();
	}
	
	public static boolean isFilter(String chemical) {
		boolean result = false;
		
		result = result || chemical == null || chemical.isEmpty();
		result = result || chemical.length() < 3;
		result = result || DOSAGE_PATTERN.matcher(chemical).find();
		result = result || chemical.indexOf('@') != -1;
		
		for (String excludeString : EXCLUDE_LIST) {
			result = result || chemical.contains(excludeString);
		}
		
		if (!result && filteredList.contains(chemical)) {
			result = true;
		}
		
		if (result) {
			try {
				if (!filteredList.contains(chemical)) {
					filteredList.add(chemical);
				}
				appendToFilterLog(chemical);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public static String rewriteChemical(String chemical) {
		chemical = chemical.replace("'s ", " ");
		
		Matcher m = SYNTACTIC_INVERSION_PATTERN.matcher(chemical);
		if (m.matches()) {
			String s1 = m.group(1).trim();
			String s2 = m.group(2).trim();
			
			if (!EXCLUDE_PREFIXES.contains(s2.toLowerCase())) {
				s1 = rewriteChemical(s1);
				s2 = rewriteChemical(s2);
				chemical = s2 + (s2.endsWith("-") ? "" : " ") + s1 + "<SPLIT>" + s1;
			} else {
				s1 = rewriteChemical(s1);
				chemical = s1;
			}
		}
		
		m = ABBREVIATION_PATTERN.matcher(chemical);
		if (m.matches()) {
			String s1 = null;
			String s2 = null;
			
			List<Mention> abbreviations = extractAbbrev.getMentions(chemical);
			if (!abbreviations.isEmpty()) {
				s1 = abbreviations.get(0).getCHID();
				s2 = abbreviations.get(0).getText();
			} else {
				s1 = m.group(1);
			}
			
			//if (!s2.isEmpty() && s1.charAt(0) == s2.charAt(0) && s2.indexOf(' ') == -1) {
			if (s2 != null) {
				chemical = chemical + "<SPLIT>" + s1 + "<SPLIT>" + s2;
			} else {
				chemical = chemical + "<SPLIT>" + s1;
			}
		}
		
		return chemical;
	}
	
	public static void appendToRewrittenLog(String s) throws IOException {
		if (rewriteLog == null) {
			rewriteLog = new BufferedWriter(new FileWriter(rewriteLogFile, true));
		}
		
		rewriteLog.write(s);
		rewriteLog.newLine();
	}
	
	private static int processed = 0;
	private static int filtered = 0;
	private static int rewritten = 0;
	public static Map<String, String[]> processChemicals(Map<String, String[]> chemicals) {
		Map<String, String[]> result = new HashMap<String, String[]>();
		
		System.out.print("Processing chemicals... ");
		
		Map<String, String[]> toBeMerged = new HashMap<String, String[]>();
		for (String chemical : chemicals.keySet()) {
			if (!isFilter(chemical)) {
				String rewrittenChemical = rewriteChemical(chemical);
				
				if (!chemical.equals(rewrittenChemical)) {
					//System.out.println(chemical + " -> " + rewrittenChemical);
					rewritten++;
					
					try {
						if (rewrittenChemical.contains("<SPLIT>")) {
							String[] split = rewrittenChemical.split("<SPLIT>");
							
							toBeMerged.put(split[0].trim(), chemicals.get(chemical));
							String logEntry = "'" + split[0].trim() + "'";
							for (int i = 1; i < split.length; i++) {
								toBeMerged.put(rewrittenChemical, chemicals.get(chemical));
								logEntry += " and '" + split[i].trim() + "'";
							}
							
							appendToRewrittenLog(String.format("%s -> %s", chemical, logEntry));
							rewritten += split.length - 1;
						} else {
							toBeMerged.put(rewrittenChemical, chemicals.get(chemical));
							appendToRewrittenLog(String.format("%s -> %s", chemical, rewrittenChemical));
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					result.put(rewrittenChemical, chemicals.get(chemical));
				}
			} else {
				filtered++;
				//System.out.println("- " + chemical);
			}
			
			processed++;
		}
		
		System.out.println("Done.");
		System.out.printf("  %d chemicals processed, %d filtered, %d rewritten.%n", result.size(), filtered, rewritten);
		
		if (!toBeMerged.isEmpty()) {
			System.out.println("Merging rewritten chemicals...");
			
			for (String chemical : new ArrayList<String>(toBeMerged.keySet())) {
				if (isFilter(chemical)) {
					toBeMerged.remove(chemical);
				}
			}
			mergeIDs(result, toBeMerged, false);
			System.out.println("Done.");
		}
		
		return result;
	}
	
	public static void writeDictionary(Map<String, String[]> ids, File outputFile) throws IOException {
		outputFile.getParentFile().mkdirs();
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		
		System.out.print("Writing dictionary to '" + outputFile + "'... ");
		
		for (String chem : ids.keySet()) {
        	String[] chemIds = ids.get(chem);
        	String idString = "";
        	
            for (ChemicalID type : ChemicalID.values()) {
            	String id = "";
            	if (type.ordinal() < chemIds.length) {
            		id = chemIds[type.ordinal()];
            		if (id == null) id = "";
            	}
            	idString += "\t" + id; 
            }
            
        	writer.write(chem + idString);
            writer.newLine();
        }
		
		writer.close();
		
		System.out.println("Done.");
	}
	
	public static void writeAutomaton(Collection<String> chemicals, File outputFile) throws FileNotFoundException, IOException {
		System.out.print("Writing chemical automaton to '" + outputFile + "'");
		
		List<String> sortedList = new ArrayList<String>(chemicals);
		Collections.sort(sortedList, StringUnionOperations.LEXICOGRAPHIC_ORDER);
		System.out.print(".");
		State state = StringUnionOperations.build(sortedList.toArray(new String[sortedList.size()]));
		Automaton automaton = new Automaton();
		automaton.setInitialState(state);
		System.out.print(".");
		
		RunAutomaton runAutomaton = new RunAutomaton(automaton);
		System.out.print(".");
		outputFile.getParentFile().mkdirs();
		OutputStream s = new FileOutputStream(outputFile);
		runAutomaton.store(s);
		
		s.close();
		
		System.out.println(" Done.");
	}
	
	public static Map<String, String[]> getChebi(URL url) {
		System.out.println();
		System.out.println("--- Generating ChEBI Update ---");
		
		File outputLocation = new File(DictionaryUpdater.outputLocation + url.getFile().replaceAll(".*/", ""));
		try {
			outputLocation = downloadFile(url, outputLocation);
		} catch (IOException e) {
			System.err.println("Could not download ChEBI SDF file from " + url.toString());
			e.printStackTrace();
			return null;
		}
		
		List<Map<String, String>> sdfData;
		try {
			sdfData = readSDFFile(outputLocation);
		} catch (IOException e) {
			System.err.println("Could not read SDF file from " + outputLocation);
			e.printStackTrace();
			return null;
		}
		
		Map<String, String[]> ids = convertChEBISDFToDict(sdfData);
		
		return ids;
	}
	
	public static void updateDictionaryFile(File dictionaryFile, List<String> newAutomatonFiles) throws IOException {
		ZipFile zipFile = new ZipFile(dictionaryFile);
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputLocation + "dict.zip"));
		
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if (!newAutomatonFiles.contains(entry.getName())) {
	            InputStream in = zipFile.getInputStream(entry);
	            out.putNextEntry(new ZipEntry(entry.getName()));
	            IOUtils.copy(in, out);
	            in.close();
            }
        }
        
        zipFile.close();
        
        for (String fileName : newAutomatonFiles) {
        	File newAutomatonFile = new File(outputLocation + fileName);
		    InputStream in = new FileInputStream(newAutomatonFile);
		    out.putNextEntry(new ZipEntry(newAutomatonFile.getName()));
		    IOUtils.copy(in, out);
		    
		    in.close();
        }
        
        out.close();
	}
	
	public static void update(File dictionaryFile, File idsFile) throws FileNotFoundException, IOException {
		Map<String, String[]> chemicals = getChebi(chebiSDFURL);
		
		if (!new File(outputLocation).exists()) {
			new File(outputLocation).mkdir();
		}
		
		if (mergeLogFile.exists()) mergeLogFile.delete();
		if (conflictLogFile.exists()) conflictLogFile.delete();
		if (rewriteLogFile.exists()) rewriteLogFile.delete();
		if (deletedLogFile.exists()) deletedLogFile.delete();
		
		List<String> automatonFiles = new ArrayList<String>();
		if (chemicals != null) {
			chemicals = processChemicals(chemicals);
			
			/*for (String chemical : new ArrayList<String>(chemicals.keySet())) {
				//if (chemical.matches("[\\w':,\\-]+( [\\w':,\\-]+)?")) chemicals.remove(chemical);
				if (chemical.matches("[\\w':,\\-]+")) chemicals.remove(chemical);
			}*/
			
			writeDictionary(chemicals, new File(outputLocation + "chebi.map"));
			writeAutomaton(chemicals.keySet(), new File(outputLocation + "chebi_updated.atm"));
			automatonFiles.add("chebi_updated.atm");
		}
		
		Map<String, String[]> ids = null;	
		if (idsFile != null) {
			if (!idsFile.exists()) {
				System.out.println("IDs file at '" + idsFile.getPath() + "' does not exist");
			} else {
				System.out.println();
				System.out.println("--- Loading IDs ---");
				ids = readIdsFile(idsFile);
				ids = processChemicals(ids);
			}
		}
		
		if (ids != null) {
			System.out.println();
			System.out.println("--- Updating IDs ---");
			System.out.println("Merging ChEBI ids...");
			ids = mergeIDs(ids, chemicals, true);
			System.out.println("Done.");
		}
		
		if (rewriteLog != null) {
			rewriteLog.close();
		}
		if (deletedLog != null) {
			deletedLog.close();
		}
			
		if (ids != null) {
			System.out.print("Writing ids to 'output/ids.zip'... ");
			Normalizer.writeIDs(outputLocation + "ids.map", ids);
			
			FileInputStream in = new FileInputStream(outputLocation + "ids.map");
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputLocation + "ids.zip"));
			out.putNextEntry(new ZipEntry("ids.map"));

			IOUtils.copy(in, out);
			in.close();
			out.close();
			
			System.out.println("Done.");
		}
		
		ids = null;	
		if (dictionaryFile != null) {
			System.out.println();
			System.out.println("--- Updating Dictionary ---");
			System.out.print("Writing dictionary to 'output/dict.zip'... ");
			
			updateDictionaryFile(dictionaryFile, automatonFiles);
			
			System.out.println("Done.");
		}
	}
	
	public static void updateFiles(File dictionaryFile, File idsFile, boolean removeTemporaryFiles) throws FileNotFoundException, IOException {
		File newDictFile = new File(outputLocation + "dict.zip");
		File newIdsFile = new File(outputLocation + "ids.zip");
		String extensionPattern = "(\\.[^\\./\\\\]+)+$";
		
		update(dictionaryFile, idsFile);
		
		System.out.println();
		
		if (newDictFile.exists() && dictionaryFile.exists()) {
			String oldFilePath = dictionaryFile.getPath().replaceAll(extensionPattern, "-old$1");
			
			int i = 2;
			while (new File(oldFilePath).exists()) {
				oldFilePath = dictionaryFile.getPath().replaceAll(extensionPattern, "-old" + i++ + "$1");
			}
			
			System.out.printf("Renaming '%s' to '%s'%n", dictionaryFile, oldFilePath);
			dictionaryFile.renameTo(new File(oldFilePath));
			System.out.printf("Moving '%s' to '%s'%n", newDictFile, dictionaryFile);
			newDictFile.renameTo(dictionaryFile);
		}
		
		if (newIdsFile.exists() && idsFile.exists()) {
			String oldFilePath = idsFile.getPath().replaceAll(extensionPattern, "-old$1");
			
			int i = 2;
			while (new File(oldFilePath).exists()) {
				oldFilePath = idsFile.getPath().replaceAll(extensionPattern, "-old" + i + "$1");
			}
			
			System.out.printf("Renaming '%s' to '%s'%n", idsFile, oldFilePath);
			idsFile.renameTo(new File(oldFilePath));
			System.out.printf("Moving '%s' to '%s'%n", newIdsFile, idsFile);
			newIdsFile.renameTo(idsFile);
		}
		
		if (removeTemporaryFiles) {
			File outputDir = new File(outputLocation);
			for (File file : outputDir.listFiles()) {
				file.delete();
			}
			outputDir.delete();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		update(new File(dictFilePath), new File(idsFilePath));
	}
}
