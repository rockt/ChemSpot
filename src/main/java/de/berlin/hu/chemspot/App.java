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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.uima.UIMAException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.examples.xmi.XmiCollectionReader;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.XMLInputSource;
import org.apache.uima.util.XMLSerializer;
import org.u_compare.shared.semantic.NamedEntity;
import org.uimafit.factory.CollectionReaderFactory;
import org.uimafit.factory.JCasFactory;
import org.uimafit.util.JCasUtil;
import org.xml.sax.SAXException;

import de.berlin.hu.types.PubmedDocument;
import de.berlin.hu.uima.cr.ddi.DDICorpusCR;
import de.berlin.hu.util.Constants;

import uk.co.flamingpenguin.jewel.cli.ArgumentValidationException;
import uk.co.flamingpenguin.jewel.cli.CliFactory;

public class App {
	private static String pathToCorpora;
	private static String pathToModelFile;
	private static String pathToDictionaryFile;
	private static String pathToOutputFile;
	private static String pathToCRAFTCorpus;
	private static String pathToNaCTeMCorpus;
	private static String pathToPatentCorpus;
	private static boolean convertToIOB = true;
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
    private static String pathToXMICorpus;
    private static String pathToXMIOutput;
    private static String pathToDDICorpus;

    public static void main(String[] args) throws UIMAException, IOException {
		try {
			// read arguments
			arguments = CliFactory.parseArguments(ChemSpotArguments.class, args);
			pathToModelFile = arguments.getPathToCRFModelFile();
            pathToSentenceFile = arguments.getPathToSentenceModelFile();
			if (arguments.isPathToOutputFile()) {
				pathToOutputFile = arguments.getPathToOutputFile();
			}
			if (arguments.isPathToXMIOutput()) {
				pathToXMIOutput = arguments.getPathToXMIOutput();
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
			} else if (arguments.isPathToXMICorpus()) {
				pathToXMICorpus = arguments.getPathToXMICorpus();
			} else if (arguments.isPathToNaCTeMCorpus()) {
				pathToNaCTeMCorpus = arguments.getPathToNaCTeMCorpus();
			} else if (arguments.isPathToPatentCorpus()) {
				pathToPatentCorpus = arguments.getPathToPatentCorpus();
			} else if (arguments.isPathToDDICorpus()) {
				pathToDDICorpus = arguments.getPathToDDICorpus();
			}  else {
                usage();
                throw new IllegalArgumentException("At least one corpus (IOB directory, a text file or a command line text) should be specified!");
			}
			if (arguments.isPathToTextFile()) {
				pathToTextFile = arguments.getPathToTextFile();
			}
			detailedEvaluation = arguments.isDetailedEvaluation();
			evaluate = detailedEvaluation || arguments.isRunEvaluation();
			convertToIOB = arguments.isConvertToIOB();
		} catch(ArgumentValidationException e) {
			System.out.println(e);
			usage();
            System.exit(0);
		}

        //initializing ChemSpot with a CRF model file and an LINNAEUS automaton (the latter is optional)
        ChemSpot chemspot = new ChemSpot(pathToModelFile, pathToDictionaryFile, pathToSentenceFile, pathToIDsFile);

        TypeSystemDescription typeSystem = UIMAFramework.getXMLParser().parseTypeSystemDescription(new XMLInputSource(chemspot.getClass().getClassLoader().getResource("desc/TypeSystem.xml")));

        // tag from command line
        if (tagFromCommandLine != null) {
            List<Mention> mentions = runChemSpot(chemspot, typeSystem, tagFromCommandLine, pathToOutputFile, false);
            for (Mention mention : mentions) {
                System.out.printf("%d\t%d\t%s\t%s\t%s\n",
                     mention.getStart(), mention.getEnd(), mention.getText(),
                     mention.getCHID(), mention.getSource());
            }
        } else {
        	// tag document collection
            if (arguments.isPathToIOBCorpora() || arguments.isPathToGZCorpus() || arguments.isPathToCRAFTCorpus() || arguments.isPathToXMICorpus() || arguments.isPathToNaCTeMCorpus() || arguments.isPathToPatentCorpus() || arguments.isPathToDDICorpus()) {
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
            	} else if (arguments.isPathToNaCTeMCorpus()) {
            		reader = CollectionReaderFactory.createCollectionReader(UIMAFramework.getXMLParser().parseCollectionReaderDescription(new XMLInputSource(typeSystem.getClass().getClassLoader()
                            .getResource("desc/cr/NaCTeMCollectionReader.xml"))), XmiCollectionReader.PARAM_INPUTDIR, pathToNaCTeMCorpus);
            	} else if (arguments.isPathToPatentCorpus()) {
            		reader = CollectionReaderFactory.createCollectionReader(UIMAFramework.getXMLParser().parseCollectionReaderDescription(new XMLInputSource(typeSystem.getClass().getClassLoader()
                            .getResource("desc/cr/PatentCorpusCollectionReader.xml"))), XmiCollectionReader.PARAM_INPUTDIR, pathToPatentCorpus);
            	} else if (arguments.isPathToXMICorpus()) {
            		reader = CollectionReaderFactory.createCollectionReader(XmiCollectionReader.class, XmiCollectionReader.PARAM_INPUTDIR, pathToXMICorpus);
            	} else if (arguments.isPathToDDICorpus()) {
            		reader = CollectionReaderFactory.createCollectionReader(UIMAFramework.getXMLParser().parseCollectionReaderDescription(new XMLInputSource(typeSystem.getClass().getClassLoader()
                            .getResource("desc/cr/DDICorpusCR.xml"))), DDICorpusCR.PARAM_INPUTDIR, pathToDDICorpus, DDICorpusCR.PARAM_SUBDIR, true);
            	}

            	tagCollection(chemspot, typeSystem, reader, threaded, threadNr);
            // tag single file
            } else {
            	JCas jcas = JCasFactory.createJCas(typeSystem);
            	
                if (arguments.isZippedTextFile()) {
                    ChemSpot.readGZFile(jcas, pathToTextFile);
                } else {
                    ChemSpot.readFile(jcas, pathToTextFile);
                }
            	
            	runChemSpot(chemspot, jcas, pathToOutputFile, false);
            }   
        }
	}
    
    private static List<Mention> runChemSpot(ChemSpot chemspot, TypeSystemDescription typeSystem, String text, String outputPath, boolean evaluate) {
    	JCas jcas;
		try {
			jcas = JCasFactory.createJCas(typeSystem);
		} catch (UIMAException e) {
			e.printStackTrace();
			return new ArrayList<Mention>();
		}
        jcas.setDocumentText(text);
        PubmedDocument pd = new PubmedDocument(jcas);
        pd.setBegin(0);
        pd.setEnd(text.length());
        pd.setPmid("");
        pd.addToIndexes(jcas);
        return runChemSpot(chemspot, jcas, outputPath, evaluate);
    }
    
    private static List<NamedEntity> removeOtherEntities(JCas jcas) {
    	List<NamedEntity> result = new ArrayList<NamedEntity>();
    	List<String> sources = new ArrayList<String>();
    	
    	Iterator<NamedEntity> entities = JCasUtil.iterator(jcas, NamedEntity.class);
        while (entities.hasNext()) {
        	NamedEntity entity = entities.next();
        	if (Constants.GOLDSTANDARD.equals(entity.getSource())) continue;
        	if (!sources.contains(entity.getSource())) sources.add(entity.getSource());
        	result.add(entity);
        }
        
        for (NamedEntity ne : result) {
			ne.removeFromIndexes();
		}
        
        if (!sources.isEmpty()) {
        	System.out.println("found pre-exisiting entities from: " + sources);
        }
    	
    	return result;
    }
    
    private static ChemicalNEREvaluator otherEvaluator = new ChemicalNEREvaluator();
    private static List<Mention> runChemSpot(ChemSpot chemspot, JCas jcas, String outputPath, boolean evaluate) {
    	boolean hasOtherEntities = JCasUtil.iterator(jcas, NamedEntity.class).hasNext();
    	if (hasOtherEntities) {
    		System.out.println("Pre-existing entities found in document. Evaluating and removing them.");
    		otherEvaluator.evaluate(jcas);
    		removeOtherEntities(jcas);
    	}
    	
    	List<Mention> mentions = chemspot.tag(jcas);
    	if (evaluate) {
    		chemspot.getEvaluator().evaluate(jcas);
    	}
    	
    	if (pathToOutputFile != null && outputPath != null) {
	    	String output = convertToIOB ? ChemSpot.convertToIOB(jcas) : ChemSpot.serializeAnnotations(jcas);
	    	try {
		    	FileWriter outputFile = outputPath != null ? new FileWriter(new File(outputPath)) : null;
		        if (outputFile != null) {
		        	outputFile.write(output);
		        	System.out.println("\nOutput written to: " + outputPath);
		        	outputFile.close();
		        }
		        
	    	} catch (IOException e) {
	    		System.err.println("Error while writing ChemSpot output");
	    		e.printStackTrace();
	    	}
    	}
    	
    	if (pathToXMIOutput != null && outputPath != null) {
    		try {
    			pathToXMIOutput += !pathToXMIOutput.endsWith("/") && !pathToXMIOutput.endsWith("\\") ? "/" : "";
    			File xmiOutputFile = new File(pathToXMIOutput + outputPath.replaceFirst(".*/", "").replaceFirst("\\.[^\\.]+$", "") + ".xmi");
    			xmiOutputFile.getParentFile().mkdirs();
	    		OutputStream out = new FileOutputStream(xmiOutputFile);
	    		XmiCasSerializer serializer = new XmiCasSerializer(jcas.getTypeSystem());
	    		XMLSerializer xmlSerializer = new XMLSerializer(out, false);
    		
				serializer.serialize(jcas.getCas(), xmlSerializer.getContentHandler());
				out.close();
				
				System.out.println("XMI file written to: " + xmiOutputFile.getCanonicalPath());
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	return mentions;
    }
    
    private static void tagCollection(ChemSpot chemspot, TypeSystemDescription typeSystem, CollectionReader reader, boolean threaded, int threads) throws CollectionException, UIMAException, IOException {
    	ExecutorService threadPool = threaded ? Executors.newFixedThreadPool(threads) : null;
    	int runNr = 1;
    	
    	// determine output path (if there is one) and separate it into directory and filename
    	File outputPath = pathToOutputFile != null ? new File(pathToOutputFile) : (pathToXMIOutput != null ? new File(pathToOutputFile) : null);
    	String filename = null;
    	String outputPathString = null;
    	if (outputPath != null) {
    		if (outputPath.getName().contains(".")) {
        		filename = outputPath.getName();
        		outputPath = outputPath.getAbsoluteFile().getParentFile();
        	}
        	
        	if (!outputPath.exists()) {
        		outputPath.mkdirs();
        	}
        	outputPathString = outputPath.getCanonicalPath().replaceAll("\\\\", "/");
            outputPathString = !outputPathString.endsWith("/") ? outputPathString + "/" : outputPathString;
    	}
    	
    	while (reader.hasNext()) {
            JCas jcas = JCasFactory.createJCas(typeSystem);
            reader.getNext(jcas.getCas());
            
            String outputFilePath = null;
            String fileType = convertToIOB ? ".iob" : ".chem";
            
            // prepare output file
            if (outputPath != null) {
	            Iterator<SourceDocumentInformation> srcIterator = JCasUtil.iterator(jcas, SourceDocumentInformation.class);
	            if (filename == null && srcIterator.hasNext()) {
	    	        SourceDocumentInformation src = srcIterator.next();
	    	        outputFilePath = src.getUri().replaceFirst(".*/", outputPathString) + fileType;
	            } else {
	            	if (runNr == 1 && !reader.hasNext()) {
	            		outputFilePath = outputPathString + filename;
	            	} else {
	            		String prefix = "";
	            		if (filename != null) {
	            			int prefixPos = filename.indexOf('.') > -1 ? filename.indexOf('.') : filename.length();
	            			prefix = filename != null ? filename.substring(0, prefixPos) + "-" : "";
	            		}
		            	outputFilePath = String.format("%s%s%04d%s", outputPathString, prefix, runNr, fileType);
	            	}
	            }
            }

            // run ChemSpot threaded or...
            if (threaded) {
	            ChemSpotRun run = new ChemSpotRun(runNr, chemspot, jcas, outputFilePath, evaluate);
	            threadPool.submit(run);
	        // non-threaded
            } else {
            	runChemSpot(chemspot, jcas, outputFilePath, evaluate);
            }
            
            runNr++;
        }
    	
    	if (threaded) {
    		// shut down thread pool and block until termination
    		try {
    			threadPool.shutdown();
				threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
    	if (detailedEvaluation) {
    		chemspot.getEvaluator().writeDetailedEvaluationResults(outputPathString);
    		
    		if (otherEvaluator.getTP() + otherEvaluator.getFN() + otherEvaluator.getFP() > 0) {
    			List<Mention> normalizedAll = new ArrayList<Mention>(otherEvaluator.getNormalizedAll());
    			List<Mention> normalized = new ArrayList<Mention>(otherEvaluator.getNormalized());
    			List<Mention> normalizedCorrect = new ArrayList<Mention>(otherEvaluator.getNormalizedCorrect());
    			
    			normalized.retainAll(chemspot.getEvaluator().getNormalizedCorrect());
    			normalizedAll.retainAll(chemspot.getEvaluator().getNormalizedCorrect());
    			normalizedCorrect.retainAll(chemspot.getEvaluator().getNormalizedCorrect());
    			
    			File normalizedFoundFile = new File(outputPathString + "normalizations-correct-by-ChemSpot.txt");
    			FileOutputStream writer = new FileOutputStream(normalizedFoundFile);
	    		
	    	    otherEvaluator.writeNormalizations(writer, normalizedAll, normalized, normalizedCorrect);

	    		writer.close();
	    		System.out.println("Pre-existing normalized entities found by ChemSpot written to: " + normalizedFoundFile.getName());
	    		
    			normalizedAll = new ArrayList<Mention>(otherEvaluator.getNormalizedAll());
    			normalized = new ArrayList<Mention>(otherEvaluator.getNormalized());
    			normalizedCorrect = new ArrayList<Mention>(otherEvaluator.getNormalizedCorrect());
    			
    			normalized.removeAll(chemspot.getEvaluator().getNormalizedCorrect());
    			normalizedAll.removeAll(chemspot.getEvaluator().getNormalizedCorrect());
    			normalizedCorrect.removeAll(chemspot.getEvaluator().getNormalizedCorrect());
    			
    			File notNormalizedFoundFile = new File(outputPathString + "normalizations-not-correct-by-ChemSpot.txt");
    			writer = new FileOutputStream(notNormalizedFoundFile);
	    		
	    	    otherEvaluator.writeNormalizations(writer, normalizedAll, normalized, normalizedCorrect);

	    		writer.close();
	    		System.out.println("Pre-existing normalized entities not found by ChemSpot written to: " + notNormalizedFoundFile.getName());
    		}
    	}
    }
    
    private static class ChemSpotRun implements Runnable {
    	private int runNr = -1;
    	private ChemSpot chemspot = null;
    	private JCas jCas = null;
    	private String outputFile;
    	private boolean evaluate;
    	
    	public ChemSpotRun (int runNr, ChemSpot chemspot, JCas jCas, String outputFile, boolean evaluate) {
    		this.runNr = runNr;
    		this.chemspot = chemspot;
    		this.jCas = jCas;
    		this.outputFile = outputFile;
    		this.evaluate = evaluate;
    	}
    	
		public void run() {
			System.out.println("Starting run " + runNr);
			runChemSpot(chemspot, jCas, outputFile, evaluate);
			System.out.println("Run " + runNr + " finished");
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
		System.out.println("\t-o path to output file");
		System.out.println("\t-I set if output should be converted to IOB format");
        System.exit(0);
	}
}
