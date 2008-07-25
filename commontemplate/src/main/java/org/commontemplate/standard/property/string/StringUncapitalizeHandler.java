package org.commontemplate.standard.property.string;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class StringUncapitalizeHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		String str = ((String)bean);
		if (str.length() == 0)
			return str;
		if (str.length() == 1)
			return str.toLowerCase();
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

}