package org.commontemplate.standard.operator.number;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 数字约等于比较操作符: "~="<br/>
 * 如: ${12.1 ~= 12} ${num1 ~= num2}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class NumberApproximateEqualsOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NumberApproximateEqualsOperatorHandler() {
		super(Number.class, Number.class, true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		if (leftOperand == null && rightOperand == null)
			return Boolean.TRUE;
		if (leftOperand == null || rightOperand == null)
			return Boolean.FALSE;
		return Boolean.valueOf(((Number)leftOperand).intValue() == ((Number)rightOperand).intValue());
	}

}