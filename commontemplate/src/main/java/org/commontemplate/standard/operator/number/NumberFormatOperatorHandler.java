package org.commontemplate.standard.operator.number;

import java.text.DecimalFormat;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class NumberFormatOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NumberFormatOperatorHandler() {
		super(Number.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return new DecimalFormat((String)rightOperand).format((Number)leftOperand);
	}

}
