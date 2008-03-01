package org.commontemplate.core;

/**
 * 常量参数.
 * 
 * @author liangfei0201@163.com
 * 
 */
public abstract class Constant extends Parameter {

	/**
	 * 获取常量值
	 * 
	 * @return 常量值
	 */
	public abstract Object getValue();

	// final: 输出固定值, 保证语义正确
	public final Object evaluate(VariableResolver context) throws EvaluationException {
		return getValue();
	}

}