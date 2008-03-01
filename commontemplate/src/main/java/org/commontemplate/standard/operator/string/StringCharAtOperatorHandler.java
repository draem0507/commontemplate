package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class StringCharAtOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringCharAtOperatorHandler() {
		super(String.class, Integer.class, true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return new Character(((String)leftOperand).charAt(((Integer)rightOperand).intValue()));
	}

}