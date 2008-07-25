package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class StringPrefixOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringPrefixOperatorHandler() {
		super(String.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		String str = (String)leftOperand;
		int i = str.indexOf((String)rightOperand);
		if (i == -1)
			return null;
		return str.substring(0, i);
	}

}