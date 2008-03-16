package org.commontemplate.standard.operator.number;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 按位或运算操作符: "^"<br/>
 * 如: ${0x4EFD ^ 0x0001}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class BitXorOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public BitXorOperatorHandler() {
		super(Number.class, Number.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return new Integer(((Number)leftOperand).intValue() ^ ((Number)rightOperand).intValue());
	}

}