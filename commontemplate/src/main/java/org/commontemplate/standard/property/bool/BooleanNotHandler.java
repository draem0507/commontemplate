package org.commontemplate.standard.property.bool;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class BooleanNotHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		return Boolean.valueOf(! ((Boolean)bean).booleanValue());
	}

}
