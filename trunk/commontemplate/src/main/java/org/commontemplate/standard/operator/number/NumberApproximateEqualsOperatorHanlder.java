package org.commontemplate.standard.operator.number;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 数字约等于比较操作符: "~="<br/>
 * 如: ${12.1 ~= 12} ${num1 ~= num2}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class NumberApproximateEqualsOperatorHanlder extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NumberApproximateEqualsOperatorHanlder() {
		super(Number.class, Number.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf(((Number)leftOperand).intValue() == ((Number)rightOperand).intValue());
	}

}