package org.commontemplate.standard.directive.filter;

import java.util.HashMap;
import java.util.Map;

import org.commontemplate.core.OutputFilter;
import org.commontemplate.standard.operator.collection.Filter;

public class ValueOutputFilter implements OutputFilter {
	
	private final Filter filter;
	
	private String valueName;

	public ValueOutputFilter(Filter filter, String valueName) {
		super();
		this.filter = filter;
		this.valueName = valueName;
	}

	public String filter(String text) {
		Map variables = new HashMap(4);
		variables.put(resolveValueName(filter.getName()), text);
		return String.valueOf(filter.filter(variables));
	}

	public final String resolveValueName(String name) {
		if (name != null && name.length() > 0) 
			return name;
		return valueName;
	}
	
}