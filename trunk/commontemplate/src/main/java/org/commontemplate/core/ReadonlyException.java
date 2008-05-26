package org.commontemplate.core;

/**
 * 变量只读异常, 当给"只读变量"或"锁定的容器"内赋值时抛出.
 *
 * @author liangfei0201@163.com
 *
 */
public class ReadonlyException extends VariableException {

	private static final long serialVersionUID = 1L;

	public ReadonlyException(String variableName, String message) {
		super(variableName, message);
	}

}
