package org.commontemplate.standard.property.string;

import org.commontemplate.standard.property.PropertyHandlerSupport;
import org.commontemplate.util.TypeUtils;

public class StringIsNamingPropertyHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object doProperty(Object bean) throws Exception {
		String str = (String)bean;
		return Boolean.valueOf(TypeUtils.isNamed(str));
	}

}
