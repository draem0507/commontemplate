package org.commontemplate.core;

/**
 * 表达式结构访问者，用于遍历整个表达式树
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class ExpressionVisitor {

	public void visitVariable(Variable variable) {}

	public void visitConstant(Constant constant) {}

	public void visitBinaryOperator(BinaryOperator binaryOperator) {}

	public void visitUnaryOperator(UnaryOperator unaryOperator) {}

}
