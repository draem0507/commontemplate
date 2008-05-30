package org.commontemplate.standard.property.string;

import java.text.SimpleDateFormat;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class StringToDateTimeHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";

	public Object doProperty(Object bean) throws Exception {
		return new SimpleDateFormat(DATE_TIME_FORMAT).parse((String)bean);
	}

}