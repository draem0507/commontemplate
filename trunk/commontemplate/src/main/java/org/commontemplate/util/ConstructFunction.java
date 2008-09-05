package org.commontemplate.util;

/**
 * 类构造函数
 *
 * @author liangfei0201@163.com
 *
 */
public class ConstructFunction extends Function {

	public ConstructFunction(Class clazz, Object argument) {
		super(clazz == null ? null : clazz.getName(), argument);
	}

	private Class clazz;

	public Class getConstructClass() {
		return clazz;
	}

}
