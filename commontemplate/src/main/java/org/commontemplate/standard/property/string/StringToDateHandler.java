package org.commontemplate.standard.property.string;

import java.text.SimpleDateFormat;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class StringToDateHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public Object doProperty(Object bean) throws Exception {
		return new SimpleDateFormat(DATE_FORMAT).parse((String)bean);
	}

}