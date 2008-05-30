package org.commontemplate.standard.property.string;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class StringEscapeXmlHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		String str = (String)bean;
		return str.replaceAll("&", "&amp;")
					.replaceAll(">", "&gt;")
					.replaceAll("<", "&lt;")
					.replaceAll("\"", "&quot;")
					.replaceAll("\'", "&apos;");
	}

}