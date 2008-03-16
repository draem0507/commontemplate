package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

/**
 * 字符串反转一元操作符: "-"<br/>
 * 如: ${-"abc"}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class StringReverseOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringReverseOperatorHandler() {
		super(String.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		return new StringBuffer((String)operand).reverse().toString();
	}

}