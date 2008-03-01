package org.commontemplate.core;

/**
 * 变量已定义异常
 * 
 * @author liangfei0201@163.com
 * 
 */
public class DefinedException extends VariableException {

	private static final long serialVersionUID = 1L;

	public DefinedException(String variableName) {
		super(variableName + " 已定义! 不能重复定义!", variableName);
	}

	public DefinedException(String message, String variableName) {
		super(message, variableName);
	}

}
