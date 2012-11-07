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
import de.berlin.hu.util.Constants;
import de.berlin.hu.wbi.common.research.Evaluator;
import org.apache.uima.UIMAException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.XMLInputSource;
import org.u_compare.shared.semantic.NamedEntity;
import org.u_compare.shared.syntactic.Token;
import org.uimafit.factory.AnalysisEngineFactory;
import org.uimafit.factory.JCasFactory;
import org.uimafit.util.JCasUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
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
    private AnalysisEngine posTagger;
    private AnalysisEngine sentenceDetector;
    private AnalysisEngine sentenceConverter;
    private AnalysisEngine tokenConverter;
    private AnalysisEngine crfTagger;
    private AnalysisEngine dictionaryTagger;
    private AnalysisEngine chemicalFormulaTagger;
    private AnalysisEngine abbrevTagger;
    private AnalysisEngine annotationMerger;
    private AnalysisEngine fineTokenizer;
    private AnalysisEngine stopwordFilter;
    private AnalysisEngine mentionExpander;
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
            posTagger = AnalysisEngineFactory.createAnalysisEngine(UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(this.getClass().getClassLoader()
                    .getResource("desc/ae/tagger/opennlp/PosTagger.xml"))), CAS.NAME_DEFAULT_SOFA);
            tokenConverter = AnalysisEngineFactory.createPrimitive(UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(this.getClass().getClassLoader()
                    .getResource("desc/ae/converter/OpenNLPToUCompareTokenConverterAE.xml"))));
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
            mentionExpander = AnalysisEngineFactory.createAnalysisEngine(UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(this.getClass().getClassLoader()
                    .getResource("desc/ae/expander/MentionExpanderAE.xml"))), CAS.NAME_DEFAULT_SOFA);;
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
     * Returns all mentions (non-goldstandard entities) from a jcas object.
     * 
     * @param jcas the jcas
     * @return
     */
    public static List<Mention> getMentions(JCas jcas) {
    	List<Mention> mentions = new ArrayList<Mention>();
        Iterator<NamedEntity> entities = JCasUtil.iterator(jcas, NamedEntity.class);
        while (entities.hasNext()) {
            NamedEntity entity = entities.next();
            //disregards gold-standard mentions
            if (!Constants.GOLDSTANDARD.equals(entity.getSource())) {
                mentions.add(new Mention(entity));
            }
        }

        return mentions;
    }
    
    /**
     * Returns all goldstandard entities from a jcas object.
     * 
     * @param jcas the jcas
     * @return
     */
    public static List<Mention> getGoldstandardAnnotations(JCas jcas) {
    	List<Mention> result = new ArrayList<Mention>();
        Iterator<NamedEntity> entities = JCasUtil.iterator(jcas, NamedEntity.class);
        while (entities.hasNext()) {
            NamedEntity entity = entities.next();
            if (Constants.GOLDSTANDARD.equals(entity.getSource())) {
                result.add(new Mention(entity));
            }
        }

        return result;
    }
    
    /**
     * Reads a text from a file and puts the content into the provided jcas.
     * 
     * @param jcas the jcas
     * @param pathToFile the path to the text file
     * @throws IOException
     */
    public static void readFile(JCas jcas, String pathToFile) throws IOException {
    	FileInputStream stream = new FileInputStream(new File(pathToFile));
    	String text = null;
		try {
			FileChannel fc = stream.getChannel();
			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
			text = Charset.defaultCharset().decode(bb).toString();
		} finally {
			stream.close();
		}
		
		jcas.setDocumentText(text);
        PubmedDocument pd = new PubmedDocument(jcas);
        pd.setBegin(0);
        pd.setEnd(text.length());
        pd.setPmid("");
        pd.addToIndexes(jcas);
    }
    
    /**
     * Reads a text from a gzipped file and puts the content into the provided jcas.
     * 
     * @param jcas the jcas
     * @param pathToFile the path to the text file
     * @throws IOException
     */
    public static void readGZFile(JCas jcas, String pathToFile) throws IOException {
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
    
    /**
     * Finds chemical entities in the document of a {@code JCas} object and returns a list of mentions.
     * @param jcas contains the document text
     * @return a list of mentions
     */
    public List<Mention> tag(JCas jcas) {
        try {
        	fineTokenizer.process(jcas);
            synchronized (this) {
            	sentenceDetector.process(jcas);
            }
            posTagger.process(jcas);
            tokenConverter.process(jcas);
            sentenceConverter.process(jcas);
            crfTagger.process(jcas);
            if (dictionaryTagger != null) dictionaryTagger.process(jcas);
            chemicalFormulaTagger.process(jcas);
            abbrevTagger.process(jcas);
            mentionExpander.process(jcas);
            annotationMerger.process(jcas);
            stopwordFilter.process(jcas);
            if (normalizer != null) normalizer.process(jcas);
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
     * @throws UIMAException 
     */
    public List<Mention> tag(String text) throws UIMAException {
        JCas jcas = JCasFactory.createJCas(typeSystem);
        jcas.setDocumentText(text);
        PubmedDocument pd = new PubmedDocument(jcas);
        pd.setBegin(0);
        pd.setEnd(text.length());
        pd.setPmid("");
        pd.addToIndexes(jcas);
        return tag(jcas);
    }

    /**
     * Converts all annotations from jcas to the IOB format
     * 
     * @param jcas the jcas
     * @return
     */
    public static String convertToIOB(JCas jcas) {
    	StringBuilder sb = new StringBuilder();
        HashMap<String, ArrayList<NamedEntity>> goldAnnotations = new HashMap<String, ArrayList<NamedEntity>>();
        HashMap<String, ArrayList<NamedEntity>> pipelineAnnotations = new HashMap<String, ArrayList<NamedEntity>>();
    	
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
                if (!Constants.GOLDSTANDARD.equals(entity.getSource())) {
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
        
        return sb.toString();
    }

    private int TP = 0;
    private int FP = 0;
    private int FN = 0;
    private Object evaluationLock = new Object();
    private List<Mention> truePositives = new ArrayList<Mention>();
    private List<Mention> falsePositives = new ArrayList<Mention>();
    private List<Mention> falseNegatives = new ArrayList<Mention>();

    /**
     * Evaluates the annotation results.
     * 
     * @param jcas
     */
    public void evaluate(JCas jcas) {
    	System.out.println("Starting evaluation...");
    	
    	List<Mention> mentions = getMentions(jcas);
    	List<Mention> goldstandardAnnotations = getGoldstandardAnnotations(jcas);

        synchronized(evaluationLock) {
	        if (goldstandardAnnotations.size() == 0) {
	            FP += mentions.size();
	            falsePositives.addAll(mentions);
	        } else if (mentions.size() == 0) {
	            FN += goldstandardAnnotations.size();
	            falseNegatives.addAll(goldstandardAnnotations);
	        } else {
	            Evaluator<Mention, Mention> evaluator = new Evaluator<Mention, Mention>(mentions, goldstandardAnnotations);
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
	        }
        }
    }
    
    private static List<List<Mention>> sortMentionListsBySize(List<Mention> list, boolean bySource) {
    	List<List<Mention>> result = new ArrayList<List<Mention>>();
    	
    	Map<String, List<Mention>> annotationMap = new HashMap<String, List<Mention>>();
    	for (Mention mention : list) {
    		String key = bySource ? mention.getSource() : mention.getText().toLowerCase();
    		
    		if (!annotationMap.containsKey(key)) {
    			annotationMap.put(key, new ArrayList<Mention>());
    		}
    		
    		annotationMap.get(key).add(mention);
    	}
    	
    	for (String key : annotationMap.keySet()) {
    		result.add(annotationMap.get(key));
    	}
    	
    	Comparator<List<Mention>> comparator = new Comparator<List<Mention>>() {

			public int compare(List<Mention> o1, List<Mention> o2) {
				return o1.size() - o2.size();
			}
    		
    	};
    	
    	Collections.sort(result, Collections.reverseOrder(comparator));
    	
    	return result;
    }
    
    private static void writeOverlapping(OutputStream s, String name1, List<Mention> list1,  String name2, List<Mention> list2) throws IOException {
    	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s));
    	
    	list1 = new ArrayList<Mention>(list1);
    	Collections.sort(list1);
    	list2 = new ArrayList<Mention>(list2);
    	Collections.sort(list2);
    	
    	Pattern startPattern = Pattern.compile("(\\S+\\s+){5}\\S*$");
    	Pattern stopPattern = Pattern.compile("^\\S*(\\s+\\S+){5}");
    	writer.write(String.format("Overlapping occurrences of <%s> and [%s]:%n", name1, name2));
    	int maxLength = 100;
    	int i = 0;
    	for (Mention m1 : list1) {
    		while (i < list2.size() && list2.get(i).getEnd() < m1.getStart()) i++;
    		
    		int j = i;
    		while (j < list2.size() && list2.get(j).overlaps(m1)) {
    			Mention m2 = list2.get(j++);
    			
    			if (!m1.getCas().getDocumentText().equals(m2.getCas().getDocumentText())) continue;
    			
    	    	String text = m1.getCas().getDocumentText();
    	    	int begin = Math.min(m1.getStart(), m2.getStart());
    			int end = Math.max(m1.getEnd(), m2.getEnd());
    	    	
    	    	Matcher matcher = startPattern.matcher(text.substring(Math.max(begin-maxLength, 0), begin));
    			int start = matcher.find() ? Math.max(begin-maxLength, 0) + matcher.start() : Math.max(begin-30, 0);
    			
    			matcher = stopPattern.matcher(text.substring(end, Math.min(end+maxLength, text.length())));
    			int stop = matcher.find() ? end + matcher.end() : Math.min(end+30, text.length());
    			
    			StringBuilder sb = new StringBuilder();
    			sb.append(text.substring(start, stop));
    			sb.insert(m1.getStart() - start, '<');
    			sb.insert(m1.getEnd() - start + 1, '>');
    			sb.insert(m2.getStart() - start + (m1.getStart() < m2.getStart() ? 1 : 0) + (m1.getStart() == m2.getStart() && m1.getEnd() > m2.getEnd() ? 1 : 0), "[");
    			sb.insert(m2.getEnd() - start + 2 + (m1.getEnd() < m2.getEnd() || (m1.getEnd() == m2.getEnd() && m1.getStart() > m2.getStart()) ? 1 : 0), "]").toString();
    			writer.write("..." + sb.toString().replaceAll("\r?\n", "\\\\n") + "...");
    			writer.newLine();
    		}
    	}
    	
    	writer.flush();
    }
    
    private static void writeList(OutputStream s, String name, List<Mention> list) throws IOException {
    	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s));
    	List<List<Mention>> listBySize = sortMentionListsBySize(list, false);
    	
    	writer.write(String.format("%n%n%n%s:%n", name));
    	writer.write(String.format("%8s\t%25s\t%s%n", "#", "CHEMICAL", "SOURCE"));
    	for (List<Mention> annotationList : listBySize) {
    		List<List<Mention>> listBySource = sortMentionListsBySize(annotationList, true);
    		
    		String sources = "";
    		for (List<Mention> sourceList : listBySource) {
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
    		writer.write(String.format("%8d\t%25s\t%s%n", annotationList.size(), annotation, sources));
    	}
    	
    	writer.flush();
    }
    
    private static void writeContext(OutputStream s, String name, List<Mention> list) throws IOException {
    	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s));
    	List<List<Mention>> listBySize = sortMentionListsBySize(list, false);
    	
    	writer.write(String.format("%s:%n", name));
    	
    	Pattern startPattern = Pattern.compile("(\\S+\\s+){5}\\S*$");
    	Pattern stopPattern = Pattern.compile("^\\S*(\\s+\\S+){5}");
    	int maxLength = 100;
    	try {
    	for (List<Mention> annotationList : listBySize) {
    		if (annotationList.isEmpty()) continue;
    		writer.write(String.format("%n%n%s (%d):%n", annotationList.get(0).getText(), annotationList.size()));
    		
    		int i = 0;
    		for (Mention mention : annotationList) {
    			if (i++ > 30) {
    				writer.write("...");
    				writer.newLine();
    				break;
    			}
    			
    			String text = null;
				text = mention.getCas().getDocumentText();
    			int begin = mention.getStart();
    			int end = mention.getEnd();
    			
    			Matcher matcher = startPattern.matcher(text.substring(Math.max(begin-maxLength, 0), begin));
    			int start = matcher.find() ? Math.max(begin-maxLength, 0) + matcher.start() : Math.max(begin-30, 0);
    			
    			matcher = stopPattern.matcher(text.substring(end, Math.min(end+maxLength, text.length())));
    			int stop = matcher.find() ? end + matcher.end() : Math.min(end+30, text.length());
    			
    			String output = String.format("...%s<%s>%s...", text.substring(start , begin), text.substring(begin, end), text.substring(end, stop));
    			output = output.replaceAll("\r?\n", "\\\\n");
    			writer.write(output);
    			writer.newLine();
    		}
    	}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	writer.flush();
    }
    
    public void writeDetailedEvaluationResults(String outputPath) throws IOException {
    	synchronized (evaluationLock) {
    		if (outputPath == null) outputPath = "";
    		
    		File evaluationFile  = new File(outputPath + "evaluation.txt");
    		OutputStream writer = new FileOutputStream(evaluationFile);
    		writeList(writer, "true positives", truePositives);
    		writeList(writer, "false negatives", falseNegatives);
    		writeList(writer, "false positives", falsePositives);
    		writer.close();
    		System.out.println("Evaluation results written to: " + evaluationFile.getName());
    		
    		File falsePositivesFile = new File(outputPath + "evaluation-FPs.txt");
    		writer = new FileOutputStream(falsePositivesFile);
    		writeContext(writer, "false positives contexts", falsePositives);
    		writer.close();
    		System.out.println("False positive contexts written to: " + falsePositivesFile.getName());
    		
    		File falseNegativesFile = new File(outputPath + "evaluation-FNs.txt");
    		writer = new FileOutputStream(falseNegativesFile);
    		writeContext(writer, "false negatives contexts", falseNegatives);
    		writer.close();
    		System.out.println("False negative contexts written to: " + falseNegativesFile.getName());
    		
    		File falsePositivesNegativesFile = new File(outputPath + "evaluation-FP-FNs.txt");
    		writer = new FileOutputStream(falsePositivesNegativesFile);
    		writeOverlapping(writer, "false negatives", falseNegatives, "false positives", falsePositives);
    		writer.close();
    		System.out.println("Overlapping occurrences of false positives and negatives written to: " + falsePositivesNegativesFile.getName());
    	}
    }
    
    public static String serializeAnnotations(JCas jcas) {
        int offset;
        StringBuilder sb = new StringBuilder();
        Iterator<PubmedDocument> documentIterator = JCasUtil.iterator(jcas, PubmedDocument.class);
        while (documentIterator.hasNext()) {
            PubmedDocument document = documentIterator.next();
            offset = document.getBegin();
            String pmid = document.getPmid();
            int numberOfEntities = 0;
            Iterator<NamedEntity> entityIterator = JCasUtil.iterator(document, NamedEntity.class, true, true);
            while (entityIterator.hasNext()) {
                NamedEntity entity = entityIterator.next();
                if (!Constants.GOLDSTANDARD.equals(entity.getSource())) {
                    //offset fix for GeneView
                    //int begin = entity.getBegin() - offset;
                    int begin = entity.getBegin() - offset - 1;
                    //int end = entity.getEnd() - offset - 1;
                    int end = entity.getEnd() - offset - 2;
                    String id = (new Mention(entity)).getCHID();
                    String text = entity.getCoveredText();
                    if (id == null || id.isEmpty()) {
                    	sb.append(pmid + "\t" + begin + "\t" + end + "\t" + text + "\t" + "\\N\n");
                    } else {
                    	sb.append(pmid + "\t" + begin + "\t" + end + "\t" + text + "\t" + id + "\n");
                    }
                }
                numberOfEntities++;
            }
            if (numberOfEntities == 0) {
            	sb.append(pmid + "\t-1\t-1\t\\N\t\\N\n");
            }
        }
        
        return sb.toString();
    }
}
