package org.commontemplate.standard.operator;

import org.commontemplate.config.LazyOperand;
import org.commontemplate.core.EvaluationException;
import org.commontemplate.core.Expression;

public class LazyOperandMock implements LazyOperand {

	private final Object obj;

	public LazyOperandMock(Object obj) {
		this.obj = obj;
	}

	public Object evaluate() throws EvaluationException {
		return obj;
	}

	public Expression getExpression() {
		return null;
	}

}
