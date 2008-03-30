package org.commontemplate.standard.property.string;

import org.commontemplate.standard.property.PropertyHandlerSupport;
import org.commontemplate.util.JavaStringConvertUtils;

public class StringEscapeJsHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object getProperty(Object bean) throws Exception {
		String str = (String)bean;
		return JavaStringConvertUtils.revertLiteral(str);
	}

}