package org.commontemplate.standard.property.string;

import org.commontemplate.standard.property.PropertyHandlerSupport;
import org.commontemplate.util.coder.MD5;

public class StringMd5PropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object getProperty(Object bean) throws Exception {
		return MD5.encode((String)bean);
	}

}