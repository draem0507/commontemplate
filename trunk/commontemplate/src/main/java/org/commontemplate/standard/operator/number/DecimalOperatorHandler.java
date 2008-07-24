package org.commontemplate.standard.operator.number;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

public class DecimalOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public DecimalOperatorHandler() {
		super(Number.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		Number num = (Number)operand;
		int value = num.intValue();
		if (num instanceof Float)
			return Float.valueOf("0." + value);
		return Double.valueOf("0." + value);
	}

}