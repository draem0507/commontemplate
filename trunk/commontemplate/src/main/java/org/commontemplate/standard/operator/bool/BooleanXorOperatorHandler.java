package org.commontemplate.standard.operator.bool;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class BooleanXorOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public BooleanXorOperatorHandler() {
		super(Boolean.class, Boolean.class);
	}
	
	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf(((Boolean)leftOperand).booleanValue() ^ ((Boolean)rightOperand).booleanValue());
	}

}