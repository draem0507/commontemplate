package org.commontemplate.standard.filter;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.List;

import org.commontemplate.config.ResourceFilter;

public class ResourceFilterChain implements ResourceFilter, Serializable {

	private static final long serialVersionUID = 1L;

	private List templateReaderFilters;

	public void setResourceFilters(List templateReaderFilters) {
		this.templateReaderFilters = templateReaderFilters;
	}

	public Reader filter(Reader reader) throws IOException {
		if (templateReaderFilters != null && templateReaderFilters.size() > 0) {
			for (int i = 0, n = templateReaderFilters.size(); i < n; i++) {
				ResourceFilter resourceFilter = (ResourceFilter)templateReaderFilters.get(i);
				if (resourceFilter != null) {
					reader = resourceFilter.filter(reader);
				}
			}
		}
		return reader;
	}

}