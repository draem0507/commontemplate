package org.commontemplate.standard.operator.object;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class InstanceofClassOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public InstanceofClassOperatorHandler() {
		super(Object.class, Class.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Class cls = (Class)rightOperand;
		return Boolean.valueOf(leftOperand.getClass().isAssignableFrom(cls));
	}

}