package de.berlin.hu.chemspot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.jcas.JCas;

import de.berlin.hu.util.Constants;
import de.berlin.hu.wbi.common.research.Evaluator;

public class ChemicalNEREvaluator {
    private int TP = 0;
    private int FP = 0;
    private int FN = 0;
    private Object evaluationLock = new Object();
    private List<Mention> truePositives = new ArrayList<Mention>();
    private List<Mention> falsePositives = new ArrayList<Mention>();
    private List<Mention> falseNegatives = new ArrayList<Mention>();
    
    private List<Mention> normalizedAll = new ArrayList<Mention>();
    private List<Mention> normalized = new ArrayList<Mention>();
    private List<Mention> normalizedCorrect = new ArrayList<Mention>();
    private Object normalizationLock = new Object();

    /**
     * Evaluates the annotation results.
     * 
     * @param jcas
     */
    public void evaluate(JCas jcas) {
    	System.out.println("Starting evaluation...");
    	
    	List<Mention> mentions = ChemSpot.getMentions(jcas);
    	List<Mention> goldstandardAnnotations = ChemSpot.getGoldstandardAnnotations(jcas);

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

	            evaluateNormalization(new ArrayList<Mention>(evaluator.getTruePositives()), goldstandardAnnotations);
	
	            System.out.format("True Positives:\t\t%d\nFalse Positives:\t%d\nFalse Negatives:\t%d\n", TP, FP, FN);
	            double precision = (double) TP / ((double) TP + FP);
	            double recall = (double) TP / ((double) TP + FN);
	            double fscore = precision + recall > 0 ? 2 * (precision * recall) / (precision + recall) : 0;
	            System.out.format("Precision:\t\t%f\nRecall:\t\t\t%f\nF1 Score:\t\t%f\n", precision, recall, fscore);
	            synchronized(normalizationLock) {
	            	if (!normalized.isEmpty()) {
	            		double correctAllRatio = !normalizedAll.isEmpty() ? (double)normalizedCorrect.size() / (double)normalizedAll.size() : 0;
	            		double correctNormalizedRatio = !normalized.isEmpty() ? (double)normalizedCorrect.size() / (double)normalized.size() : 0;
	            		System.out.format("%d of %d entities were normalized, %d correctly (%.2f %% of all and %.2f %% of normalized)%n", normalized.size(), normalizedAll.size(), normalizedCorrect.size(), correctAllRatio * 100.0, correctNormalizedRatio * 100);
	            	}
	            }
	            System.out.println();
	        }
        }
    }
    
    private double evaluateNormalization(List<Mention> tps, List<Mention> goldStandard) {
    	synchronized (normalizationLock) {
    		Collections.sort(tps);
    		Collections.sort(goldStandard);
    	
	    	int i = 0;
	    	
	    	for (Mention m : tps) {
	    		while (i < goldStandard.size() && goldStandard.get(i).getStart() < m.getStart()) i++;
	    		
	    		if (goldStandard.get(i).getStart() == m.getStart()) {
	    			Mention s = goldStandard.get(i);
	    			
	    			if (s.getCHEB() != null) {
	    				normalizedAll.add(s);
	    				if (m.getCHEB() != null && !m.getCHEB().isEmpty()) {
	    					normalized.add(m);
	    					
	    					if (m.getCHEB().equals(s.getCHEB())) {
	    						normalizedCorrect.add(m);
	    					} else {
	    						m.setCHEB(String.format("%s (correct: %s)", m.getCHEB(), s.getCHEB()));
	    					}
	    				}
	    			}
	    		}
	    	}
	    	
	    	return !normalizedAll.isEmpty() ? (double)normalizedCorrect.size() / (double)normalizedAll.size() : 0;
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
    	writer.write(String.format("Overlapping occurrences of <%s> and [%s]:%n%n", name1, name2));
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
    	writer.write(String.format("%8s\t%25s\t%30s\t%s%n", "#", "CHEMICAL", "SOURCE", "ChEBI ID"));
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
    		
    		Set<String> ids = new HashSet<String>();
			for (Mention m : annotationList) {
				String id = m.getCHEB();
				if (id != null && !id.trim().isEmpty() && !"null".equals(id.trim()) && !ids.contains(id.trim())) {
					ids.add(id.trim());
				}
			}
			String idString = "";
			for (String id : ids) {
				idString += ("".equals(idString) ? "" : ", ") + id;
			}
    		
    		String annotation = !annotationList.isEmpty() ? annotationList.get(0).getText() : "";
    		writer.write(String.format("%8d\t%25s\t%30s\t%s%n", annotationList.size(), annotation, sources, idString));
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
    	
    	writer.flush();
    }
    
    public void writeNormalizations(OutputStream s, List<Mention> normalizedAll, List<Mention> normalized, List<Mention> normalizedCorrect) throws IOException {
    	BufferedWriter w = new BufferedWriter(new OutputStreamWriter(s));
		double correctAllRatio = !normalizedAll.isEmpty() ? (double)normalizedCorrect.size() / (double)normalizedAll.size() : 0;
		double correctNormalizedRatio = !normalized.isEmpty() ? (double)normalizedCorrect.size() / (double)normalized.size() : 0;
		w.write(String.format("entities total              : %d%n", normalizedAll.size()));
		w.write(String.format("entities normalized         : %d%n", normalized.size()));
		w.write(String.format("normalized correct          : %d%n", normalizedCorrect.size()));
		w.write(String.format("percent correct (all)       : %.2f %%%n" , correctAllRatio * 100.0));
		w.write(String.format("percent correct (normalized): %.2f %%%n" , correctNormalizedRatio * 100.0));
		w.flush();
		
		writeList(s, "correct", normalizedCorrect);
		
		List<Mention> normalizedIncorrect = new ArrayList<Mention>(normalized);
		normalizedIncorrect.removeAll(normalizedCorrect);
		writeList(s, "incorrect", normalizedIncorrect);
		
		List<Mention> notNormalized = new ArrayList<Mention>(normalizedAll);
		notNormalized.removeAll(normalizedCorrect);
		notNormalized.removeAll(normalizedIncorrect);
		writeList(s, "not normalized", notNormalized);
    }
    
    public void writeDetailedEvaluationResults(String outputPath) throws IOException {
    	synchronized (evaluationLock) {
    		if (outputPath == null) outputPath = "";
    		
    		File evaluationFile  = new File(outputPath + "evaluation.txt");
    		OutputStream writer = new FileOutputStream(evaluationFile);
    		
    		BufferedWriter w = new BufferedWriter(new OutputStreamWriter(writer));
    		w.write(String.format("True Positives:\t\t%d%nFalse Positives:\t%d%nFalse Negatives:\t%d%n", TP, FP, FN));
            double precision = (double) TP / ((double) TP + FP);
            double recall = (double) TP / ((double) TP + FN);
            double fscore = precision + recall > 0 ? 2 * (precision * recall) / (precision + recall) : 0;
            w.write(String.format("Precision:\t\t%f%nRecall:\t\t\t%f%nF1 Score:\t\t%f%n", precision, recall, fscore));
            w.flush();
    		
    		writeList(writer, "true positives", truePositives);
    		writeList(writer, "false negatives", falseNegatives);
    		writeList(writer, "false positives", falsePositives);
    		w.close();
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
    		
    		File falsePositivesNegativesFile = new File(outputPath + "evaluation-overlappings-FPs-FNs.txt");
    		writer = new FileOutputStream(falsePositivesNegativesFile);
    		writeOverlapping(writer, "false negatives", falseNegatives, "false positives", falsePositives);
    		writer.close();
    		System.out.println("Overlapping occurrences of false positives and negatives written to: " + falsePositivesNegativesFile.getName());
    		
    		synchronized(normalizationLock) {
    			if (!normalized.isEmpty()) {
		    		File normalizedFile = new File(outputPath + "normalizations.txt");
		    		writer = new FileOutputStream(normalizedFile);
		    		
		    	    writeNormalizations(writer, normalizedAll, normalized, normalizedCorrect);

		    		writer.close();
		    		System.out.println("Normalized entities written to: " + normalizedFile.getName());
    			}
    		}
    	}
    }

	public int getTP() {
		return TP;
	}

	public void setTP(int tP) {
		TP = tP;
	}

	public int getFP() {
		return FP;
	}

	public void setFP(int fP) {
		FP = fP;
	}

	public int getFN() {
		return FN;
	}

	public void setFN(int fN) {
		FN = fN;
	}

	public List<Mention> getTruePositives() {
		return truePositives;
	}

	public void setTruePositives(List<Mention> truePositives) {
		this.truePositives = truePositives;
	}

	public List<Mention> getFalsePositives() {
		return falsePositives;
	}

	public void setFalsePositives(List<Mention> falsePositives) {
		this.falsePositives = falsePositives;
	}

	public List<Mention> getFalseNegatives() {
		return falseNegatives;
	}

	public void setFalseNegatives(List<Mention> falseNegatives) {
		this.falseNegatives = falseNegatives;
	}

	public List<Mention> getNormalizedAll() {
		return normalizedAll;
	}

	public void setNormalizedAll(List<Mention> normalizedAll) {
		this.normalizedAll = normalizedAll;
	}

	public List<Mention> getNormalized() {
		return normalized;
	}

	public void setNormalized(List<Mention> normalized) {
		this.normalized = normalized;
	}

	public List<Mention> getNormalizedCorrect() {
		return normalizedCorrect;
	}

	public void setNormalizedCorrect(List<Mention> normalizedCorrect) {
		this.normalizedCorrect = normalizedCorrect;
	}
}
