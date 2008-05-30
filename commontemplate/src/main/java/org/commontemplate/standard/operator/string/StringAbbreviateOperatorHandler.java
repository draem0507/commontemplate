package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class StringAbbreviateOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringAbbreviateOperatorHandler() {
		super(String.class, Number.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		String str = (String)leftOperand;
		int len = ((Number)rightOperand).intValue();
		// TODO 待参考apache-commons StringUtils.abbreviate()实现
		if (len <= 0)
			return "";
		if (len < str.length())
			return str.substring(0, len);
		return str;
	}

}
