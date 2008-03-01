package org.commontemplate.config;

import org.commontemplate.core.EvaluationException;
import org.commontemplate.core.Expression;

/**
 * 延迟求值封装
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface LazyOperand {

	/**
	 * 获取延迟的表达式
	 * 
	 * @return 延迟的表达式
	 */
	public Expression getExpression();

	/**
	 * 求值
	 * 
	 * @return 求值结果
	 * @throws EvaluationException
	 */
	public Object evaluate() throws EvaluationException;
}
