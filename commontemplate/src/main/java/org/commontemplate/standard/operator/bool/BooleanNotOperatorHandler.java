package org.commontemplate.standard.operator.bool;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;
import org.commontemplate.util.TypeUtils;

/**
 * 布尔值"非"逻辑操作符: "!"<br/>
 * 如: $if{! isOk}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class BooleanNotOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public BooleanNotOperatorHandler() {
		super(Object.class, true);
	}

	public Object doEvaluate(Object operand) throws Exception {
		return Boolean.valueOf(! TypeUtils.isTrue(operand));
	}

}