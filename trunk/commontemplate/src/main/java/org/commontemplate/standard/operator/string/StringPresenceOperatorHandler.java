package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

public class StringPresenceOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringPresenceOperatorHandler() {
		super(String.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		String str = (String)operand;
		return Boolean.valueOf(str != null && str.trim().length() > 0);
	}

}