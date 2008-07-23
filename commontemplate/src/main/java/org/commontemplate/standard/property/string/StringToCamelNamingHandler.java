package org.commontemplate.standard.property.string;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class StringToCamelNamingHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public static final char UNDERLINE = '_';

	public Object doProperty(Object bean) throws Exception {
		String name = (String)bean;
		StringBuffer buf = new StringBuffer();
		boolean upper = false;
		for (int i = 0, n = name.length(); i < n; i ++) {
			char ch = name.charAt(i);
			if (ch == UNDERLINE) {
				upper = true;
			} else {
				if (upper) {
					buf.append(Character.toUpperCase(ch));
					upper = false;
				} else {
					buf.append(ch);
				}
			}
		}
		buf.setCharAt(0, Character.toLowerCase(buf.charAt(0)));
		return buf.toString();
	}

}
