package org.commontemplate.engine.expression;

import java.util.List;

import org.commontemplate.core.Constant;
import org.commontemplate.core.EvaluationException;
import org.commontemplate.core.ExpressionVisitor;
import org.commontemplate.core.VariableResolver;
import org.commontemplate.util.Location;

/**
 * 常量实现类
 *
 * @author liangfei0201@163.com
 *
 */
final class ConstantImpl extends Constant {

	private static final long serialVersionUID = 1L;

	private final Object value;

	private final Location location;

	private final List evaluateInterceptors;

	private final Constant proxy;

	ConstantImpl(Object value, Location location, List evaluateInterceptors) {
		this.value = value;
		this.location = location;
		this.evaluateInterceptors = evaluateInterceptors;
		this.proxy = new ConstantProxy(this);
	}

	public Object evaluate(VariableResolver context) throws EvaluationException {
		if (evaluateInterceptors != null && evaluateInterceptors.size() > 0)
			return new EvaluationImpl(proxy, context, evaluateInterceptors).doEvaluate();
		else
			return doEvaluate(context);
	}

	Object doEvaluate(VariableResolver context) throws EvaluationException {
		return getValue();
	}

	public String getName() {
		return String.valueOf(value);
	}

	public String getSource() {
		if (value instanceof String)
			return "\"" + value + "\"";
		return String.valueOf(value);
	}

	public Location getLocation() {
		return location;
	}

	public Object getValue() {
		return value;
	}

	public void accept(ExpressionVisitor visitor) {
		visitor.visitConstant(this);
	}

}
