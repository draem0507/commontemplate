package org.commontemplate.standard.directive.filter.space;

import java.io.Serializable;

import org.commontemplate.core.OutputFilter;

public class LeftTrimSpaceFilter implements OutputFilter, Serializable {

	private static final long serialVersionUID = 1L;

	public String filter(String message) {
		if (message == null || message.length() == 0)
			return message;
		int count = 0;
		for (int i = 0, n = message.length(); i < n; i ++) {
			char ch = message.charAt(i);
			if (ch <= ' ') {
				count ++;
			} else {
				break;
			}
		}
		if (count == 0)
			return message;
		return message.substring(count);
	}

}
