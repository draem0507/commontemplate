package org.commontemplate.standard.property.string;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class StringToByteHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		return new Byte((String)bean);
	}

}