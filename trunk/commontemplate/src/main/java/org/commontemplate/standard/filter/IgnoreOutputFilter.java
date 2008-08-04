package org.commontemplate.standard.filter;

import java.io.Serializable;

import org.commontemplate.core.OutputFilter;

public class IgnoreOutputFilter implements OutputFilter, Serializable {

	private static final long serialVersionUID = 1L;

	private static IgnoreOutputFilter IGNORE_OUTPUT_FILTER = new IgnoreOutputFilter();

	private IgnoreOutputFilter() {}

	public static IgnoreOutputFilter getInstance() {
		return IGNORE_OUTPUT_FILTER;
	}

	public String filter(String text) {
		return null;
	}

}
