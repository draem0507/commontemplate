package org.commontemplate.standard.directive.filter.escape;

import java.io.Serializable;

import org.commontemplate.core.OutputFilter;
import org.commontemplate.util.coder.HTML;

public class HtmlEscapeFilter implements OutputFilter, Serializable {

	private static final long serialVersionUID = 1L;

	public String filter(String message) {
		return HTML.encode(message);
	}

}
