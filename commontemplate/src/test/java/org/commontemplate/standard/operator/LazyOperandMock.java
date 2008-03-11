package org.commontemplate.standard.operator;

import org.commontemplate.config.LazyOperand;
import org.commontemplate.core.EvaluationException;
import org.commontemplate.core.Expression;

public class LazyOperandMock implements LazyOperand {

	private final Object obj;
	private Expression expression;

	public LazyOperandMock(Object obj) {
		this.obj = obj;
	}
	
	public LazyOperandMock(Object obj, Expression expression) {
		this.obj = obj;
		this.expression = expression;
	}

	public Object evaluate() throws EvaluationException {
		return obj;
	}

	public Expression getExpression() {
		return expression;
	}
}
