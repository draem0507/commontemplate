package org.commontemplate.standard.operator.number;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 数字幂运算操作符: "**"<br/>
 * 如: ${12 ** 2} ${num1 ** num2}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class NumberPowerOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NumberPowerOperatorHandler() {
		super(Number.class, Number.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return new Double(Math.pow(((Number)leftOperand).doubleValue(), ((Number)rightOperand).doubleValue()));
	}

}
