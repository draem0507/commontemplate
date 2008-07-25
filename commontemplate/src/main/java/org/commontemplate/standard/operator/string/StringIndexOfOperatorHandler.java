package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class StringIndexOfOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringIndexOfOperatorHandler() {
		super(String.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return new Integer(((String)leftOperand).indexOf((String)rightOperand));
	}

}