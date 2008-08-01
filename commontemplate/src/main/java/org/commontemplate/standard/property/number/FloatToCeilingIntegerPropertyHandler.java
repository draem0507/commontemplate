package org.commontemplate.standard.property.number;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class FloatToCeilingIntegerPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		Float value = (Float)bean;
		float f = value.floatValue();
		int i = value.intValue();
		if (f - i != 0) {
			if (f > 0) {
				i ++;
			} else {
				i --;
			}
		}
		return new Integer(i);
	}

}
