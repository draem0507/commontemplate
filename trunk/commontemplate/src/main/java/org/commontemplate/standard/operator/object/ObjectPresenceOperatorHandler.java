package org.commontemplate.standard.operator.object;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

public class ObjectPresenceOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ObjectPresenceOperatorHandler() {
		super(Object.class, true);
	}

	public Object doEvaluate(Object operand) throws Exception {
		return Boolean.valueOf(operand != null);
	}

}