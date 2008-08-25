package org.commontemplate.standard.directive.filter;

import java.util.HashMap;
import java.util.Map;

import org.commontemplate.core.OutputConverter;
import org.commontemplate.standard.operator.collection.Filter;

public class ValueOutputConverter implements OutputConverter {

	private final Filter filter;

	private String valueName;

	public ValueOutputConverter(Filter filter, String valueName) {
		super();
		this.filter = filter;
		this.valueName = valueName;
	}

	public Object convert(Object msg) {
		Map variables = new HashMap(4);
		variables.put(resolveValueName(filter.getName()), msg);
		return filter.filter(variables);
	}

	public final String resolveValueName(String name) {
		if (name != null && name.length() > 0)
			return name;
		return valueName;
	}

}
