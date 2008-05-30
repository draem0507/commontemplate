package org.commontemplate.standard.property.string;

import java.net.URLEncoder;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class StringEscapeUrlHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		String str = (String)bean;
		return URLEncoder.encode(str, "UTF-8");
	}

}