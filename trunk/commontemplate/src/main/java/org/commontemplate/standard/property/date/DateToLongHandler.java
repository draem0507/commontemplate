package org.commontemplate.standard.property.date;

import java.util.Date;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class DateToLongHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		return new Long(((Date)bean).getTime());
	}

}