package org.commontemplate.standard.property.number;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class MonthPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object getProperty(Object bean) throws Exception {
		return new DateField(DateField.MONTH, ((Number)bean).intValue());
	}

}