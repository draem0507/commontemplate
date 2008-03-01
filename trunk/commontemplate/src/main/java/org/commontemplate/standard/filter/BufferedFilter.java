package org.commontemplate.standard.filter;

import java.io.Serializable;

import org.commontemplate.core.OutputFilter;

public class BufferedFilter implements OutputFilter, Serializable {

	private static final long serialVersionUID = 1L;

	private StringBuffer buffer = new StringBuffer();

	public BufferedFilter() {
	}

	public String filter(String text) {
		buffer.append(text);
		return null;
	}
	
	public String getBuffered() {
		return buffer.toString();
	}

}
