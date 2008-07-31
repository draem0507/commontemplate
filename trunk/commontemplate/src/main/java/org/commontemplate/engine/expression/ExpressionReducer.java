package org.commontemplate.engine.expression;

import java.util.List;

import org.commontemplate.core.BinaryOperator;
import org.commontemplate.core.Constant;
import org.commontemplate.core.Expression;
import org.commontemplate.core.Operator;
import org.commontemplate.core.UnaryOperator;
import org.commontemplate.core.Variable;
import org.commontemplate.util.Assert;
import org.commontemplate.util.I18nExceptionFactory;
import org.commontemplate.util.LinkedStack;
import org.commontemplate.util.Stack;

/**
 * 表达式归约器
 *
 * @author liangfei0201@163.com
 *
 */
final class ExpressionReducer {

	ExpressionReducer() {

	}

	/**
	 * 将表达式序列归约成表达式树
	 *
	 * @param expressions
	 *            表达式序列
	 * @return 表达式树的根表达式
	 */
	Expression reduce(List expressions) {
		if (expressions == null
				|| expressions.size() == 0)
			return null;
		ReduceStack expressionStack = new ReduceStack();
		for (int i = 0, n = expressions.size(); i < n; i++) {
			Expression expression = (Expression) expressions.get(i);
			// 弹栈
			if (expression == Parenthesis.RIGHT_PARENTHESIS) {
				while (expressionStack.popOperator() != Parenthesis.LEFT_PARENTHESIS); // 一直弹出到最近的一个左括号为止
			} else if (expression instanceof BinaryOperator) {
				while (isHighPriority((BinaryOperator) expression, expressionStack.peekOperator())) {
					expressionStack.popOperator(); // 将优先级高于及等于当前操作符的弹出
				}
			}
			// 压栈
			if (expression != Parenthesis.RIGHT_PARENTHESIS) { // 右括号不入栈
				if (expression instanceof Operator)
					expressionStack.pushOperator((Operator) expression); // 压入操作符
				else
					expressionStack.pushParameter(expression); // 压入参数
			}
		}
		return expressionStack.popResult();
	}

	/**
	 * 待比较的操作符是否比栈顶的操作符的优先级高
	 *
	 * @param operator
	 *            待比较的操作符
	 * @param topOperator
	 *            栈顶的操作符
	 * @return 是否高优先级
	 */
	private boolean isHighPriority(BinaryOperator operator, Operator topOperator) {
		if (topOperator == null || topOperator == Parenthesis.LEFT_PARENTHESIS) // 括号的优先级总是最高
			return false;
		if (topOperator.getPriority() > operator.getPriority()) // 比较优先级
			return true;
		if (topOperator.getPriority() == operator.getPriority()
				&& !((BinaryOperatorImpl) operator).isRightToLeft()) // 是否为从右到左结合
			return true;
		return false;
	}

	// 表达式归约辅助栈
	private static final class ReduceStack {

		// 操作符栈
		private Stack operatorStack = new LinkedStack();

		// 参数栈
		private Stack parameterStack = new LinkedStack();

		/**
		 * 压入操作符
		 *
		 * @param operator
		 *            操作符
		 */
		void pushOperator(Operator operator) {
			operatorStack.push(operator);
		}

		/**
		 * 压入参数
		 *
		 * @param parameter
		 *            参数
		 */
		void pushParameter(Expression parameter) {
			parameterStack.push(parameter);
		}

		/**
		 * 窥取栈顶操作符
		 *
		 * @return 操作符
		 */
		Operator peekOperator() {
			if (operatorStack.isEmpty())
				return null;
			return (Operator) operatorStack.peek();
		}

