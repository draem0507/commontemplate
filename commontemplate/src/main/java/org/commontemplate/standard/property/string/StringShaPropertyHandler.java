package org.commontemplate.standard.property.string;

import org.commontemplate.standard.property.PropertyHandlerSupport;
import org.commontemplate.util.coder.SHA;

public class StringShaPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object getProperty(Object bean) throws Exception {
		return SHA.encode((String)bean);
	}

}