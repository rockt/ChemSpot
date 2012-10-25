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

import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.tcas.Annotation;
import org.u_compare.shared.semantic.NamedEntity;

public class Mention implements Comparable<Object> {
	private int start;
	private int end;
	private String text;
	private String[] ids;
	private String source;
	private CAS cas;

    /**
     * Represents a chemical entity found in a text.
     * @param start position of the start character of an annotation
     * @param end position of the end character of an annotation (exclusive)
     * @param text covered text
     * @param ids a string representation of an array of identifiers of the form: [0] CHID, [1] CHEB, [2] CAS, [3] PUBC, [4] PUBS, [5] INCH, [6] DRUG, [7] HMBD, [8] KEGG, [9] KEGD, [10] MESH
     * @param source indicates whether found by the CRF, the dictionary or taken from goldstandard
     */
	public Mention(int start, int end, String text, String ids, String source, CAS cas) {
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
        this.cas = cas;
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

    public Mention(NamedEntity entity) {
        this(entity.getBegin(), entity.getEnd(), entity.getCoveredText(), entity.getId(), entity.getSource(), entity.getCAS());
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

    public String getCHEB() {
        return getId(Constants.CHEB);
    }

    public String getCAS() {
        return getId(Constants.CAS);
    }

    public String getPUBC() {
        return getId(Constants.PUBC);
    }

    public String getPUBS() {
        return getId(Constants.PUBS);
    }

    public String getINCH() {
        return getId(Constants.INCH);
    }

    public String getDRUG() {
        return getId(Constants.DRUG);
    }

    public String getHMBD() {
        return getId(Constants.HMBD);
    }

    public String getKEGG() {
        return getId(Constants.KEGG);
    }

    public String getKEGD() {
        return getId(Constants.KEGD);
    }

    public String getMESH() {
        return getId(Constants.MESH);
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
    
    public boolean equals(Object obj) {
    	if (this == obj) {
    		return true;
    	}
    	
    	if (obj == null || !(obj instanceof Mention)) {
    		return false;
    	}
    	
		Mention other = (Mention) obj;
		if (getStart() != other.getStart() || getEnd() != other.getEnd()
				|| (getText() == null && other.getText() != null) || (getText() != null && !getText().equals(other.getText()))) {
			return false;
		}

		return true;
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getStart();
		result = prime * result + getEnd();
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

    @Override
    public String toString() {
        return start + " " + end + " " + text + " " + getCHID();
    }

	public int compareTo(Object o) {
		if (this.equals(o)) {
			return 0;
		}
		
		int otherBegin = 0;
		int otherEnd = 0;
		
		if (o instanceof Mention) {
			Mention other = (Mention) o;
			otherBegin = other.getStart();
			otherEnd = other.getEnd();
		} else if (o instanceof Annotation) {
			Annotation other = (Annotation) o;
			otherBegin = other.getBegin();
			otherEnd = other.getEnd();
		} else {
			return 0;
		}
		
		if (getStart() != otherBegin) {
			return getStart() - otherBegin;
		} else {
			return getEnd() - otherEnd;
		}
	}

	public CAS getCas() {
		return cas;
	}

	public void setCas(CAS cas) {
		this.cas = cas;
	}
}
