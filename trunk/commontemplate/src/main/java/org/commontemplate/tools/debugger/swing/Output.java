package org.commontemplate.tools.debugger.swing;

public class Output {

	private String content;

	private int last;

	public Output(String content, int last) {
		super();
		this.content = content;
		this.last = last;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

}
