package org.commontemplate.core;

/**
 * 表达式组件工厂, 为外部引擎或存储方式提供表达式组装元素, 适合于自底向上的构建方式.
 *
 * @author liangfei0201@163.com
 *
 */
public interface ExpressionFactory {

	/**
	 * 创建常量
	 *
	 * @param constantValue 常量值
	 * @return 常量表达式
	 */
	public Constant createConstant(Object constantValue);

	/**
	 * 创建变量
	 *
	 * @param variableName 变量名
	 * @return 变量表达式
	 */
	public Variable createVariable(String variableName);

	/**
	 * 创建一元操作符
	 *
	 * @param operatorName 操作符名
	 * @param operand 操作数
	 * @return 一元操作符表达式
	 */
	public UnaryOperator createUnaryOperator(String operatorName, Expression operand);

	/**
	 * 创建二元操作符
	 *
	 * @param operatorName 操作符名
	 * @param leftOperand 左操作数
	 * @param rightOperand 右操作数
	 * @return 二元操作符表达式
	 */
	public BinaryOperator createBinaryOperator(String operatorName, Expression leftOperand, Expression rightOperand);

}
