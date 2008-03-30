package org.commontemplate.standard.property.string;

import org.commontemplate.standard.property.PropertyHandlerSupport;

public class StringToCharacterHandler extends PropertyHandlerSupport {

	private static final long serialVersionUID = 1L;

	public Object getProperty(Object bean) throws Exception {
		String s = (String)bean;
		if (s.length() == 0)
			return new Character('\0');
		if (s.length() == 1)
			return new Character(s.charAt(0));
		throw new java.lang.IllegalArgumentException(s + "toCharacter failure: length != 1");
	}

}