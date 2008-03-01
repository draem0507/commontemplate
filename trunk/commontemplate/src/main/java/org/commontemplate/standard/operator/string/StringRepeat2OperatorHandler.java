package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class StringRepeat2OperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringRepeat2OperatorHandler() {
		super(Integer.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		int n = ((Integer)leftOperand).intValue();
		String s = ((String)rightOperand);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < n; i ++)
			buf.append(s);
		return buf.toString();
	}

}