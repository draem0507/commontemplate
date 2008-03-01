package org.commontemplate.standard.property.array;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class ArrayLengthHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object handleProperty(Object bean) throws Exception {
		return new Integer(((Object[])bean).length);
	}

}
