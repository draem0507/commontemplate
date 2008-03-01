package org.commontemplate.standard.operator.number;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;
import org.commontemplate.util.NumberArithmetic;

public class NumberNegativeOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NumberNegativeOperatorHandler() {
		super(Number.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		return NumberArithmetic.negative((Number)operand);
	}

}
