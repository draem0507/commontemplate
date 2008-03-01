package org.commontemplate.standard.operator.number;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.NumberArithmetic;

public class NumberMultiplyOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NumberMultiplyOperatorHandler() {
		super(Number.class, Number.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return NumberArithmetic.multiply((Number)leftOperand, (Number)rightOperand);
	}

}
