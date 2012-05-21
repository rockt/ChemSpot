package de.berlin.hu.chemspot;

public class Mention {
	private int start;
	private int end;
	private String text;
	private String id;
	private String source;
	
	public Mention(int start, int end, String text, String id, String source) {
		this.start = start;
		this.end = end;
		this.text = text;
		this.id = id;
		this.source = source;
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
	public void setText(String text) {
		this.text = text;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
}
