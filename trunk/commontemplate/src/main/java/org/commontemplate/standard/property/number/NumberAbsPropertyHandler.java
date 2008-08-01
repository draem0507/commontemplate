package org.commontemplate.standard.property.number;

import org.commontemplate.standard.property.PropertyHandlerSupport;
import org.commontemplate.util.NumberArithmetic;

public class NumberAbsPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		Number num = (Number)bean;
		double d = num.doubleValue();
		if (d < 0)
			return NumberArithmetic.negative(num);
		return num;
	}

}