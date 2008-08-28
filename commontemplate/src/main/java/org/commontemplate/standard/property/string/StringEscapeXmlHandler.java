package org.commontemplate.standard.property.string;

import org.commontemplate.standard.property.PropertyHandlerSupport;
import org.commontemplate.util.coder.XML;

public class StringEscapeXmlHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		 return XML.encode((String)bean);
	}

}