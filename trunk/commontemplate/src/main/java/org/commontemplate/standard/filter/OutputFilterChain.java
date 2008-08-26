package org.commontemplate.standard.filter;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.commontemplate.core.OutputFilter;

public class OutputFilterChain implements OutputFilter, Serializable {

	private static final long serialVersionUID = 1L;

	private List filters;

	public OutputFilterChain(List filters) {
		super();
		this.filters = filters;
	}

	public String filter(String text) {
		for (Iterator iterator = filters.iterator(); iterator.hasNext();) {
			OutputFilter outputFilter = (OutputFilter)iterator.next();
			if (outputFilter != null) {
				text = outputFilter.filter(text);
			}
		}
		return text;
	}

}
