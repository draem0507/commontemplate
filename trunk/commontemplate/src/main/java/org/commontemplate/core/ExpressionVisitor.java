package org.commontemplate.core;

/**
 * 表达式结构访问者，用于遍历整个表达式树
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class ExpressionVisitor {

	/**
	 * 当访问到变量时被回调
	 *
	 * @see org.commontemplate.core.Variable#accept(ExpressionVisitor)
	 * @see org.commontemplate.core.Expression#accept(ExpressionVisitor)
	 * @param variable 访问到的变量
	 * @throws StopVisitException 当希望停止访问时抛出
	 */
	public void visitVariable(Variable variable) throws StopVisitException {}

	/**
	 * 当访问到常量时被回调
	 *
	 * @see org.commontemplate.core.Constant#accept(ExpressionVisitor)
	 * @see org.commontemplate.core.Expression#accept(ExpressionVisitor)
	 * @param constant 访问到的常量
	 * @throws StopVisitException 当希望停止访问时抛出
	 */
	public void visitConstant(Constant constant) throws StopVisitException {}

	/**
	 * 当访问到二元操作符时被回调
	 *
	 * @see org.commontemplate.core.BinaryOperator#accept(ExpressionVisitor)
	 * @see org.commontemplate.core.Expression#accept(ExpressionVisitor)
	 * @param binaryOperator 访问到的二元操作符
	 * @throws StopVisitException 当希望停止访问时抛出
	 */
	public void visitBinaryOperator(BinaryOperator binaryOperator) throws StopVisitException {}

	/**
	 * 当访问到一元操作符时被回调
	 *
	 * @see org.commontemplate.core.UnaryOperator#accept(ExpressionVisitor)
	 * @see org.commontemplate.core.Expression#accept(ExpressionVisitor)
	 * @param unaryOperator 访问到的一元操作符
	 * @throws StopVisitException 当希望停止访问时抛出
	 */
	public void visitUnaryOperator(UnaryOperator unaryOperator) throws StopVisitException {}

}
