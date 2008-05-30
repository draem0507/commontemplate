package org.commontemplate.standard.property.string;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class StringCapitalHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		String str = ((String)bean);
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

}