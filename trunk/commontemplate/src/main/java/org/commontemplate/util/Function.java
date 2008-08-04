package org.commontemplate.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 代表对象的一个函数
 *
 * @author liangfei0201@163.com
 *
 */
public final class Function {

	private final String name;

	private final Object argument;

	public Function(String name, Object argument) {
		super();
		this.name = name;
		this.argument = argument;
	}

	public final String getName() {
		return name;
	}

	public final Object getArgument() {
		return argument;
	}

	public final List getArguments() {
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

}
