package org.commontemplate.standard.property.string;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class StringToUnderlineNamingHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public static final char UNDERLINE = '_';

	public Object doProperty(Object bean) throws Exception {
		String name = (String)bean;
		StringBuffer buf = new StringBuffer();
		for (int i = 0, n = name.length(); i < n; i ++) {
			char ch = name.charAt(i);
			if (i != 0 && ch >= 65 && ch <= 90)
				buf.append(UNDERLINE);
			buf.append(Character.toLowerCase(ch));
		}
		return buf.toString();
	}

}
