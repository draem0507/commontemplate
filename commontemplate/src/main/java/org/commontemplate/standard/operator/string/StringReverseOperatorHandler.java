package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

public class StringReverseOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringReverseOperatorHandler() {
		super(String.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		return new StringBuffer((String)operand).reverse().toString();
	}

}