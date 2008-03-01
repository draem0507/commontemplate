package org.commontemplate.core;

/**
 * 变量异常
 * 
 * @author liangfei0201@163.com
 * 
 */
public class VariableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String variableName;

	public VariableException(String message, String variableName) {
		super(message);
		this.variableName = variableName;
	}

	public String getVariableName() {
		return variableName;
	}

}
