package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class StringRepeatOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringRepeatOperatorHandler() {
		super(String.class, Integer.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		String s = ((String)leftOperand);
		int n = ((Integer)rightOperand).intValue();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < n; i ++)
			buf.append(s);
		return buf.toString();
	}

}