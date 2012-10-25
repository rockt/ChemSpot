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

package de.berlin.hu.chemspot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.uima.UIMAException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.examples.xmi.XmiCollectionReader;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.XMLInputSource;
import org.uimafit.factory.CollectionReaderFactory;
import org.uimafit.factory.JCasFactory;

import uk.co.flamingpenguin.jewel.cli.ArgumentValidationException;
import uk.co.flamingpenguin.jewel.cli.CliFactory;

public class App {
	private static String pathToCorpora;
	private static String pathToModelFile;
	private static String pathToDictionaryFile;
	private static String pathToOutputFile;
	private static ChemSpotArguments arguments;
	private static boolean evaluate = false;
	private static boolean detailedEvaluation = false;
	private static boolean threaded = false;
	private static int threadNr = 1;
	private static String pathToTextFile;
    private static String tagFromCommandLine;
    private static String pathToSentenceFile;
    private static String pathToIDsFile;
    private static String pathToGZCorpus;
    private static String pathToCRAFTCorpus;

    public static void main(String[] args) throws UIMAException, IOException {
		try {
			arguments = CliFactory.parseArguments(ChemSpotArguments.class, args);
			pathToModelFile = arguments.getPathToCRFModelFile();
            pathToSentenceFile = arguments.getPathToSentenceModelFile();
			if (arguments.isPathToOutputFile()) {
				pathToOutputFile = arguments.getPathToOutputFile();
			}			
			if (arguments.isPathToDictionary()) {
				pathToDictionaryFile = arguments.getPathToDictionary();
			} 
			if (arguments.isPathToIDs()) {
         		pathToIDsFile = arguments.getPathToIDs();
            }
			if (arguments.isThreadNr()) {
				threaded = true;
         		threadNr = arguments.getThreadNr();
            }
			if (arguments.isPathToTextFile()) {
				pathToTextFile = arguments.getPathToTextFile();
			} else if (arguments.isPathToIOBCorpora()) {
				pathToCorpora = arguments.getPathToIOBCorpora();
            } else if (arguments.isPathToGZCorpus()) {
            	pathToGZCorpus = arguments.getPathToGZCorpus();
            } else if (arguments.isPathToCRAFTCorpus()) {
            	pathToCRAFTCorpus = arguments.getPathToCRAFTCorpus();
            } else if (arguments.isTagCommandLine()) {
                    tagFromCommandLine = arguments.getTagCommandLine();
			} else {
                usage();
                throw new IllegalArgumentException("At least one corpus (IOB directory, a text file or a command line text) should be specified!");
			}
			if (arguments.isPathToTextFile()) {
				pathToTextFile = arguments.getPathToTextFile();
			}
			detailedEvaluation = arguments.isDetailedEvaluation();
			evaluate = detailedEvaluation || arguments.isRunEvaluation();
			

		} catch(ArgumentValidationException e) {
			System.out.println(e);
			usage();
            System.exit(0);
		}

        //initializing ChemSpot with a CRF model file and an LINNAEUS automaton (the latter is optional)
        ChemSpot chemspot = new ChemSpot(pathToModelFile, pathToDictionaryFile, pathToSentenceFile, pathToIDsFile);

        TypeSystemDescription typeSystem = UIMAFramework.getXMLParser().parseTypeSystemDescription(new XMLInputSource(chemspot.getClass().getClassLoader().getResource("desc/TypeSystem.xml")));

        if (tagFromCommandLine != null) {
            List<Mention> mentions = chemspot.tag(tagFromCommandLine);
            for (Mention mention : mentions) {
                System.out.printf("%d\t%d\t%s\t%s\t%s\n",
                     mention.getStart(), mention.getEnd(), mention.getText(),
                     mention.getCHID(), mention.getSource());
            }
        } else {
            FileWriter outputFile = null;
            if (arguments.isPathToOutputFile()) outputFile = new FileWriter(new File(pathToOutputFile));

            if (arguments.isPathToIOBCorpora() || arguments.isPathToGZCorpus() || arguments.isPathToCRAFTCorpus()) {
                //CollectionReader reader = CollectionReaderFactory.createCollectionReaderFromPath("desc/cr/ScaiCorpusCR.xml", "InputDirectory", pathToCorpora, "UseGoldStandardAnnotations", true, "GoldstandardTypeSuffix" , "", "BrowseSubdirectories", true, "IncludeSuffixes", new String[]{"iob", "iob2"});
            	CollectionReader reader = null;
            	if (arguments.isPathToIOBCorpora()) {
                	reader = CollectionReaderFactory.createCollectionReader(UIMAFramework.getXMLParser().parseCollectionReaderDescription(new XMLInputSource(typeSystem.getClass().getClassLoader()
                            .getResource("desc/cr/ScaiCorpusCR.xml"))), "InputDirectory", pathToCorpora, "UseGoldStandardAnnotations", true, "GoldstandardTypeSuffix" , "", "BrowseSubdirectories", true, "IncludeSuffixes", new String[]{"iob", "iob2"});
                } else if (arguments.isPathToGZCorpus()) {
                	reader = CollectionReaderFactory.createCollectionReader(UIMAFramework.getXMLParser().parseCollectionReaderDescription(new XMLInputSource(typeSystem.getClass().getClassLoader()
                            .getResource("desc/cr/ZipFileCR.xml"))), "InputDirectory", pathToGZCorpus);
                } else if (arguments.isPathToCRAFTCorpus()) {
            		reader = CollectionReaderFactory.createCollectionReader(UIMAFramework.getXMLParser().parseCollectionReaderDescription(new XMLInputSource(typeSystem.getClass().getClassLoader()
                            .getResource("desc/cr/CraftCR.xml"))), XmiCollectionReader.PARAM_INPUTDIR, pathToCRAFTCorpus);
            	}

            	tagCollection(chemspot, typeSystem, reader, threaded, threadNr);
            } else {
                if (arguments.isZippedTextFile()) {
                    chemspot.tagGZ(pathToTextFile);
                } else {
                    BufferedReader reader = new BufferedReader(new FileReader(new File(pathToTextFile)));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (outputFile != null) outputFile.write(chemspot.tagReturnIOB(line));
                    }
                }
            }
            if (outputFile != null) outputFile.close();
        }
	}
    
    private static void tagCollection(ChemSpot chemspot, TypeSystemDescription typeSystem, CollectionReader reader, boolean threaded, int threads) throws CollectionException, UIMAException, IOException {
    	ExecutorService threadPool = threaded ? Executors.newFixedThreadPool(threads) : null;
    	int runNr = 1;
    	
    	while (reader.hasNext()) {
            JCas jcas = JCasFactory.createJCas(typeSystem);
            reader.getNext(jcas.getCas());
            
            if (threaded) {
	            ChemSpotRun run = new ChemSpotRun(runNr++, chemspot, jcas, arguments.isSerializeOutputPath());
	            threadPool.submit(run);
            } else {
            	String output = chemspot.tagJCas(jcas, evaluate, evaluate, detailedEvaluation);
            	
            	FileWriter outputFile = arguments.isPathToOutputFile() ? new FileWriter(new File(pathToOutputFile)) : null;
                if (outputFile != null) outputFile.write(output);
            }
        }
    	
    	if (threaded) {
    		threadPool.shutdown();
    	}
    }
    
    private static class ChemSpotRun implements Runnable {
    	private int runNr = -1;
    	private ChemSpot chemspot = null;
    	private JCas jCas = null;
    	private boolean serialize = false;
    	
    	public ChemSpotRun (int runNr, ChemSpot chemspot, JCas jCas, boolean serialize) {
    		this.runNr = runNr;
    		this.chemspot = chemspot;
    		this.jCas = jCas;
    		this.serialize = serialize;
    	}
    	
		public void run() {
			try {
				System.out.println("Starting run " + runNr);
				String output = chemspot.tagJCas(jCas, evaluate, evaluate, detailedEvaluation);
				FileWriter outputFile = arguments.isPathToOutputFile() ? new FileWriter(new File(pathToOutputFile)) : null;
                if (outputFile != null) outputFile.write(output);
				if (serialize) {
					ChemSpot.serializeAnnotations(jCas, arguments.getSerializeOutputPath());
				}
				System.out.println("Run " + runNr + " finished");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (AnalysisEngineProcessException e) {
				e.printStackTrace();
			}
		}
    	
    }

	private static void usage() {
		System.out.println("usage:");
		System.out.println("  mandatory arguments:");
        System.out.println("\t-m path to a CRF model file");
        System.out.println("\t-s path to a OpenNLP sentence model file");
        System.out.println();
        System.out.println("  optional arguments:");
        System.out.println("\t-d path to a zipped set of brics dictionary automata");
        System.out.println("\t-i path to a zipped tab-separated text file representing a map of terms to ids");
        System.out.println("\t-e if this parameter is set, the performance of ChemSpot on a IOB gold-standard corpus (cf. -c) or CRAFT corpus is evaluated");
        System.out.println("\t-T number of threads to create when processing a document collection");
        System.out.println();
        System.out.println("  input control:");
		System.out.println("\t-c path to a directory containing corpora in IOB format that should be tagged");
		System.out.println("\t-C path to a directory containing CRAFT corpus files in UIMA XMI format that should be tagged");
		System.out.println("\t-g path to a directory containing gzipped text files that should be tagged");
		System.out.println("\t-t path to a text file that should be tagged");
		System.out.println();
        System.out.println("  output control:");
		System.out.println("\t-o for a single document: path to an output file (IOB format)");
		System.out.println("\t-S for a document collection: path to which serialized annotation files should be written (tsv format)");
        System.exit(0);
	}
}
