package org.commontemplate.standard.directive.filter.space;

import java.io.Serializable;

import org.commontemplate.core.OutputFilter;

public class TrimSpaceFilter implements OutputFilter, Serializable {

	private static final long serialVersionUID = 1L;

	public String filter(String message) {
		if (message == null || message.length() == 0)
			return message;
		return message.trim();
	}

}
