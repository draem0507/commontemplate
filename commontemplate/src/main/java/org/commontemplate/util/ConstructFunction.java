package org.commontemplate.util;

/**
 * 类构造函数
 *
 * @author liangfei0201@163.com
 *
 */
public class ConstructFunction extends Function {

	private static final long serialVersionUID = 1L;

	public ConstructFunction(Class clazz, Object argument) {
		super(clazz == null ? null : clazz.getName(), argument);
		this.clazz = clazz;
	}

	private Class clazz;

	public Class getConstructClass() {
		return clazz;
	}

}
