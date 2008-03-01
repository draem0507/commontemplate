package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class StringIngoreCaseEqualsOperatorHanlder extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringIngoreCaseEqualsOperatorHanlder() {
		super(String.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf((((String)leftOperand)).trim().equalsIgnoreCase(((String)rightOperand).trim()));
	}

}