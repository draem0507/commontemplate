package org.commontemplate.standard.operator.number;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class BitLeftShiftOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public BitLeftShiftOperatorHandler() {
		super(Number.class, Number.class);
	}
	
	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return new Integer(((Number)leftOperand).intValue() << ((Number)rightOperand).intValue());
	}

}