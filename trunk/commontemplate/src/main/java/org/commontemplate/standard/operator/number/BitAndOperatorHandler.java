package org.commontemplate.standard.operator.number;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class BitAndOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public BitAndOperatorHandler() {
		super(Number.class, Number.class);
	}
	
	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return new Integer(((Number)leftOperand).intValue() & ((Number)rightOperand).intValue());
	}

}