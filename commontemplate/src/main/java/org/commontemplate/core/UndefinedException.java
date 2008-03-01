package org.commontemplate.core;

/**
 * 变量未定义异常
 * 
 * @author liangfei0201@163.com
 * 
 */
public class UndefinedException extends VariableException {

	private static final long serialVersionUID = 1L;

	public UndefinedException(String variableName) {
		super(variableName + " 未定义!", variableName);
	}

	public UndefinedException(String message, String variableName) {
		super(message, variableName);
	}

}
