package org.commontemplate.core;

public class InvaildVariableNameException extends VariableException {

	private static final long serialVersionUID = 1L;

	public InvaildVariableNameException(String variableName) {
		super(variableName, "Invaild Variable Name: " + variableName);
	}

	public InvaildVariableNameException(String variableName, String message) {
		super(variableName, message);
	}

}
