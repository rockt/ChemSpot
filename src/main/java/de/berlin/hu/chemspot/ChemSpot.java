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

import de.berlin.hu.types.PubmedDocument;
import de.berlin.hu.uima.cc.eval.ComparableAnnotation;
import de.berlin.hu.wbi.common.research.Evaluator;
import org.apache.uima.UIMAException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.impl.cpm.Constants;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.XMLInputSource;
import org.u_compare.shared.semantic.NamedEntity;
import org.u_compare.shared.syntactic.Token;
import org.uimafit.factory.AnalysisEngineFactory;
import org.uimafit.factory.JCasFactory;
import org.uimafit.util.JCasUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

public class ChemSpot {
    private TypeSystemDescription typeSystem;
    private AnalysisEngine sentenceDetector;
    private AnalysisEngine sentenceConverter;
    private AnalysisEngine crfTagger;
    private AnalysisEngine dictionaryTagger;
    private AnalysisEngine chemicalFormulaTagger;
    private AnalysisEngine abbrevTagger;
    private AnalysisEngine annotationMerger;
    private AnalysisEngine fineTokenizer;
    private AnalysisEngine stopwordFilter;
    private AnalysisEngine normalizer;

    /**
     * Initializes ChemSpot without a dictionary automaton and a normalizer.
     * @param pathToCRFModelFile the Path to a CRF model
     */
    public ChemSpot(String pathToCRFModelFile, String pathToSentenceModelFile) {
        new ChemSpot(pathToCRFModelFile, null, pathToSentenceModelFile, null);
    }

    /**
     * Initializes ChemSpot without a normalizer.
     * @param pathToCRFModelFile the Path to a CRF model
     */
    public ChemSpot(String pathToCRFModelFile, String pathToDictionaryFile, String pathToSentenceModelFile) {
        new ChemSpot(pathToCRFModelFile, pathToDictionaryFile, pathToSentenceModelFile, null);
    }

