package org.commontemplate.standard.operator.number;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.NumberArithmetic;

/**
 * 数字求模运算操作符: "%"<br/>
 * 如: ${12 % 2} ${num1 % num2}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class NumberModulusOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NumberModulusOperatorHandler() {
		super(Number.class, Number.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return NumberArithmetic.modulus((Number)leftOperand, (Number)rightOperand);
	}

}
