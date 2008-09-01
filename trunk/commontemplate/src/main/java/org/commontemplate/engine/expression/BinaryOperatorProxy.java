package org.commontemplate.engine.expression;

import java.io.IOException;
import java.util.List;

import org.commontemplate.core.BinaryOperator;
import org.commontemplate.core.EvaluationException;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ExpressionVisitor;
import org.commontemplate.core.VariableResolver;
import org.commontemplate.util.Location;

/**
 * 二元操作符代理
 *
 * @author liangfei0201@163.com
 *
 */
final class BinaryOperatorProxy extends BinaryOperator {

	private static final long serialVersionUID = 3415472324167642029L;

	private BinaryOperatorImpl binaryOperator;

	BinaryOperatorProxy(BinaryOperatorImpl binaryOperator) {
		this.binaryOperator = binaryOperator;
	}

	BinaryOperatorImpl getTarget() {
		return binaryOperator;
	}

	public void accept(ExpressionVisitor visitor) {
		binaryOperator.accept(visitor);
	}

	public boolean equals(Object obj) {
		return binaryOperator.equals(obj);
	}

	public Object evaluate(VariableResolver context) throws EvaluationException {
		throw new java.lang.UnsupportedOperationException();
	}

	public Expression getLeftOperand() {
		return binaryOperator.getLeftOperand();
	}

	public Location getLocation() {
		return binaryOperator.getLocation();
	}

	public String getName() {
		return binaryOperator.getName();
	}

	public List getOperands() {
		return binaryOperator.getOperands();
	}

	public int getPriority() {
		return binaryOperator.getPriority();
	}

	public Expression getRightOperand() {
		return binaryOperator.getRightOperand();
	}

	public String getSource() throws IOException {
		return binaryOperator.getSource();
	}

	public String getType() {
		return binaryOperator.getType();
	}

	public int hashCode() {
		return binaryOperator.hashCode();
	}

	public String toString() {
		return binaryOperator.toString();
	}

}
