package org.commontemplate.standard.operator.object;

import org.commontemplate.util.Function;

public class ClassStaticFunctionOperatorHandler extends ClassStaticOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ClassStaticFunctionOperatorHandler() {
		super(Function.class);
	}
	
	protected String getOperandName(Object operand) {
		return ((Function)operand).getName();
	}
	
	protected Object getOperandArgument(Object operand) {
		return ((Function)operand).getArgument();
	}
}