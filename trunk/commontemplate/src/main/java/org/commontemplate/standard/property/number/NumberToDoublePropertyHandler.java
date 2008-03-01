package org.commontemplate.standard.property.number;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class NumberToDoublePropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object handleProperty(Object bean) throws Exception {
		return new Double(((Number)bean).doubleValue());
	}

}
