package org.commontemplate.standard.property.string;

import java.text.SimpleDateFormat;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class StringToTimeHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public static final String TIME_FORMAT = "hh:mm:ss";

	public Object doProperty(Object bean) throws Exception {
		return new SimpleDateFormat(TIME_FORMAT).parse((String)bean);
	}

}