package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 字符串忽略大小写比较操作符: "~="<br/>
 * 如: ${"abc" ~= "ABC"}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class StringIngoreCaseEqualsOperatorHanlder extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringIngoreCaseEqualsOperatorHanlder() {
		super(String.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf((((String)leftOperand)).trim().equalsIgnoreCase(((String)rightOperand).trim()));
	}

}