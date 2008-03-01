package org.commontemplate.core;

/**
 * 变量参数.
 * 
 * @author liangfei0201@163.com
 *
 */
public abstract class Variable extends Parameter {

	// final: 只允许作为变量查询, 保证语义正确
	public final Object evaluate(VariableResolver context) throws EvaluationException {
		return context.lookupVariable(getName());
	}

}
