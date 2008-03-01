package org.commontemplate.standard.operator.bool;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;
import org.commontemplate.util.TypeUtils;

/**
 * 布尔值"非"运算
 * 
 * @author liangfei0201@163.com
 *
 */
public class BooleanNotOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public BooleanNotOperatorHandler() {
		super(Object.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		return Boolean.valueOf(! TypeUtils.isTrue(operand));
	}

}