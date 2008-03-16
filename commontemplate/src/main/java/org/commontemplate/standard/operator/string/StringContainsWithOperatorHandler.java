package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 字符串是否包含某子串判断操作符: "*="<br/>
 * 如: ${"abc" *= "bc"}<br/>
 *
 * @see java.lang.String#indexOf(int)
 * @author liangfei0201@163.com
 *
 */
public class StringContainsWithOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringContainsWithOperatorHandler() {
		super(String.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf(((String)leftOperand).indexOf((String)rightOperand) != -1);
	}

}