package org.commontemplate.standard.property.number;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class MillisecondPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object getProperty(Object bean) throws Exception {
		return new DateField(DateField.MILLISECOND, ((Number)bean).intValue());
	}

}