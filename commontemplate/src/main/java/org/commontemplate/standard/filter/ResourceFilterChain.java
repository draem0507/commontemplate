package org.commontemplate.standard.filter;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.commontemplate.config.ResourceFilter;

public class ResourceFilterChain implements ResourceFilter {

	private List templateReaderFilters;

	public void setResourceFilters(List templateReaderFilters) {
		this.templateReaderFilters = templateReaderFilters;
	}

	public Reader filter(Reader reader) throws IOException {
		if (templateReaderFilters != null && templateReaderFilters.size() > 0) {
			for (int i = 0, n = templateReaderFilters.size(); i < n; i++) {
				reader = ((ResourceFilter) templateReaderFilters.get(i))
						.filter(reader);
			}
		}
		return reader;
	}

}