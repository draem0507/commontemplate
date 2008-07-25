package org.commontemplate.standard.property.string;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class StringCapitalizeHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		String str = ((String)bean);
		if (str.length() == 0)
			return str;
		if (str.length() == 1)
			return str.toUpperCase();
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

}