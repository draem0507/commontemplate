package org.commontemplate.core;

public class UndefinedException extends VariableException {

	private static final long serialVersionUID = 1L;

	public UndefinedException(String variableName) {
		super(variableName, "Undefined Variable: " + variableName);
	}

}
