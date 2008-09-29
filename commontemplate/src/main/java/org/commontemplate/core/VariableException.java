package org.commontemplate.core;

import org.commontemplate.util.I18nRuntimeException;

/**
 * 变量异常
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class VariableException extends I18nRuntimeException {

	private String variableName;

	public VariableException(String variableName, String message) {
		super(message, new Object[]{variableName});
		this.variableName = variableName;
	}

	public VariableException(String variableName, String message, Object[] args) {
		super(message, args);
		this.variableName = variableName;
	}

	public String getVariableName() {
		return variableName;
	}

}
