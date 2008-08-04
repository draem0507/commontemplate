package org.commontemplate.standard.directive.filter.space;

import java.io.Serializable;

import org.commontemplate.core.OutputFilter;

public class RightTrimSpaceFilter implements OutputFilter, Serializable {

	private static final long serialVersionUID = 1L;

	public String filter(String message) {
		if (message == null || message.length() == 0)
			return message;
		int count = message.length();
		for (int i = count - 1; i >= 0; i --) {
			char ch = message.charAt(i);
			if (ch <= ' ') {
				count --;
			} else {
				break;
			}
		}
		if (count == message.length())
			return message;
		return message.substring(0, count);
	}

}
