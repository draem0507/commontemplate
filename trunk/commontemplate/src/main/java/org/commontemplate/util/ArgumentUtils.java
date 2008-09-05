package org.commontemplate.util;

import java.util.ArrayList;
import java.util.List;

public final class ArgumentUtils {

	private ArgumentUtils(){}

	public static List getArgumentList(Object argument) {
		List args = null;
		if (argument == null) {
			args = new ArrayList(0);
		} else if (argument instanceof List) {
			args = (List)argument;
		} else {
			args = new ArrayList(1);
			args.add(argument);
		}
		return args;
	}

	public static Object[] getArgumentArray(Object argument) {
		return getArgumentList(argument).toArray();
	}

}
