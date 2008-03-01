package org.commontemplate.standard.property.number;

import java.util.Date;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class LongToDateHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object handleProperty(Object bean) throws Exception {
		return new Date(((Number)bean).longValue());
	}

}