package org.commontemplate.standard.operator.context;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

public class VariableOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public VariableOperatorHandler() {
		super(Object.class);
	}

	public Object doEvaluate(Object operand)
			throws Exception {
		return operand;
	}

}
