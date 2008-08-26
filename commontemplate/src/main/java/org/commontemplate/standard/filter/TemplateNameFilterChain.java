package org.commontemplate.standard.filter;

import java.io.Serializable;
import java.util.List;

import org.commontemplate.config.InvalidTemplateNameException;
import org.commontemplate.config.TemplateNameFilter;

public class TemplateNameFilterChain implements TemplateNameFilter, Serializable {

	private static final long serialVersionUID = 1L;

	private List templateNameFilters;

	public void setTemplateNameFilters(List templateNameFilters) {
		this.templateNameFilters = templateNameFilters;
	}

	public String filter(String path, String currentTemplateName)
			throws InvalidTemplateNameException {
		if (templateNameFilters != null && templateNameFilters.size() > 0) {
			for (int i = 0, n = templateNameFilters.size(); i < n; i++) {
				TemplateNameFilter templateNameFilter = (TemplateNameFilter) templateNameFilters.get(i);
				if (templateNameFilter != null) {
					path = templateNameFilter.filter(
							path, currentTemplateName);
				}
			}
		}
		return path;
	}

}