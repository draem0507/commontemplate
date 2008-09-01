package org.commontemplate.engine.expression;

import java.io.IOException;

import org.commontemplate.core.Constant;
import org.commontemplate.core.EvaluationException;
import org.commontemplate.core.ExpressionVisitor;
import org.commontemplate.core.VariableResolver;
import org.commontemplate.util.Location;

/**
 * 常量代理
 *
 * @author liangfei0201@163.com
 *
 */
final class ConstantProxy extends Constant {

	private static final long serialVersionUID = 1298785276282117395L;

	private ConstantImpl constant;

	ConstantProxy(ConstantImpl constant) {
		this.constant = constant;
	}

	ConstantImpl getTarget() {
		return constant;
	}

	public void accept(ExpressionVisitor visitor) {
		constant.accept(visitor);
	}

	public boolean equals(Object obj) {
		return constant.equals(obj);
	}

	public Object evaluate(VariableResolver context) throws EvaluationException {
		throw new java.lang.UnsupportedOperationException();
	}

	public Location getLocation() {
		return constant.getLocation();
	}

	public String getName() {
		return constant.getName();
	}

	public String getSource() throws IOException {
		return constant.getSource();
	}

	public String getType() {
		return constant.getType();
	}

	public Object getValue() {
		return constant.getValue();
	}

	public int hashCode() {
		return constant.hashCode();
	}

	public String toString() {
		return constant.toString();
	}

}
