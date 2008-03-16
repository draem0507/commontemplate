package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 字符串正则表达式不配匹操作符: "!~"<br/>
 * 如: ${"123" !~ "^[a-z]+$"}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class StringNotMatchOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringNotMatchOperatorHandler() {
		super(String.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf(! ((String)leftOperand).matches((String)rightOperand));
	}

}