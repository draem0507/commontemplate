package org.commontemplate.standard.filter;

import org.commontemplate.config.ExpressionFilter;

public class ExpressionReplacementFilter implements ExpressionFilter {

	public String filter(String text) {
		if (text == null)
			return null;
		return text.replaceAll("\\&lt\\;", "<").replaceAll("\\&gt\\;", ">");
	}

}
