package org.commontemplate.core;

/**
 * 变量参数.
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class Variable extends Parameter {

	public static final String TYPE = "Variable";

	public String getType() {
		return TYPE;
	}

	// 语义的默认实现
	public Object evaluate(VariableResolver context) throws EvaluationException {
		return context.getVariable(getName());
	}

}
