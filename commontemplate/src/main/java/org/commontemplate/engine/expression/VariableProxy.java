package org.commontemplate.engine.expression;

import java.io.IOException;

import org.commontemplate.core.EvaluationException;
import org.commontemplate.core.ExpressionVisitor;
import org.commontemplate.core.Variable;
import org.commontemplate.core.VariableResolver;
import org.commontemplate.util.Location;

/**
 * 变量代理
 *
 * @author liangfei0201@163.com
 *
 */
final class VariableProxy extends Variable {

	private static final long serialVersionUID = -2100088436127690490L;

	private VariableImpl variable;

	VariableProxy(VariableImpl variable) {
		this.variable = variable;
	}

	VariableImpl getTarget() {
		return variable;
	}

	public void accept(ExpressionVisitor visitor) {
		variable.accept(visitor);
	}

	public boolean equals(Object obj) {
		return variable.equals(obj);
	}

	public Object evaluate(VariableResolver context) throws EvaluationException {
		return variable.evaluate(context);
	}

	public Location getLocation() {
		return variable.getLocation();
	}

	public String getName() {
		return variable.getName();
	}

	public String getSource() throws IOException {
		return variable.getSource();
	}

	public String getType() {
		return variable.getType();
	}

	public int hashCode() {
		return variable.hashCode();
	}

	public String toString() {
		return variable.toString();
	}

	public void accept(ExpressionVisitor visitor, boolean isEnter) {
		variable.accept(visitor, isEnter);
	}
}
