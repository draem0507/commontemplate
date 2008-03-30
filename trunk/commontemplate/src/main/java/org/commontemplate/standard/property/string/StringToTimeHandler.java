package org.commontemplate.standard.property.string;

import java.text.SimpleDateFormat;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class StringToTimeHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object getProperty(Object bean) throws Exception {
		return new SimpleDateFormat("hh:mm:ss").parse((String)bean);
	}

}