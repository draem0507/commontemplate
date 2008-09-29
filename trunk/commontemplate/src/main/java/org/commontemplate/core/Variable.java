package org.commontemplate.core;

/**
 * 变量参数.
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class Variable extends Parameter {

	// 语义的默认实现
	public Object evaluate(VariableResolver context) throws EvaluationException {
		try {
			return context.getVariable(getName());
		} catch (UndefinedException e) {
			return null;
		}
	}

}
