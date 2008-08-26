package org.commontemplate.engine.expression;


import java.util.List;

import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ExpressionBuilder;
import org.commontemplate.core.Operator;
import org.commontemplate.util.Assert;
import org.commontemplate.util.LinkedStack;
import org.commontemplate.util.Stack;

/**
 * 表达式构建器实现
 *
 * @author liangfei0201@163.com
 *
 */
final class ExpressionBuilderImpl implements ExpressionBuilder {

	// 操作符处理类供给器
	private final OperatorHandlerProvider operatorHandlerProvider;

	private final List evaluateInterceptors;

	public ExpressionBuilderImpl(OperatorHandlerProvider operatorHandlerProvider, List evaluateInterceptors) {
		this.operatorHandlerProvider = operatorHandlerProvider;
		this.evaluateInterceptors = evaluateInterceptors;
	}

	// 操作符栈
	private Stack operatorStack = new LinkedStack();

	// 参数栈
	private Stack parameterStack = new LinkedStack();

	public Expression getExpression() {
		while (! operatorStack.isEmpty()) { // 弹栈直到空
			Operator operator = (Operator)operatorStack.pop(); // 弹出栈顶操作符
			if (operator instanceof BinaryOperatorImpl) { // 二元操作符
				BinaryOperatorImpl binaryOperator = (BinaryOperatorImpl)operator; // 转型
				Expression rightOperand = (Expression)parameterStack.pop(); // 弹出右参
				Expression leftOperand = (Expression)parameterStack.pop(); // 弹出左参
				binaryOperator.setOperands(leftOperand, rightOperand); // 设置操作符参数
			} else { // 一元操作符
				UnaryOperatorImpl unaryOperator = (UnaryOperatorImpl)operator; // 转型
				Expression operand = (Expression)parameterStack.pop(); // 弹出参数
				unaryOperator.setOperand(operand); // 设置操作符参数
			}
			parameterStack.push(operator); // 将组装好的操作符压入参数栈
		}
		Expression result = (Expression)parameterStack.pop();
		Assert.assertTrue(parameterStack.isEmpty(), "ExpressionBuilderImpl.operator.miss.parameter");
		return result;
	}

	public void addBinaryOperator(String operatorName) {
		operatorStack.push(new BinaryOperatorImpl(operatorName, null,
				operatorHandlerProvider.getBinaryOperatorPriority(operatorName),
				operatorHandlerProvider.getBinaryOperatorHandler(operatorName), evaluateInterceptors));
	}

	public void addUnaryOperator(String operatorName) {
		operatorStack.push(new UnaryOperatorImpl(operatorName, null,
				operatorHandlerProvider.getUnaryOperatorPriority(operatorName),
				operatorHandlerProvider.getUnaryOperatorHandler(operatorName), evaluateInterceptors));
	}

	public void addConstant(Object value) {
		parameterStack.push(new ConstantImpl(value, null, evaluateInterceptors));
	}

	public void addVariable(String variableName) {
		parameterStack.push(new VariableImpl(variableName, null, evaluateInterceptors));
	}

}
