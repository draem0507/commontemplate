package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 字符串正则表达式配匹操作符: "~"<br/>
 * 如: ${"abc" ~ "^[a-z]+$"}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class StringMatchOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringMatchOperatorHandler() {
		super(String.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf(((String)leftOperand).matches((String)rightOperand));
	}

}