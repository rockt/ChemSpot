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

import de.berlin.hu.util.Constants;

public class Mention {
	private int start;
	private int end;
	private String text;
	private String[] ids;
	private String source;

    /**
     * Represents a chemical entity found in a text.
     * @param start position of the start character of an annotation
     * @param end position of the end character of an annotation (exclusive)
     * @param text covered text
     * @param ids a string representation of an array of identifiers of the form: [0] CHID, [1] CHEB, [2] CAS, [3] PUBC, [4] PUBS, [5] INCH, [6] DRUG, [7] HMBD, [8] KEGG, [9] KEGD, [10] MESH
     * @param source indicates whether found by the CRF, the dictionary or taken from goldstandard
     */
	public Mention(int start, int end, String text, String ids, String source) {
		this.start = start;
		this.end = end;
		this.text = text;
        if (ids != null) {
            String tempIds = ids;
            if (tempIds.startsWith("[")) tempIds = tempIds.substring(1);
            if (tempIds.endsWith("]")) tempIds = tempIds.substring(0, tempIds.length() - 1);
            this.ids = tempIds.split(",");
        } else {
            this.ids = new String[0];
        }
        this.source = source;
	}

    public Mention(int start, int end, String text) {
   		this.start = start;
   		this.end = end;
   		this.text = text;
        this.source = Constants.DICTIONARY;
   	}

    public Mention(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public String getText() {
		return text;
	}
	public String[] getIds() {
		return ids;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

    public String getCHID() {
        return getId(Constants.CHID);
    }

    public String getINCH() {
        return getId(Constants.INCH);
    }

    private String getId(int pos) {
        String id = "";
        try {
          id = ids[pos];
        } catch (ArrayIndexOutOfBoundsException e) {
            //ignore
        }
        return id;
    }
}
