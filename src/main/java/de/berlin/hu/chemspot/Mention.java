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
	private String id;
	private String source;

    /**
     * Represents a chemical entity found in a text.
     * @param start position of the start character of an annotation
     * @param end position of the end character of an annotation (exclusive)
     * @param text covered text
     * @param id ChemIDplus or InChI identifier if entities is normalized, empty otherwise
     * @param source indicates whether found by BANNER's CRF, ChemIDplus LINNAEUS dictionary or taken from goldstandard
     */
	public Mention(int start, int end, String text, String id, String source) {
		this.start = start;
		this.end = end;
		this.text = text;
        this.id = id;
        if ("banner".equals(source)) this.source = "CRF";
        else if ("linnaeus".equals(source)) this.source = "Dictionary";
        else this.source = source;
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
	public String getId() {
		return id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
}
