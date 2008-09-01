package org.commontemplate.engine.expression;

import java.util.List;

import org.commontemplate.core.EvaluationException;
import org.commontemplate.core.ExpressionVisitor;
import org.commontemplate.core.Variable;
import org.commontemplate.core.VariableResolver;
import org.commontemplate.util.Location;

/**
 * 变量实现类
 *
 * @author liangfei0201@163.com
 *
 */
final class VariableImpl extends Variable {

	private static final long serialVersionUID = 1L;

	private final String name;

	private final Location location;

	private final List evaluateInterceptors;

	private final Variable proxy;

	VariableImpl(String name, Location location, List evaluateInterceptors) {
		this.name = name;
		this.location = location;
		this.evaluateInterceptors = evaluateInterceptors;
		this.proxy = new VariableProxy(this);
	}

	public Object evaluate(VariableResolver context) throws EvaluationException {
		if (evaluateInterceptors != null && evaluateInterceptors.size() > 0)
			return new EvaluationImpl(proxy, context, evaluateInterceptors).doEvaluate();
		else
			return doEvaluate(context);
	}

	Object doEvaluate(VariableResolver context) throws EvaluationException {
		return context.getVariable(getName());
	}

	public String getName() {
		return name;
	}

	public String getSource() {
		return name;
	}

	public Location getLocation() {
		return location;
	}

	public void accept(ExpressionVisitor visitor) {
		visitor.visitVariable(this);
	}

}
