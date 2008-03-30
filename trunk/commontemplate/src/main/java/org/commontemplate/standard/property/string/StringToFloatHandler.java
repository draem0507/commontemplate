package org.commontemplate.standard.property.string;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class StringToFloatHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object getProperty(Object bean) throws Exception {
		return new Float((String)bean);
	}

}