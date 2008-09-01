package org.commontemplate.engine.expression;

import java.io.IOException;
import java.util.List;

import org.commontemplate.core.EvaluationException;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ExpressionVisitor;
import org.commontemplate.core.UnaryOperator;
import org.commontemplate.core.VariableResolver;
import org.commontemplate.util.Location;

/**
 * 一元操作符代理
 *
 * @author liangfei0201@163.com
 *
 */
final class UnaryOperatorProxy extends UnaryOperator {

	private static final long serialVersionUID = 3838707963620031918L;

	private UnaryOperatorImpl unaryOperator;

	UnaryOperatorProxy(UnaryOperatorImpl unaryOperator) {
		this.unaryOperator = unaryOperator;
	}

	UnaryOperatorImpl getTarget() {
		return unaryOperator;
	}

	public void accept(ExpressionVisitor visitor) {
		unaryOperator.accept(visitor);
	}

	public boolean equals(Object obj) {
		return unaryOperator.equals(obj);
	}

	public Object evaluate(VariableResolver context) throws EvaluationException {
		throw new java.lang.UnsupportedOperationException();
	}

	public Location getLocation() {
		return unaryOperator.getLocation();
	}

	public String getName() {
		return unaryOperator.getName();
	}

	public Expression getOperand() {
		return unaryOperator.getOperand();
	}

	public List getOperands() {
		return unaryOperator.getOperands();
	}

	public int getPriority() {
		return unaryOperator.getPriority();
	}

	public String getSource() throws IOException {
		return unaryOperator.getSource();
	}

	public String getType() {
		return unaryOperator.getType();
	}

	public int hashCode() {
		return unaryOperator.hashCode();
	}

	public String toString() {
		return unaryOperator.toString();
	}

}
