package org.commontemplate.standard.filter;

import java.util.List;

import org.commontemplate.config.TextFilter;

public class TextFilterChain implements TextFilter, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private List textFilters;

	public void setTextFilters(List textFilters) {
		this.textFilters = textFilters;
	}

	public String filter(String text, boolean isFirst, boolean isLast) {
		if (textFilters != null && textFilters.size() > 0) {
			for (int i = 0, n = textFilters.size(); i < n; i++) {
				TextFilter textFilter = (TextFilter) textFilters.get(i);
				if (textFilter != null) {
					text = textFilter.filter(text, isFirst, isLast);
				}
			}
		}
		return text;
	}

}