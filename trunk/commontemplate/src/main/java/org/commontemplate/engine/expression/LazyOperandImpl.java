package org.commontemplate.engine.expression;

import org.commontemplate.config.LazyOperand;
import org.commontemplate.core.VariableResolver;
import org.commontemplate.core.Expression;
import org.commontemplate.core.EvaluationException;

/**
 * 延迟求值封装类
 * 
 * @author liangfei0201@163.com
 *
 */
final class LazyOperandImpl implements LazyOperand {
	
	private final Expression operand;
	
	private final VariableResolver context;

	LazyOperandImpl(Expression operand, VariableResolver context) {
		this.operand = operand;
		this.context = context;
	}

	public Expression getExpression() {
		return operand;
	}

	public final Object evaluate() throws EvaluationException {
		return operand.evaluate(context);
	}

}
