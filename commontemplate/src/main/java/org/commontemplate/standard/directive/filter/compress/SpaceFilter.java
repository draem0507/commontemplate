package org.commontemplate.standard.directive.filter.compress;

import java.io.Serializable;

import org.commontemplate.core.OutputFilter;

public class SpaceFilter implements OutputFilter, Serializable {

	private static final long serialVersionUID = 1L;
	
	public String filter(String message) {
		return message.replaceAll("\\s+", " ");
	}

}
