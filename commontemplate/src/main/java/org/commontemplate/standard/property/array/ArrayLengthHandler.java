package org.commontemplate.standard.property.array;

import java.lang.reflect.Array;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class ArrayLengthHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		return new Integer(Array.getLength(bean));
	}

}