    /**
     * Initializes ChemSpot with a CRF model, an OpenNLP sentence model and a dictionary automaton.
     * @param pathToCRFModelFile the path to a CRF model
     * @param pathToDictionaryFile the ath to a dictionary automaton
     */
    public ChemSpot(String pathToCRFModelFile, String pathToDictionaryFile, String pathToSentenceModelFile, String pathToIDs) {
        try {
            typeSystem = UIMAFramework.getXMLParser().parseTypeSystemDescription(new XMLInputSource(this.getClass().getClassLoader().getResource("desc/TypeSystem.xml")));
            fineTokenizer = AnalysisEngineFactory.createAnalysisEngine(UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(this.getClass().getClassLoader()
                    .getResource("desc/ae/tokenizer/FineGrainedTokenizerAE.xml"))), CAS.NAME_DEFAULT_SOFA);
            sentenceDetector = AnalysisEngineFactory.createPrimitive(UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(this.getClass().getClassLoader()
                    .getResource("desc/ae/tagger/opennlp/SentenceDetector.xml"))), "opennlp.uima.ModelName", pathToSentenceModelFile);
            sentenceConverter = AnalysisEngineFactory.createAnalysisEngine(UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(this.getClass().getClassLoader()
                    .getResource("desc/ae/converter/OpenNLPToUCompareSentenceConverterAE.xml"))), CAS.NAME_DEFAULT_SOFA);
            System.out.println("Loading CRF...");
            crfTagger = AnalysisEngineFactory.createPrimitive(UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(this.getClass().getClassLoader()
                    .getResource("desc/banner/tagger/BANNERTaggerAE.xml"))),  "BannerModelFile", pathToCRFModelFile);
            if (pathToDictionaryFile != null) {
                System.out.println("Loading dictionary...");
                dictionaryTagger = AnalysisEngineFactory.createPrimitive(UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(this.getClass().getClassLoader()
                        .getResource("desc/ae/tagger/BricsTaggerAE.xml"))), "DrugBankMatcherDictionaryAutomat", pathToDictionaryFile);
            } else System.out.println("No dictionary location specified! Tagging without dictionary...");
            chemicalFormulaTagger = AnalysisEngineFactory.createAnalysisEngine(UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(this.getClass().getClassLoader()
                    .getResource("desc/ae/tagger/ChemicalFormulaTaggerAE.xml"))), CAS.NAME_DEFAULT_SOFA);
            abbrevTagger = AnalysisEngineFactory.createAnalysisEngine(UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(this.getClass().getClassLoader()
                    .getResource("desc/ae/tagger/AbbreviationTaggerAE.xml"))), CAS.NAME_DEFAULT_SOFA);
            annotationMerger = AnalysisEngineFactory.createAnalysisEngine(UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(this.getClass().getClassLoader()
                    .getResource("desc/ae/AnnotationMergerAE.xml"))), CAS.NAME_DEFAULT_SOFA);
            if (pathToIDs != null) {
                normalizer = AnalysisEngineFactory.createPrimitive(UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(this.getClass().getClassLoader()
                                        .getResource("desc/ae/normalizer/NormalizerAE.xml"))), "PathToIDs", pathToIDs);
            } else System.out.println("No location for ids specified! Tagging without subsequent normalization...");
            stopwordFilter = AnalysisEngineFactory.createAnalysisEngine(UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(this.getClass().getClassLoader()
                    .getResource("desc/ae/filter/StopwordFilterAE.xml"))), CAS.NAME_DEFAULT_SOFA);
            System.out.println("Finished initializing ChemSpot.");
        } catch (UIMAException e) {
            System.err.println("Failed initializing ChemSpot.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Failed initializing ChemSpot.");
            e.printStackTrace();
        }
    }

    /**
     * Finds chemical entities in the document of a {@code JCas} object and returns a list of mentions.
     * @param jcas contains the document text
     * @return a list of mentions
     */
    private List<Mention> tag(JCas jcas) {
        try {
            fineTokenizer.process(jcas);
            sentenceDetector.process(jcas);
            sentenceConverter.process(jcas);
            crfTagger.process(jcas);
            if (dictionaryTagger != null) dictionaryTagger.process(jcas);
            annotationMerger.process(jcas);
            stopwordFilter.process(jcas);
            if (normalizer != null) normalizer.process(jcas);

            List<Mention> mentions = new ArrayList<Mention>();
            Iterator<NamedEntity> entities = JCasUtil.iterator(jcas, NamedEntity.class);
            while (entities.hasNext()) {
                NamedEntity entity = entities.next();
                //disregards gold-standard mentions
                if (!"goldstandard".equals(entity.getSource())) {
                    mentions.add(new Mention(entity.getBegin(), entity.getEnd(), entity.getCoveredText(), entity.getId(), entity.getSource()));
                }
            }

            return mentions;
        } catch (AnalysisEngineProcessException e) {
            System.err.println("Failed to extract chemicals from text.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Finds chemical entities in a {@code text} and returns a list of mentions.
     * @param text natural language text from which ChemSpot shall extract chemical entities
     * @return a list of mentions
     */
    public List<Mention> tag(String text) {
        try {
            JCas jcas = JCasFactory.createJCas(typeSystem);
            jcas.setDocumentText(text);
            PubmedDocument pd = new PubmedDocument(jcas);
            pd.setBegin(0);
            pd.setEnd(text.length());
            pd.setPmid("");
            pd.addToIndexes(jcas);
            return tag(jcas);
        } catch (UIMAException e) {
            System.err.println("Failed to extract chemicals from text.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Finds chemical entities in a {@code text} and returns the output in IOB format.
     * @param text natural language text from which you want to extract chemical entities
     * @return a string representing the output in IOB format
     * @throws UIMAException
     */
    public String tagReturnIOB(String text) throws UIMAException {
        JCas jcas = JCasFactory.createJCas(typeSystem);
        jcas.setDocumentText(text);
        PubmedDocument pd = new PubmedDocument(jcas);
        pd.setBegin(0);
        pd.setEnd(text.length());
        pd.setPmid("");
        pd.addToIndexes(jcas);
        return tagJCas(jcas, false, true, false);
    }

    /**
     * Finds chemical entities in a zipped file containing a corpus in MutationFinder format. Subsequently, annotations are written in "{@code pathToGZ}".chemical using the same format.
     * @param pathToGZ the path to a zipped MutationFinder corpus.
     * @throws UIMAException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void tagGZ(String pathToGZ) throws UIMAException, IOException {
        JCas jcas = JCasFactory.createJCas(typeSystem);
        readGZFile(jcas, pathToGZ);
        tagJCas(jcas, false, false, false);
        Iterator<NamedEntity> annotations = JCasUtil.iterator(jcas, NamedEntity.class);
        while (annotations.hasNext()) {
            NamedEntity entity = annotations.next();
            entity.setEnd(entity.getEnd());
        }
        serializeAnnotations(jcas, null);
    }

    /**
     * Finds chemical entities in the document text of a {@code JCas} object. If gold-standard annotations were provided, it can calculate precision, recall and F measure or return the annotated text in IOB format.
     * @param jcas contains the document text
     * @param evaluate whether to evaluate if gold-standard entities were provided
     * @param convertToIOB whether to return a String representing annotations in IOB format
     * @return a string representing the output in IOB format
     * @throws AnalysisEngineProcessException
     */
    //FIXME: split this method into two parts: tagging and writing IOB
    public String tagJCas(JCas jcas, boolean evaluate, boolean convertToIOB, boolean detailed) throws AnalysisEngineProcessException {
        //TODO change to buffered string builder!
        StringBuilder sb = new StringBuilder();
        fineTokenizer.process(jcas);
        synchronized (this) {
        	sentenceDetector.process(jcas);
        }
        sentenceConverter.process(jcas);
        crfTagger.process(jcas);
        if (dictionaryTagger != null) dictionaryTagger.process(jcas);
        chemicalFormulaTagger.process(jcas);
        abbrevTagger.process(jcas);
        annotationMerger.process(jcas);
        stopwordFilter.process(jcas);
        if (normalizer != null) normalizer.process(jcas);

        HashMap<String, ArrayList<NamedEntity>> goldAnnotations = new HashMap<String, ArrayList<NamedEntity>>();
        HashMap<String, ArrayList<NamedEntity>> pipelineAnnotations = new HashMap<String, ArrayList<NamedEntity>>();

        if (convertToIOB) {
            System.out.println("Converting annotations to IOB format...");
            Iterator<PubmedDocument> abstracts = JCasUtil.iterator(jcas, PubmedDocument.class);

            while (abstracts.hasNext()) {
                PubmedDocument pubmedAbstract = abstracts.next();
                sb.append("### ").append(pubmedAbstract.getPmid()).append("\n");
                int offset = pubmedAbstract.getBegin();
                String pmid = pubmedAbstract.getPmid();

                List<Token> tokens = JCasUtil.selectCovered(Token.class, pubmedAbstract);
                for (Token token : tokens) {
                    token.setLabel("O");
                }

                List<NamedEntity> entities = JCasUtil.selectCovered(NamedEntity.class, pubmedAbstract);
                for (NamedEntity entity : entities) {
                    int firstTokenBegin = 0;
                    int lastTokenEnd = 0;

                    String id = entity.getId();
                    if (id == null) id = "";
                    if (!"goldstandard".equals(entity.getSource())) {
                        if (pipelineAnnotations.containsKey(pmid)) {
                            pipelineAnnotations.get(pmid).add(entity);
                        } else {
                            ArrayList<NamedEntity> tempArray = new ArrayList<NamedEntity>();
                            tempArray.add(entity);
                            pipelineAnnotations.put(pmid, tempArray);
                        }
                        List<Token> entityTokens = JCasUtil.selectCovered(Token.class, entity);
                        boolean first = true;
                        for (Token token : entityTokens) {
                            if (first) {
                                if (id.isEmpty()) token.setLabel("B-CHEMICAL"); else token.setLabel("B-CHEMICAL" + "\t" + id);
                                first = false;
                                firstTokenBegin = token.getBegin();
                            } else {
                                token.setLabel("I-CHEMICAL" + "\t" + id);
                            }
                            lastTokenEnd = token.getEnd();
                        }
                        assert entity.getBegin() == firstTokenBegin : (id + ": " + entity.getBegin() + " -> " + firstTokenBegin);
                        assert entity.getEnd() == lastTokenEnd : (id + ": " + entity.getEnd() + " -> " + lastTokenEnd);
                    } else {
                        if (goldAnnotations.containsKey(pmid)) {
                            goldAnnotations.get(pmid).add(entity);
                        } else {
                            ArrayList<NamedEntity> tempArray = new ArrayList<NamedEntity>();
                            tempArray.add(entity);
                            goldAnnotations.put(pmid, tempArray);
                        }
                    }
                }

                List<Token> tokensToPrint = JCasUtil.selectCovered(Token.class, pubmedAbstract);
                boolean firstToken = true;
                for (Token token : tokensToPrint) {
                    if (firstToken && (token.getBegin() - offset) != 0) {
                        sb.append(" " + "\t" + 0 + "\t").append(token.getBegin() - offset).append("\t\t|O\n");
                    }
                    firstToken = false;
                    sb.append(token.getCoveredText()).append("\t").append(token.getBegin() - offset).append("\t").append(token.getEnd() - offset).append("\t\t|").append(token.getLabel()).append("\n");
                }
            }
        }

        if (evaluate) {
            System.out.println("Starting evaluation...");
            evaluate(goldAnnotations, pipelineAnnotations, detailed);
        }
        return sb.toString();
    }

    private int TP = 0;
    private int FP = 0;
    private int FN = 0;
    private Object evaluationLock = new Object();
    private List<ComparableAnnotation> truePositives = new ArrayList<ComparableAnnotation>();
    private List<ComparableAnnotation> falsePositives = new ArrayList<ComparableAnnotation>();
    private List<ComparableAnnotation> falseNegatives = new ArrayList<ComparableAnnotation>();

    //FIXME: use Mention instead of NamedEntity
    private void evaluate(HashMap<String, ArrayList<NamedEntity>> goldAnnotations, HashMap<String, ArrayList<NamedEntity>> pipelineAnnotations, boolean detailedOutput) {
    	List<ComparableAnnotation> goldAnnoationsComparable = new ArrayList<ComparableAnnotation>();
        List<ComparableAnnotation> pipelineAnnotationsComparable = new ArrayList<ComparableAnnotation>();

        for (String pmid : goldAnnotations.keySet()) {
            for (NamedEntity namedEntity : goldAnnotations.get(pmid)) {
                goldAnnoationsComparable.add(ComparableAnnotation.createInstance(namedEntity.getBegin(), namedEntity.getEnd(), namedEntity.getCoveredText(), 0, namedEntity.getCAS(), pmid, namedEntity.getSource()));
            }
        }
        for (String pmid : pipelineAnnotations.keySet()) {
            for (NamedEntity namedEntity : pipelineAnnotations.get(pmid)) {
                pipelineAnnotationsComparable.add(ComparableAnnotation.createInstance(namedEntity.getBegin(), namedEntity.getEnd(), namedEntity.getCoveredText(), 0, namedEntity.getCAS(), pmid, namedEntity.getSource()));
            }
        }

        synchronized(evaluationLock) {
	        if (goldAnnoationsComparable.size() == 0) {
	            FP += pipelineAnnotationsComparable.size();
	            falsePositives.addAll(pipelineAnnotationsComparable);
	        } else if (pipelineAnnotationsComparable.size() == 0) {
	            FN += goldAnnoationsComparable.size();
	            falseNegatives.addAll(goldAnnoationsComparable);
	        } else {
	            Evaluator<ComparableAnnotation,ComparableAnnotation> evaluator = new Evaluator<ComparableAnnotation,ComparableAnnotation>(pipelineAnnotationsComparable, goldAnnoationsComparable);
	            evaluator.evaluate();
	
	            TP += evaluator.getTruePositives().size();
	            FP += evaluator.getFalsePositives().size();
	            FN += evaluator.getFalseNegatives().size();
	            
	            truePositives.addAll(evaluator.getTruePositives());
	            falsePositives.addAll(evaluator.getFalsePositives());
	            falseNegatives.addAll(evaluator.getFalseNegatives());
	
	            System.out.format("True Positives:\t\t%d\nFalse Positives:\t%d\nFalse Negatives:\t%d\n", TP, FP, FN);
	            double precision = (double) TP / ((double) TP + FP);
	            double recall = (double) TP / ((double) TP + FN);
	            double fscore = precision + recall > 0 ? 2 * (precision * recall) / (precision + recall) : 0;
	            System.out.format("Precision:\t\t%f\nRecall:\t\t\t%f\nF1 Score:\t\t%f\n", precision, recall, fscore);
	            System.out.println();
	            
	            if (detailedOutput) {
	            	printDetailedEvaluationResults();
	            }
	        }
        }
    }
    
    private static List<List<ComparableAnnotation>> sortComparableAnnotationListsBySize(List<ComparableAnnotation> list, boolean bySource) {
    	List<List<ComparableAnnotation>> result = new ArrayList<List<ComparableAnnotation>>();
    	
    	Map<String, List<ComparableAnnotation>> annotationMap = new HashMap<String, List<ComparableAnnotation>>();
    	for (ComparableAnnotation ca : list) {
    		String key = bySource ? ca.getSource() : ca.getText();
    		
    		if (!annotationMap.containsKey(key)) {
    			annotationMap.put(key, new ArrayList<ComparableAnnotation>());
    		}
    		
    		annotationMap.get(key).add(ca);
    	}
    	
    	for (String key : annotationMap.keySet()) {
    		result.add(annotationMap.get(key));
    	}
    	
    	Comparator<List<ComparableAnnotation>> comparator = new Comparator<List<ComparableAnnotation>>() {

			public int compare(List<ComparableAnnotation> o1, List<ComparableAnnotation> o2) {
				return o1.size() - o2.size();
			}
    		
    	};
    	
    	Collections.sort(result, Collections.reverseOrder(comparator));
    	
    	return result;
    }
    
    private void printList(String name, List<ComparableAnnotation> list) {
    	List<List<ComparableAnnotation>> listBySize = sortComparableAnnotationListsBySize(list, false);
    	
    	System.out.printf("%n%n%n%n%n%s:%n", name);
    	System.out.printf("%8s\t%25s\t%s%n", "#", "CHEMICAL", "SOURCE");
    	for (List<ComparableAnnotation> annotationList : listBySize) {
    		List<List<ComparableAnnotation>> listBySource = sortComparableAnnotationListsBySize(annotationList, true);
    		
    		String sources = "";
    		for (List<ComparableAnnotation> sourceList : listBySource) {
    			String source = !sourceList.isEmpty() ? sourceList.get(0).getSource() : "";
    			source = source == null || source.isEmpty() ? Constants.UNKNOWN : source;
    			
    			if (listBySource.size() == 1) {
    				source = (sourceList.size() > 1 ? "all " : "") + source;
    			} else {
    				source = sourceList.size() + " " + source;
    			}
    			
    			sources += String.format("%s%s", !sources.isEmpty() ? ", " : "", source);
    		}
    		
    		String annotation = !annotationList.isEmpty() ? annotationList.get(0).getText() : "";
    		System.out.printf("%8d\t%25s\t%s%n", annotationList.size(), annotation, sources);
    	}
    }
    
    private void printContext(String name, List<ComparableAnnotation> list) {
    	List<List<ComparableAnnotation>> listBySize = sortComparableAnnotationListsBySize(list, false);
    	
    	System.out.printf("%n%n%s:%n", name);
    	
    	Pattern startPattern = Pattern.compile("(\\S+\\s+){5}\\S*$");
    	Pattern stopPattern = Pattern.compile("^\\S*(\\s+\\S+){5}");
    	int maxLength = 100;
    	try {
    	for (List<ComparableAnnotation> annotationList : listBySize) {
    		if (annotationList.isEmpty()) continue;
    		System.out.printf("%n%n%s (%d):%n", annotationList.get(0).getText(), annotationList.size());
    		
    		int i = 0;
    		for (ComparableAnnotation ca : annotationList) {
    			if (i++ > 30) {
    				System.out.println("...");
    				break;
    			}
    			
    			String text = null;
				try {
					text = ca.getCAS().getJCas().getDocumentText();
				} catch (CASException e) {
					e.printStackTrace();
					continue;
				}
    			int begin = ca.getBegin();
    			int end = ca.getEnd();
    			
    			Matcher matcher = startPattern.matcher(text.substring(Math.max(begin-maxLength, 0), begin));
    			int start = matcher.find() ? Math.max(begin-maxLength, 0) + matcher.start() : Math.max(begin-30, 0);
    			
    			matcher = stopPattern.matcher(text.substring(end, Math.min(end+maxLength, text.length())));
    			int stop = matcher.find() ? end + matcher.end() : Math.min(end+30, text.length());
    			
    			String output = String.format("...%s<%s>%s...", text.substring(start , begin), text.substring(begin, end), text.substring(end, stop));
    			output = output.replaceAll("\r?\n", "\\\\n");
    			System.out.println(output);
    		}
    	}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    private void printDetailedEvaluationResults() {
    	synchronized (evaluationLock) {
	    	printList("true positives", truePositives);
	    	printList("false negatives", falseNegatives);
	    	printList("false positives", falsePositives);
	    	printContext("false negatives contexts", falseNegatives);
    	}
    }

    private static void readGZFile(JCas jcas, String pathToFile) throws IOException {
        File file = new File(pathToFile);
        String text;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new GZIPInputStream(
                                new FileInputStream(file)) ) );

        StringBuilder textBuffer = new StringBuilder();
        Integer currindex = -1;
        while(reader.ready()){
            PubmedDocument pmdoc = new PubmedDocument(jcas);
            String s = reader.readLine();
            if (s != null) {
                //split line into pmid and text
                String pmid = s.substring(0, s.indexOf("\t"));
                String annot = s.substring(s.indexOf("\t"));
                //two = splitFirst(s, "\t");
                pmdoc.setPmid(pmid);

                //append text
                textBuffer.append(annot).append("\n");
                pmdoc.setBegin(currindex + 1);
                Integer len = annot.length();
                currindex = currindex + len + 1;
                pmdoc.setEnd(currindex);
                pmdoc.addToIndexes();
            }
        }

        text = textBuffer.toString();

        //put document in CAS
        jcas.setDocumentText(text);
        SourceDocumentInformation srcDocInfo = new SourceDocumentInformation(jcas);
        srcDocInfo.setUri(file.getAbsoluteFile().toURI().toString());
        srcDocInfo.setOffsetInSource(0);
        srcDocInfo.setDocumentSize((int) file.length());
        srcDocInfo.setBegin(0);
        srcDocInfo.setEnd(currindex);
        srcDocInfo.addToIndexes();
    }

    private static int runNr = 1;
    private static Object runLock = new Object();
    
    public static void serializeAnnotations(JCas jcas, String path) throws IOException {
        Iterator<SourceDocumentInformation> srcIterator = JCasUtil.iterator(jcas, SourceDocumentInformation.class);
        
        path = path != null ? path.replaceAll("\\\\", "/") : "log/";
        path = !path.endsWith("/") ? path + "/" : path;
        
        File pathFile = new File(path);
        if (!pathFile.exists()) {
        	synchronized (runLock) {
        		pathFile.mkdir();
        	}
        }
        
        String pathToFile = null;
        if (srcIterator.hasNext()) {
	        SourceDocumentInformation src = srcIterator.next();
	        pathToFile = src.getUri().replaceFirst("file:.*/", path) + ".chem";
        } else {
        	synchronized (runLock) {
        		pathToFile = String.format("%s%04d.chem", path, runNr++);
        	}
        }
        
        File file = new File(pathToFile);
        file.createNewFile(); //overwrite if file already exists
        FileWriter writer = new FileWriter(file);

        int offset;

        Iterator<PubmedDocument> documentIterator = JCasUtil.iterator(jcas, PubmedDocument.class);
        while (documentIterator.hasNext()) {
            PubmedDocument document = documentIterator.next();
            offset = document.getBegin();
            String pmid = document.getPmid();
            int numberOfEntities = 0;
            Iterator<NamedEntity> entityIterator = JCasUtil.iterator(document, NamedEntity.class, true, true);
            while (entityIterator.hasNext()) {
                NamedEntity entity = entityIterator.next();
                if (!"goldstandard".equals(entity.getSource())) {
                    //offset fix for GeneView
                    //int begin = entity.getBegin() - offset;
                    int begin = entity.getBegin() - offset - 1;
                    //int end = entity.getEnd() - offset - 1;
                    int end = entity.getEnd() - offset - 2;
                    String id = (new Mention(entity)).getCHID();
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
