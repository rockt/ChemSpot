package de.berlin.hu.chemspot;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ChemSpotConfiguration {
	// Constants
	public static enum Corpus {IOB, CRAFT, GZ, NACTEM, PATENT, DDI, XMI, TXT};
	public static enum Component {TOKENIZER, SENTENCE_DETECTOR, POS_TAGGER, CRF, DICTIONARY, SUM_TAGGER, ABBREV, MENTION_EXPANDER, ANNOTATION_MERGER, STOPWORD_FILTER, NORMALIZER, OPSIN, FEATURE_GENERATOR};
	
	private static final String CORPUS_PREFIX = "corpus.";
	
	private static final String OUTPUT_PATH = "output.path";
	private static final String XMI_OUTPUT_PATH = "output.path.xmi";
	private static final String CONVERT_TO_IOB = "output.convertToIOB";
	
	private static final String SENTENCE_MODEL = "sentence_model.path";
	private static final String CRF_MODEL = "crf.model.path";
	private static final String DICTIONARY = "dict.path";
	private static final String IDS = "ids.path";
	
	private static final String EVALUATION = "evaluation";
	private static final String DETAILED_EVALUATION = "evaluation.detailed";
	private static final String THREADING = "threading";
	private static final String THREAD_NR = "threading.number_of_threads";
	private static final int DEFAULT_THREAD_NR = 4;
	
	private static final String COMPONENT_PREFIX = "component.";
	
	// Variables
	private static Properties properties = null;
	
	static {
		properties = new Properties();
	}
	
	public static void initialize(String configFilePath) throws FileNotFoundException, IOException {
		properties.load(new FileReader(configFilePath));
	}
	
	public static String getProperty(String property) {
		String result = properties.getProperty(property);
		if (result != null) result = result.trim();
		return result;
	}
	
	public static String getProperty(String property, String defaultValue) {
		return properties.getProperty(property, defaultValue);
	}
	
	public static String getPathToCorpus(Corpus corpus) {
		return getProperty(CORPUS_PREFIX + corpus);
	}

	public static String getOutputPath() {
		return getProperty(OUTPUT_PATH);
	}

	public static String getXMIOutputPath() {
		return getProperty(XMI_OUTPUT_PATH);
	}

	public static boolean isConvertToIob() {
		return "true".equals(getProperty(CONVERT_TO_IOB));
	}

	public static String getSentenceModel() {
		return getProperty(SENTENCE_MODEL);
	}

	public static String getCRFModel() {
		return getProperty(CRF_MODEL);
	}

	public static String getDictionary() {
		return getProperty(DICTIONARY);
	}

	public static String getIds() {
		return getProperty(IDS);
	}

	public static boolean isEvaluate() {
		return "true".equals(getProperty(EVALUATION));
	}

	public static boolean isDetailedEvaluation() {
		return "true".equals(getProperty(DETAILED_EVALUATION));
	}

	public static boolean useComponent(Component component) {
		return "true".equals(getProperty(COMPONENT_PREFIX + component.toString().toLowerCase(), "true").toLowerCase().trim());
	}

	public static boolean isThreading() {
		return "true".equals(getProperty(THREADING));
	}

	public static int getNumberOfThreads() {
		try {
			return Integer.valueOf(getProperty(THREAD_NR, DEFAULT_THREAD_NR + ""));
		} catch (NumberFormatException e) {
			System.out.println("ERROR: value of property '" + THREAD_NR + "' is not a number. Using defalut value " + DEFAULT_THREAD_NR);
			return DEFAULT_THREAD_NR;
		}
	}
}
