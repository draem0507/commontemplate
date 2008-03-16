package org.commontemplate.standard.operator.number;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

/**
 * 按位取反运算一元操作符: "~"<br/>
 * 如: ${~ 0x4EFD}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class BitNotOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public BitNotOperatorHandler() {
		super(Number.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		return new Integer(~((Number)operand).intValue());
	}

}