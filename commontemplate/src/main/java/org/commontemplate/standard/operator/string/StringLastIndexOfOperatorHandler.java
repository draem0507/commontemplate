package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class StringLastIndexOfOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringLastIndexOfOperatorHandler() {
		super(String.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return new Integer(((String)leftOperand).lastIndexOf((String)rightOperand));
	}

}