package org.commontemplate.core;

/**
 * 常量参数.
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class Constant extends Parameter {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 获取常量值
	 *
	 * @return 常量值
	 */
	public abstract Object getValue();

	// 语义的默认实现
	public Object evaluate(VariableResolver context) throws EvaluationException {
		return getValue();
	}

}