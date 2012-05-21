package de.berlin.hu.uima.cc.eval;

import org.apache.uima.cas.CAS;

public class ComparableAnnotation implements Comparable<ComparableAnnotation> {
	
	private int begin;
	private int end;
	private String text;
	private int offset;
	private CAS cas;
	private String pmid;

	public static ComparableAnnotation createInstance(int begin, int end, String text, int offset) {
		ComparableAnnotation output = new ComparableAnnotation();
		output.setBegin(begin);
		output.setEnd(end);
		output.setText(text);
		output.setOffset(offset);
		return output;
	}
	
	public static ComparableAnnotation createInstance(int begin, int end, String text, int offset, CAS cas) {
		ComparableAnnotation output = new ComparableAnnotation();
		output.setBegin(begin);
		output.setEnd(end);
		output.setText(text);
		output.setOffset(offset);
		output.setCAS(cas);
		return output;
	}
	
	public static ComparableAnnotation createInstance(int begin, int end, String text, int offset, CAS cas, String pmid) {
		ComparableAnnotation output = new ComparableAnnotation();
		output.setBegin(begin);
		output.setEnd(end);
		output.setText(text);
		output.setOffset(offset);
		output.setCAS(cas);
		output.setPMID(pmid);
		return output;
	}
	

	public int compareTo(ComparableAnnotation o) {
		if (this.equals(o)) {
			return 0;
		}
		if (begin != o.getBegin()) {
			return begin - o.getBegin();
		} else {
			return end - o.getEnd();
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + begin;
		result = prime * result + end;
		result = prime * result + offset;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComparableAnnotation other = (ComparableAnnotation) obj;
		if (begin != other.begin)
			return false;
		if (end != other.end)
			return false;
		if (offset != other.offset)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
	
	public boolean equalsOld(Object obj) {
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		ComparableAnnotation other = (ComparableAnnotation) obj;
		
		boolean isBeginMatch = Math.abs(begin - other.getBegin()) <= offset;
		boolean isEndMatch = Math.abs(end - other.getEnd()) <= offset;
		
		if (isBeginMatch && isEndMatch) {
			return true;
		} else {
			return false;
		}
	}
	

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
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


	public void setText(String text) {
		this.text = text;
	}


	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public CAS getCAS() {
		return cas;
	}

	public void setCAS(CAS cas) {
		this.cas = cas;
	}
	
	public String getPMID() {
		return pmid;
	}

	public void setPMID(String pmid) {
		this.pmid = pmid;
	}
}
