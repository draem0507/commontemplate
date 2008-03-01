package org.commontemplate.standard.operator.number;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class NumberApproximateEqualsOperatorHanlder extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NumberApproximateEqualsOperatorHanlder() {
		super(Number.class, Number.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf(((Number)leftOperand).intValue() == ((Number)rightOperand).intValue());
	}

}