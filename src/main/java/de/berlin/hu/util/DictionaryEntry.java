package de.berlin.hu.util;

import java.util.Set;

//FIXME not working
public class DictionaryEntry {
	private static final String DELIMITER = "#####";
	private static final String TEMP = "#####";
	private static final boolean FUZZIFY = false;
	private Set<String> terms;
	private Set<String> ids;
	
/* Databases:
	KEGG
	DRUG
	PUBS
	PUBC
	HMBD
	INCH
	CAS
	CHID
	MESH
    CHEB
	KEGD
 */

	public DictionaryEntry(Set<String> terms, Set<String> ids) {
		this.terms = terms;
		this.ids = ids;
	}
	
	public String convertToLinnaeusFormat() {
		if (terms == null) {
			return null;
		}
		
		if (terms.size() != 0 && ids.size() != 0) {
			StringBuilder sb = new StringBuilder();
			String idString = null;
			
			if (DictionaryBuilder.USE_CHID_ONLY) {
				for (String id : ids) {
					if (id.contains("CHID")) {
						idString = id;
					}
				}
			} else {
				idString = convertIdsToLinnaeusFormat(ids);
			}

			for (String term : terms) {
				if (!term.isEmpty()) {
					sb.append(idString + "\t" + convertTermToLinnaeusFormat(term) + "\n");
				}
			}

			return sb.toString();
		} else {
			return null;
		}
	}

	private String convertTermToLinnaeusFormat(String term) {
		String temp = term.replaceAll("([\\\\*+\\[\\](){}\\$.?\\^|])", "\\\\$1");
		
		//replacing wrong strings
		temp = temp.replace("<sub>","");
		temp = temp.replace("</sub>","");
		temp = temp.replace("<","");
		temp = temp.replace(">","");
		
		if (FUZZIFY) {
			temp = temp.replace(" ", TEMP);
			temp = temp.replace("_", TEMP);
			temp = temp.replace("-", TEMP);
			temp = temp.replace(TEMP, "( |_|-)+");
		}
		return temp;
	}

	private String convertIdsToLinnaeusFormat(Set<String> ids) {
		StringBuilder sb = new StringBuilder();
		for (String string : ids) {
			//FIXME make INCHis possible by escaping regexes
			if (!string.startsWith("INCH")) {
				sb.append(string + DELIMITER);
			}
		}
		
		String idsString = sb.toString();
		return idsString.substring(0, idsString.length()-DELIMITER.length());
	}

	public void print() {
		System.out.println("===== Entry =====");
		for (String term : terms) {
			System.out.println(term);
		}
		System.out.println("------ IDs ------");
		for (String id : ids) {
			System.out.println(id);
		}
	}
}
