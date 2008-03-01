package org.commontemplate.standard.operator.sequence;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class IntegerSequenceOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public IntegerSequenceOperatorHandler() {
		super(Number.class, Number.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return new IntegerSequence(((Number)leftOperand).intValue(), ((Number)rightOperand).intValue());
	}

}