		/**
		 * 弹出(组装好的)操作符
		 *
		 * @return 操作符
		 */
		Operator popOperator() {
			if (operatorStack.isEmpty())
				throw I18nExceptionFactory.createIllegalStateException("ExpressionReducer.miss.left.parenthesis");

			Operator operator = (Operator) operatorStack.pop();
			// 从参数栈弹出所需参数组装操作表达式
			if (operator instanceof BinaryOperator) {
				BinaryOperator binaryOperator = (BinaryOperator) operator;
				if (parameterStack.isEmpty())
					throw I18nExceptionFactory.createIllegalStateException("ExpressionReducer.operator.miss.parameter");
				Expression rightParameter = (Expression) parameterStack.pop(); // 调整参数顺序
				if (parameterStack.isEmpty())
					throw I18nExceptionFactory.createIllegalStateException("ExpressionReducer.operator.miss.parameter");
				Expression leftParameter = (Expression) parameterStack.pop();
				parameterStack.push(populateBinaryOperator(
						(BinaryOperatorImpl) binaryOperator, leftParameter,
						rightParameter)); // 将组装好的表达式作为参数压入
			} else if (operator instanceof UnaryOperator) {
				UnaryOperator unaryOperator = (UnaryOperator) operator;
				if (parameterStack.isEmpty())
					throw I18nExceptionFactory.createIllegalStateException("ExpressionReducer.operator.miss.parameter");
				Expression parameter = (Expression) parameterStack.pop();
				parameterStack.push(populateUnaryOperator(
						(UnaryOperatorImpl) unaryOperator, parameter)); // 将组装好的表达式作为参数压入
			}
			return operator;
		}

		// 组装二元操作符
		private Expression populateBinaryOperator(
				BinaryOperatorImpl binaryOperator, Expression leftParameter,
				Expression rightParameter) {
			if (leftParameter instanceof Variable
					&& binaryOperator.isLeftOperandNamed())
				leftParameter = new ConstantImpl(((Variable) leftParameter)
						.getName(), leftParameter.getLocation());
			if (rightParameter instanceof Variable
					&& binaryOperator.isRightOperandNamed())
				rightParameter = new ConstantImpl(((Variable) rightParameter)
						.getName(), rightParameter.getLocation());
			binaryOperator.setOperands(leftParameter, rightParameter);
			try {
				// 常量优化算法，将常量先计算，如：
				// 2 * 3 + coins 将被优化为 6 + coins
				// (注: 表达式应尽量把常量计算写在前面, 如: 24 * 60 * days, 而不要用 days * 24 * 60)
				if (leftParameter instanceof Constant
						&& rightParameter instanceof Constant)
					return new ConstantImpl(binaryOperator.evaluate(null),
							binaryOperator.getLocation());
			} catch (Exception e) {
				// 抛出任何异常都表示其不支持优化
			}
			return binaryOperator;
		}

		// 组装一元操作符
		private Expression populateUnaryOperator(
				UnaryOperatorImpl unaryOperator, Expression parameter) {
			if (parameter instanceof Variable && unaryOperator.isOperandNamed())
				parameter = new ConstantImpl(((Variable) parameter).getName(),
						parameter.getLocation());
			unaryOperator.setOperand(parameter);
			try {
				// 常量优化算法，将常量先计算，如：
				// ! true 将被优化为 false
				if (parameter instanceof Constant)
					return new ConstantImpl(unaryOperator.evaluate(null),
							unaryOperator.getLocation());
			} catch (Exception e) {
				// 抛出任何异常都表示其不支持优化
			}
			return unaryOperator;
		}

		/**
		 * 弹出(最后的)结果
		 *
		 * @return 结果
		 */
		Expression popResult() {
			while (!operatorStack.isEmpty()) {
				Operator operator = popOperator(); // 弹出栈中所有操作符
				if (operator == Parenthesis.LEFT_PARENTHESIS)
					throw I18nExceptionFactory.createIllegalStateException("ExpressionReducer.miss.right.parenthesis");
			}
			Expression result = (Expression) parameterStack.pop();
			Assert.assertTrue(parameterStack.isEmpty(), "ExpressionReducer.operator.miss.parameter"); // 后验条件
			return result;
		}

	}

}
