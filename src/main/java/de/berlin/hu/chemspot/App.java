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

import org.apache.uima.UIMAException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.XMLInputSource;
import org.uimafit.factory.CollectionReaderFactory;
import org.uimafit.factory.JCasFactory;
import uk.co.flamingpenguin.jewel.cli.ArgumentValidationException;
import uk.co.flamingpenguin.jewel.cli.CliFactory;

import java.io.*;
import java.util.List;

public class App {
	private static String pathToCorpora;
	private static String pathToModelFile;
	private static String pathToDictionaryFile;
	private static String pathToOutputFile;
	private static ChemSpotArguments arguments;
	private static boolean evaluate = false;
	private static String pathToTextFile;
    private static String tagFromCommandLine;
    private static String pathToSentenceFile;

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
			if (arguments.isPathToTextFile()) {
				pathToTextFile = arguments.getPathToTextFile();
			} else if (arguments.isPathToIOBCorpora()) {
				pathToCorpora = arguments.getPathToIOBCorpora();
            } else if (arguments.isTagCommandLine()) {
                    tagFromCommandLine = arguments.getTagCommandLine();
			} else {
                usage();
                throw new IllegalArgumentException("At least one corpus (IOB directory, a text file or a command line text) should be specified!");
			}
			if (arguments.isPathToTextFile()) {
				pathToTextFile = arguments.getPathToTextFile();
			}
			evaluate = arguments.isRunEvaluation();

		} catch(ArgumentValidationException e) {
			System.out.println(e);
			usage();
            System.exit(0);
		}

        //initializing ChemSpot with a CRF model file and an LINNAEUS automaton (the latter is optional)
		ChemSpot chemspot = new ChemSpot(pathToModelFile, pathToDictionaryFile, pathToSentenceFile);

        TypeSystemDescription typeSystem = UIMAFramework.getXMLParser().parseTypeSystemDescription(new XMLInputSource(chemspot.getClass().getClassLoader().getResource("desc/TypeSystem.xml")));

        if (tagFromCommandLine != null) {
            List<Mention> mentions = chemspot.tag(tagFromCommandLine);
            for (Mention mention : mentions) {
                System.out.printf("%d\t%d\t%s\t%s\t%s\n",
                     mention.getStart(), mention.getEnd(), mention.getText(),
                     mention.getId(), mention.getSource());
            }
        } else {
            FileWriter outputFile = null;
            if (arguments.isPathToOutputFile()) outputFile = new FileWriter(new File(pathToOutputFile));

            if (arguments.isPathToIOBCorpora()) {
                //CollectionReader reader = CollectionReaderFactory.createCollectionReaderFromPath("desc/cr/ScaiCorpusCR.xml", "InputDirectory", pathToCorpora, "UseGoldStandardAnnotations", true, "GoldstandardTypeSuffix" , "", "BrowseSubdirectories", true, "IncludeSuffixes", new String[]{"iob", "iob2"});
                CollectionReader reader = CollectionReaderFactory.createCollectionReader(UIMAFramework.getXMLParser().parseCollectionReaderDescription(new XMLInputSource(typeSystem.getClass().getClassLoader()
                        .getResource("desc/cr/ScaiCorpusCR.xml"))), "InputDirectory", pathToCorpora, "UseGoldStandardAnnotations", true, "GoldstandardTypeSuffix" , "SUM", "BrowseSubdirectories", true, "IncludeSuffixes", new String[]{"iob", "iob2"});

                while (reader.hasNext()) {
                    JCas jcas = JCasFactory.createJCas(typeSystem);
                    reader.getNext(jcas.getCas());
                    String output = chemspot.tagJCas(jcas, evaluate, true);
                    if (outputFile != null) outputFile.write(output);
                }
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

	private static void usage() {
		System.out.println("usage:");
		System.out.println("\t-c path to a directory containing corpora in IOB format (optional)");
		System.out.println("\t-t path to a text file that should be tagged (optional)");
		System.out.println("\t-m path to a CRF model file");
        System.out.println("\t-s path to a OpenNLP sentence model file");
		System.out.println("\t-d path to a linnaeus dictionary file (optional)");
		System.out.println("\t-o path to an output file (IOB format)");
		System.out.println("\t-e if this parameter is set, the performance of ChemSpot on the IOB gold-standard corpus (cf. -c) is evaluated");	System.exit(0);
	}
}
