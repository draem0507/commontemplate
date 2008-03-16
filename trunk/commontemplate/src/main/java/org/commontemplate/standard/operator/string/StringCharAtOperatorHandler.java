package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 字符串指定位置操作符: "[]"<br/>
 * 如: ${str[2]}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class StringCharAtOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringCharAtOperatorHandler() {
		super(String.class, Integer.class, true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return new Character(((String)leftOperand).charAt(((Integer)rightOperand).intValue()));
	}

}