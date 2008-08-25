package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 字符重复操作符: "*"<br/>
 * 如: ${"abc" * 2}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class StringRepeatOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringRepeatOperatorHandler() {
		super(String.class, Integer.class, false, false, true); // 左右参数可交换
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		String src;
		int repeat;
		if (leftOperand instanceof String) {
			src = (String)leftOperand;
			repeat = ((Integer)rightOperand).intValue();
		} else {
			src = (String)rightOperand;
			repeat = ((Integer)leftOperand).intValue();
		}
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < repeat; i ++)
			buf.append(src);
		return buf.toString();
	}

}