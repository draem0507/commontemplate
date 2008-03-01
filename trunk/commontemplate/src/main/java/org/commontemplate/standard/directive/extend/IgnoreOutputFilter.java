package org.commontemplate.standard.directive.extend;

import java.io.Serializable;

import org.commontemplate.core.OutputFilter;

public class IgnoreOutputFilter implements OutputFilter, Serializable {

	private static final long serialVersionUID = 1L;
	
	private static IgnoreOutputFilter ignoreOutputFilter = new IgnoreOutputFilter();
	
	private IgnoreOutputFilter() {}
	
	public static IgnoreOutputFilter getInstance() {
		return ignoreOutputFilter;
	}

	public String filter(String text) {
		return null;
	}

}
