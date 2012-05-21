package de.berlin.hu.chemspot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.uimafit.factory.CollectionReaderFactory;
import org.uimafit.factory.JCasFactory;
import org.uimafit.factory.TypeSystemDescriptionFactory;

import uk.co.flamingpenguin.jewel.cli.ArgumentValidationException;
import uk.co.flamingpenguin.jewel.cli.CliFactory;

public class App {
	private static String pathToCorpora;
	private static String pathToModelFile;
	private static String pathToDictionaryFile;
	private static String pathToOutputFile;
	private static ChemSpotArguments arguments;
	private static boolean evaluate = false;
	private static String pathToTextFile;

	public static void main(String[] args) throws UIMAException, IOException {
		try {
			arguments = CliFactory.parseArguments(ChemSpotArguments.class, args);
			pathToModelFile = arguments.getPathToModelFile();
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
			} else {
				usage();
				throw new IllegalArgumentException("At least one corpus (IOB directory or a text file) should be specified!");
			}
			if (arguments.isPathToTextFile()) {
				pathToTextFile = arguments.getPathToTextFile();
			}
			evaluate = arguments.isRunEvaluation();
		} catch(ArgumentValidationException e) {
			System.out.println(e);
			usage();
		}

        //initializing ChemSpot with a CRF model file and an LINNAEUS automaton (the latter is optional)
		ChemSpot chemspot = new ChemSpot(pathToModelFile, pathToDictionaryFile);

		TypeSystemDescription typeSystem = TypeSystemDescriptionFactory.createTypeSystemDescription("desc/TypeSystem");
		
		FileWriter outputFile = null;
		if (arguments.isPathToOutputFile()) outputFile = new FileWriter(new File(pathToOutputFile));
		
		if (arguments.isPathToIOBCorpora()) {
			CollectionReader reader = CollectionReaderFactory.createCollectionReaderFromPath("desc/cr/scaiCR.xml", "InputDirectory", pathToCorpora, "UseGoldStandardAnnotations", true, "GoldstandardTypeSuffix" , "", "BrowseSubdirectories", true, "IncludeSuffixes", new String[]{"iob", "iob2"});
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

	private static void usage() {
		System.out.println("usage:");
		System.out.println("\t-c path to a directory containing corpora in IOB format (optional)");
		System.out.println("\t-t path to a text file that should be tagged (optional)");
		System.out.println("\t-m path to a CRF model file");
		System.out.println("\t-d path to a linnaeus dictionary file (optional)");
		System.out.println("\t-o path to an output file (IOB format)");
		System.out.println("\t-e if this parameter is set, the performance of ChemSpot on the IOB gold-standard corpus (cf. -c) is evaluated");	
		System.exit(0);
	}
}
