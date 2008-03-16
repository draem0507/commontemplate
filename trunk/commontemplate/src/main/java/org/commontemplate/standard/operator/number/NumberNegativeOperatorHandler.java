package org.commontemplate.standard.operator.number;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;
import org.commontemplate.util.NumberArithmetic;

/**
 * 数字取负运算一元操作符: "-"<br/>
 * 如: ${-12} ${-num1}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class NumberNegativeOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NumberNegativeOperatorHandler() {
		super(Number.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		return NumberArithmetic.negative((Number)operand);
	}

}
