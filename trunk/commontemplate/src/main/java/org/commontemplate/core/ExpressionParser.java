package org.commontemplate.core;

/**
 * 表达式解析器
 *
 * @author liangfei0201@163.com
 *
 */
public interface ExpressionParser extends ExpressionFactory {

	/**
	 * 将表达式串解析成表达式树
	 *
	 * @param expression
	 *            表达式串
	 * @return 表达式树的根表达式
	 * @throws ParsingException 表达式格式错误时抛出
	 */
	public Expression parseExpression(String expression) throws ParsingException;

	/**
	 * 创建表达式构建器
	 *
	 * @return 表达式构建器
	 */
	public ExpressionBuilder createExpressionBuilder();

}
