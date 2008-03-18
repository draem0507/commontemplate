package org.commontemplate.standard.operator;

import org.commontemplate.config.LazyOperand;
import org.commontemplate.core.EvaluationException;
import org.commontemplate.core.Expression;

/**
 * LazyOperand的Mock实现
 * 
 * @author liangfei0201@163.com
 *
 */
public class LazyOperandMock implements LazyOperand {

	private final Object obj;
	
	private final Expression expression;

	public LazyOperandMock(Object obj) {
		this.obj = obj;
		this.expression = null;
	}
	
	public LazyOperandMock(Expression expression) {
		this.obj = null;
		this.expression = expression;
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
