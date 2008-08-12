package org.commontemplate.standard.property.array;

import java.util.Arrays;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class ArraySortPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		Object[] array = (Object[])bean;
		Object[] result = new Object[array.length];
		System.arraycopy(array, 0, result, 0, array.length);
		Arrays.sort(result);
		return result;
	}

}
