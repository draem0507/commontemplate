package org.commontemplate.standard.property.string;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class StringEscapeHtmlHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object handleProperty(Object bean) throws Exception {
		String str = (String)bean;
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("<", "&lt;");
		return str;
	}

}