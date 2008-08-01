package org.commontemplate.standard.property.number;

import org.commontemplate.standard.property.PropertyHandlerSupport;
import org.commontemplate.util.NumberUtils;

public class NumberSignPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		double d = ((Number)bean).doubleValue();
		if (d < 0)
			return NumberUtils.INTEGER_NEGATIVE_ONE;
		if (d > 0)
			return NumberUtils.INTEGER_POSITIVE_ONE;
		return NumberUtils.INTEGER_ZERO;
	}

}