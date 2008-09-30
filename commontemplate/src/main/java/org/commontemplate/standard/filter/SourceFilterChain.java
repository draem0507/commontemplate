package org.commontemplate.standard.filter;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.List;

import org.commontemplate.config.SourceFilter;

public class SourceFilterChain implements SourceFilter, Serializable {

	private static final long serialVersionUID = 1L;

	private List sourceFilters;

	public void setSourceFilters(List sourceFilters) {
		this.sourceFilters = sourceFilters;
	}

	public Reader filter(Reader reader) throws IOException {
		if (sourceFilters != null && sourceFilters.size() > 0) {
			for (int i = 0, n = sourceFilters.size(); i < n; i++) {
				SourceFilter resourceFilter = (SourceFilter)sourceFilters.get(i);
				if (resourceFilter != null) {
					reader = resourceFilter.filter(reader);
				}
			}
		}
		return reader;
	}

}