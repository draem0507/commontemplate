package org.commontemplate.util;

import java.util.Collections;
import java.util.List;

/**
 * 代表对象的一个函数
 * 
 * @author liangfei0201@163.com
 *
 */
public final class Function {
	
	private final String name;
	
	private final List arguments;

	public Function(String name, List arguments) {
		super();
		this.name = name;
		this.arguments = Collections.unmodifiableList(arguments);
	}

	public final String getName() {
		return name;
	}

	public final List getArguments() {
		return arguments;
	}

}
