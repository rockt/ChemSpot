package de.berlin.hu.util;

/**
 * User: Tim Rocktaeschel
 * Date: 6/21/12
 * Time: 1:44 PM
 */
public class Constants {
	public static enum ChemicalID {CHID, CHEB, CAS, PUBC, PUBS, INCH, DRUG, HMBD, KEGG, KEGD, MESH, FDA, FDA_DATE};
	public static enum ChemicalType {SYSTEMATIC, IDENTIFIER, FORMULA, TRIVIAL, ABBREVIATION, FAMILY, MULTIPLE, UNKNOWN;
		public static ChemicalType fromString(String s) {
			if (s == null) {
				return UNKNOWN;
			} else {
				s = s.trim();
			}
			
			if (ABBREV.equals(s)) {
				return ABBREVIATION;
			} else if (CRF.equals(s) || s.toLowerCase().contains("iupac")) {
				return SYSTEMATIC;
			} else if (DICTIONARY.equals(s)) {
				return SYSTEMATIC;
			} else if (SUM_TAGGER.equals(s) || "sum".equalsIgnoreCase(s)) {
				return FORMULA;
			}
			
			try {
				return ChemicalType.valueOf(s.toUpperCase());
			} catch (IllegalArgumentException e) {
				return UNKNOWN;
			}
		}
	};
	
	public static final String ABBREV = "ABBREV";
    public static final String CRF = "crf";
    public static final String DICTIONARY = "dictionary";
    public static final String SUM_TAGGER = "sum_tagger";
    public static final String DRUG = "drug tagger";
    public static final String GOLDSTANDARD = "goldstandard";
    public static final String UNKNOWN = "unknown";
}
