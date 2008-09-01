package org.commontemplate.standard.filter;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.commontemplate.config.ExpressionFilter;

public class ExpressionFilterChain implements ExpressionFilter, Serializable {

	private static final long serialVersionUID = 1L;

	private List filters;

	public void setExpressionFilters(List filters) {
		this.filters = filters;
	}

	public String filter(String text) {
		for (Iterator iterator = filters.iterator(); iterator.hasNext();) {
			ExpressionFilter filter = (ExpressionFilter)iterator.next();
			if (filter != null) {
				text = filter.filter(text);
			}
		}
		return text;
	}

}
