package org.commontemplate.standard.operator.compare;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class AddressEqualsOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public AddressEqualsOperatorHandler() {
		super(Object.class, Object.class, true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		if (leftOperand == null && rightOperand == null)
			return Boolean.TRUE;
		if (leftOperand == null || rightOperand == null)
			return Boolean.FALSE;
		return Boolean.valueOf(leftOperand == rightOperand);
	}

}