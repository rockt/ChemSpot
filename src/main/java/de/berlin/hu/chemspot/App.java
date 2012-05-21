package de.berlin.hu.chemspot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.uima.UIMAException;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.u_compare.shared.semantic.NamedEntity;
import org.uimafit.factory.CollectionReaderFactory;
import org.uimafit.factory.JCasFactory;
import org.uimafit.factory.TypeSystemDescriptionFactory;
import org.uimafit.util.JCasUtil;

import de.berlin.hu.types.PubmedDocument;

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

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UIMAException 
	 */
	
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
			}
			else if (arguments.isPathToIOBCorpora()) {
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
				serializeAnnotations(chemspot.tagGZ(pathToTextFile));
			} else {
				BufferedReader reader = new BufferedReader(new FileReader(new File(pathToTextFile)));
				String line = "";
				while ((line = reader.readLine()) != null) {
					if (outputFile != null) outputFile.write(chemspot.tagString(line));
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
	
	private static void serializeAnnotations(JCas jcas) throws IOException {
		Iterator<SourceDocumentInformation> srcIterator = JCasUtil.iterator(jcas, SourceDocumentInformation.class);
		SourceDocumentInformation src = (SourceDocumentInformation) srcIterator.next();
		String pathToFile = src.getUri().replaceFirst("file:", "") + ".chem";
		
		File file = new File(pathToFile);
		file.createNewFile();
		FileWriter writer = new FileWriter(file);

		int offset = 0;
		
		Iterator<PubmedDocument> documentIterator = JCasUtil.iterator(jcas, PubmedDocument.class);		
		while (documentIterator.hasNext()) {
			PubmedDocument document = (PubmedDocument) documentIterator.next();
			offset = document.getBegin();
			String pmid = document.getPmid();
			int numberOfEntities = 0;
			Iterator<NamedEntity> entityIterator = JCasUtil.iterator(document, NamedEntity.class, true, true);
			while (entityIterator.hasNext()) {
				NamedEntity entity = (NamedEntity) entityIterator.next();
				if (!"goldstandard".equals(entity.getSource())) {
					int begin = entity.getBegin() - offset;
					int end = entity.getEnd() - offset - 1;
					String id = entity.getId();
					String text = entity.getCoveredText();
					if (id == null || id.isEmpty()) {
						writer.write(pmid + "\t" + begin + "\t" + end + "\t" + text + "\t" + "\\N\n");
					} else {
						writer.write(pmid + "\t" + begin + "\t" + end + "\t" + text + "\t" + id + "\n");
					}					 
				}
				numberOfEntities++;
			}
			if (numberOfEntities == 0) {
				writer.write(pmid + "\t-1\t-1\t\\N\t\\N\n");
			}
		}
		writer.close();
	}
}
