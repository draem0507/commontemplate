package org.commontemplate.core;

/**
 * 表达式	构建器, 为外部引擎或存储方式提供自顶向下的构建方式.
 * (非线程安全)
 *
 * @see org.commontemplate.core.ExpressionParser#createExpressionBuilder()
 * @author liangfei0201@163.com
 *
 */
public interface ExpressionBuilder {

	/**
	 * 获取表达式结果
	 *
	 * @return 表达式
	 */
	public Expression getExpression();

	/**
	 * 添加一元操作符, 将把紧随其后添加表达式(包括操作符,变量,常量)作为参数.
	 *
	 * @param operatorName 操作符名
	 */
	public void addUnaryOperator(String operatorName);

	/**
	 * 添加二元操作符, 将把紧随其后添加的两个表达式(包括操作符,变量,常量)作为参数.
	 *
	 * @param operatorName 操作符名
	 */
	public void addBinaryOperator(String operatorName);

	/**
	 * 添加变量
	 *
	 * @param variableName 变量名
	 */
	public void addVariable(String variableName);

	/**
	 * 添加常量
	 *
	 * @param value 常量值
	 */
	public void addConstant(Object value);

}
