package org.commontemplate.standard.operator.bool;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 布尔值"异或"逻辑操作符: "^" 或 "xor"<br/>
 * 如: $if{isX ^ isY} $if{isX xor isY}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class BooleanXorOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public BooleanXorOperatorHandler() {
		super(Boolean.class, Boolean.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf(((Boolean)leftOperand).booleanValue() ^ ((Boolean)rightOperand).booleanValue());
	}

}