package org.commontemplate.config;

import org.commontemplate.core.Expression;
import org.commontemplate.core.VariableResolver;

/**
 * 表达式求值过程封装
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class Evaluation {

	/**
	 * 获取待求值的表达式
	 *
	 * @return 待待求值的表达式，如果拦截到的是模板渲染过程，则返回null
	 */
	public abstract Expression getExpression();

	/**
	 * 获取待求值变量决策器
	 *
	 * @return 待求值变量决策器
	 */
	public abstract VariableResolver getVariableResolver();

	/**
	 * 是否已求值过
	 *
	 * @return 是否已求值过
	 */
	public abstract boolean isEvaluated();

	/**
	 * 执行求值过程或调用下一拦截器
	 */
	public abstract Object doEvaluate();

}
