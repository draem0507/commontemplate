package org.commontemplate.standard.property.bool;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class BooleanToIntegerHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		return ((Boolean)bean).booleanValue() ? new Integer(1) : new Integer(0);
	}

}