package org.commontemplate.standard.property.number;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class DoubleToCeilingIntegerPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		Double value = (Double)bean;
		double d = value.doubleValue();
		int i = value.intValue();
		if (d - i != 0) {
			if (d > 0) {
				i ++;
			} else {
				i --;
			}
		}
		return new Integer(i);
	}

}
