package de.berlin.hu.chemspot;

public class ChemSpotFactory {
	public static final String DEFAULT_SENTENCE_MODEL_LOCATION = null;
	public static final String DEFAULT_CRF_MODEL_LOCATION = null;
	public static final String DEFAULT_DICTIONARY_LOCATION = "dict.zip";
	public static final String DEFAULT_IDS_LOCATION = "ids.zip";
	public static final String DEFAULT_EUMED_MODEL_LOCATION = "multiclass.bin";
	
	/**
	 * <p>Creates a new ChemSpot object. This method will load the built-in sentence model and CRF model.</p>
	 * 
	 * <p><b>Note:</b> This method expects the dictionary file, ids file and eumed model to be at
	 * their <b>default locations</b> (that is, in the same directory as the application that is calling this method).
	 * It is recommended that you use 
	 * {@link #createChemSpot(String, String, String) createChemSpot(pathToDictionary, pathToIds, pathToEumedModel)}
	 * instead.</p>
	 * 
	 * <p>If the files cannot be found there, this method will still return a ChemSpot object, but it will neither
	 * use the dictionary and multi-class tagger nor will it perform chemical normalization (e.g. find 
	 * chemical identifiers) after tagging. This will result in <b>significantly worse performance.</b></p>
	 * 
	 * @return a new ChemSpot object
	 */
	public static ChemSpot createChemSpot() {
		return createChemSpot(DEFAULT_DICTIONARY_LOCATION, DEFAULT_IDS_LOCATION, DEFAULT_EUMED_MODEL_LOCATION);
	}
	
	/**
	 * <p>Creates a new ChemSpot object. This method will load the built-in sentence model and CRF model.</p>
	 * 
	 * <p><b>Note:</b> This method will create a new ChemSpot object without a multi-class tagger, which will result
	 * in <b>worse performance</b>. If you want to use ChemSpot with the multi-class tagger, you can specify the
	 * the location of the multi-class model by using
	 * {@link #createChemSpot(String, String, String) createChemSpot(pathToDictionary, pathToIds, pathToEumedModel)}</p>
	 * 
	 * @param pathToDictionary the dictionary location
	 * @param pathToIds the ids file location
	 * @return a new ChemSpot object
	 */
	public static ChemSpot createChemSpot(String pathToDictionary, String pathToIds) {
		return createChemSpot(pathToDictionary, pathToIds, null);
	}
	
	/**
	 * Creates a new ChemSpot object. This method will load the built-in sentence model and CRF model.
	 * 
	 * @param pathToDictionary the dictionary location
	 * @param pathToIds the ids file location
	 * @param pathToEumedModel the multi-class model location
	 * @return a new ChemSpot object
	 */
	public static ChemSpot createChemSpot(String pathToDictionary, String pathToIds, String pathToEumedModel) {
		return createChemSpot(pathToDictionary, pathToIds, pathToEumedModel, DEFAULT_SENTENCE_MODEL_LOCATION, DEFAULT_CRF_MODEL_LOCATION);
	}
	
	/**
	 * Creates a new ChemSpot object. 
	 * 
	 * @param pathToDictionary the dictionary location
	 * @param pathToIds the ids file location
	 * @param pathToEumedModel the multi-class model location
	 * @param pathToSentenceModel the sentence model location
	 * @param pathToCRFModel the CRF model location
	 * @return a new ChemSpot object
	 */
	public static ChemSpot createChemSpot(String pathToDictionary, String pathToIds, String pathToEumedModel, String pathToSentenceModel, String pathToCRFModel) {
		return new ChemSpot(pathToCRFModel, pathToDictionary, pathToSentenceModel, pathToIds, pathToEumedModel);
	}
}
