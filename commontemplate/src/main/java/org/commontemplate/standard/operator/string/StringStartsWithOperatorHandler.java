package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 字符串是否以某子串开头判断操作符: "^="<br/>
 * 如: ${"abc" ^= "bc"}<br/>
 *
 * @see java.lang.String#startsWith(String)
 * @author liangfei0201@163.com
 *
 */
public class StringStartsWithOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringStartsWithOperatorHandler() {
		super(String.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf(((String)leftOperand).startsWith((String)rightOperand));
	}

}