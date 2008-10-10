package org.commontemplate.util;

import java.io.Serializable;

/**
 * 代表对象的一个函数
 *
 * @author liangfei0201@163.com
 *
 */
public class Function implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String name;

	private final Object argument;

	public Function(String name, Object argument) {
		this.name = name;
		this.argument = argument;
	}

	public String getName() {
		return name;
	}

	public Object getArgument() {
		return argument;
	}

}
