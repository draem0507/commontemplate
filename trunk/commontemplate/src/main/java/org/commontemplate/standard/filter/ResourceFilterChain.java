package org.commontemplate.standard.filter;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.List;

import org.commontemplate.config.ResourceFilter;

public class ResourceFilterChain implements ResourceFilter, Serializable {

	private static final long serialVersionUID = 1L;

	private List resourceFilters;

	public void setResourceFilters(List resourceFilters) {
		this.resourceFilters = resourceFilters;
	}

	public Reader filter(Reader reader) throws IOException {
		if (resourceFilters != null && resourceFilters.size() > 0) {
			for (int i = 0, n = resourceFilters.size(); i < n; i++) {
				ResourceFilter resourceFilter = (ResourceFilter)resourceFilters.get(i);
				if (resourceFilter != null) {
					reader = resourceFilter.filter(reader);
				}
			}
		}
		return reader;
	}

}