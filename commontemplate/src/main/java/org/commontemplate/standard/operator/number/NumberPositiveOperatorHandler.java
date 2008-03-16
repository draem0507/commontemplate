package org.commontemplate.standard.operator.number;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

/**
 * 数字取正运算一元操作符: "+"<br/>
 * 如: ${+12} ${+num1}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class NumberPositiveOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NumberPositiveOperatorHandler() {
		super(Number.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		return operand;
	}

}
