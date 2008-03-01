package org.commontemplate.standard.property.number;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class MinutePropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object handleProperty(Object bean) throws Exception {
		return new DateField(DateField.MINUTE, ((Number)bean).intValue());
	}

}