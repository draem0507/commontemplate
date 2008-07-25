package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 字符串忽略大小写比较操作符: "~="<br/>
 * 如: ${"abc" ~= "ABC"}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class StringIgnoreCaseEqualsOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringIgnoreCaseEqualsOperatorHandler() {
		super(String.class, String.class, true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		if (leftOperand == null && rightOperand == null)
			return Boolean.TRUE;
		if (leftOperand == null || rightOperand == null)
			return Boolean.FALSE;
		return Boolean.valueOf((((String)leftOperand)).trim().equalsIgnoreCase(((String)rightOperand).trim()));
	}

}