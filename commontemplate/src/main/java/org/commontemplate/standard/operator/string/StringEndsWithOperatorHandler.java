package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 字符串是否以某子串结尾判断操作符: "$="<br/>
 * 如: ${"abc" $= "bc"}<br/>
 *
 * @see java.lang.String#endsWith(String)
 * @author liangfei0201@163.com
 *
 */
public class StringEndsWithOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringEndsWithOperatorHandler() {
		super(String.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf(((String)leftOperand).endsWith((String)rightOperand));
	}

}