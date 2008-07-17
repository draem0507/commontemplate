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
import org.commontemplate.util.NumberArithmetic;
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
		if (expressions == null || expressions.size() == 0)
			return null;
		expressions = preOptimizeExpression(expressions); // 此优化算法待测试
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
				Expression rightParameter = (Expression) parameterStack.pop(); // 调整参数顺序
				Expression leftParameter = (Expression) parameterStack.pop();
				parameterStack.push(populateBinaryOperator(
						(BinaryOperatorImpl) binaryOperator, leftParameter,
						rightParameter)); // 将组装好的表达式作为参数压入
			} else if (operator instanceof UnaryOperator) {
				UnaryOperator unaryOperator = (UnaryOperator) operator;
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

	// --------- 以下为YanRong对四则运算做的预优化处理 --------

	/**
	 * 预优化表达式，原则是常量的计算进行优化。<br>
	 * a*(1+2+b) 优化成 a*(3+b)<br>
	 * a*(1+2*b) 仍然是 a*(1+2*b)<br>
	 * a*1+2+3 优化成 a*1+5<br>
	 *
	 * @author YanRong
	 * @param expressions
	 *            表达式的列表
	 * @return 优化后的表达式列表
	 */
	private List preOptimizeExpression(List expressions) {

		// 表达式对象
		Expression expression;
		Expression prevExpression;
		Operator currentOperator;
		Operator prevOperator;
		// 这个变量的命名还需要改进
		Operator preOfpreOperator;

		for (int i = 0; i < expressions.size(); i++) {

			expression = (Expression) expressions.get(i);

			// 如果当前的操作符是右括号的话
			if (expression == Parenthesis.RIGHT_PARENTHESIS && i >= 2) {
				// 取出第 i-2 个操作符
				prevExpression = (Expression) expressions.get(i - 2);
				// 如果(第 i-2 个操作符 )是左括号的话
				if (prevExpression == Parenthesis.LEFT_PARENTHESIS) {
					// 检查是否是函数的情况,如果是函数，则不能删除括号
					if(i >= 3) {
						Expression functionExpression = (Expression) expressions.get(i - 3);
						if(functionExpression instanceof UnaryOperator) {
							continue;
						}
					}
					// 可以把括号给删除掉
					prevExpression = (Expression) expressions.get(i - 1);
					expressions.remove(i - 1);
					expressions.remove(i - 2);
					i = i - 2;
					expressions.set(i, prevExpression);
					// 寻找是否可以再和前一个参数进行优化
					if (i - 1 > 0) {
						i--;
					}
				}
			}
			// 如果当前的表达式是常量
			if (expression.getClass() == ConstantImpl.class && i >= 2) {

				// 取出前一个操作符
				prevOperator = (Operator) expressions.get(i - 1);

				// 如果前一个操作符是左括号的话，那么不处理
				if (prevOperator == Parenthesis.LEFT_PARENTHESIS) {

					continue;
				}

				// 如果不是2元操作符，则不处理
				if(prevOperator.getClass() != BinaryOperatorImpl.class) {

					// 判断是不是一元操作符
					if(prevOperator.getClass() == UnaryOperatorImpl.class) {
						UnaryOperatorImpl preUnaryOperator = (UnaryOperatorImpl) prevOperator;
						preUnaryOperator.setOperand(expression);
						expression = new ConstantImpl(preUnaryOperator.evaluate(null), null);
						// 重新设置 Expressions
						i = resetExpressions(i, 1, expressions, expression);

						continue;
					}

					continue;
				}
				BinaryOperatorImpl preBinaryOperator = ((BinaryOperatorImpl) prevOperator);
				// 是否满足结合率
				if(!preBinaryOperator.isAssociativeLaw()) {
					continue;
				}
				// 取当前操作符
				if (i < expressions.size() - 1) {
					currentOperator = (Operator) expressions.get(i + 1);
				} else {
					currentOperator = null;
				}
				// 取当前操作符 -- end

				// 取出前一个参数
				prevExpression = (Expression) expressions.get(i - 2);
				// 前一个参数是常量，进行优化
				if (prevExpression.getClass() == ConstantImpl.class) {

					// 因为目前只优化2元的算数表达式，所以参数必须为数字
					if(!(((Constant) prevExpression).getValue() instanceof Number) ||
							!(((Constant) expression).getValue() instanceof Number)) {
						continue;
					}

					// 只有当级别相同的时候才优化
					if (currentOperator == null
							|| (currentOperator.getPriority() <= preBinaryOperator.getPriority() &&
									!((BinaryOperatorImpl) currentOperator).isRightToLeft())
							|| currentOperator == Parenthesis.RIGHT_PARENTHESIS) {

						// 再往前取一个操作符。例如当前的表达式是 a-1+2
						// 那么 currentOperator = null, preBinaryOperator = +
						// prevExpression = 1。
						// 所以，我们必须再往前寻找一个操作符。也就是例子中的第一个减号。
						//TODO: flag 的使用需要重构
						boolean flag = false;
						if(i>=3) {
							preOfpreOperator = (Operator) expressions.get(i - 3);
							//如果是2元操作符，则处理
							if(preOfpreOperator.getClass() == BinaryOperatorImpl.class) {

								// 如果不满足结合的条件，那么不处理
								if(!isAssociative((BinaryOperatorImpl)preOfpreOperator, preBinaryOperator)) {
									continue;
								}

								// 如果是减号
								if("-".equals(preOfpreOperator.getName())) {
									// 如果级别相同
									if(preBinaryOperator.getPriority() == preOfpreOperator.getPriority()) {

										Number number = (Number)((Constant) expression).getValue();
										preBinaryOperator.setOperands(prevExpression,
												new ConstantImpl(NumberArithmetic.negative(number),
														expression.getLocation()));

										expression = new ConstantImpl(preBinaryOperator.evaluate(null), null);
										flag = true;
									}
								}
							}
						}
						if(!flag) {
							preBinaryOperator.setOperands(prevExpression, expression);
							expression = new ConstantImpl(preBinaryOperator.evaluate(null), null);
						}

						// 重新设置 Expressions
						i = resetExpressions(i, 2, expressions, expression);
					}
				}
			}
		}

		return expressions;
	}

	/**
	 * 重新设置表达式列表。
	 * @param currentIndex
	 * 当前的表达式列表的索引号
	 * @param removeIndexCount
	 * 要删除的表达式的个数
	 * @param expressions
	 * 表达式列表
	 * @param expression
	 * 新增加的表达式
	 * @return
	 * 新的表达式列表的索引号
	 */
	private int resetExpressions(int currentIndex, int removeIndexCount, List expressions, Expression expression) {
		for(int i = 1; i <= removeIndexCount; i++) {
			expressions.remove(currentIndex - i);
		}
		currentIndex -= removeIndexCount;
		expressions.set(currentIndex, expression);
		// 寻找是否可以再和前一个参数进行优化
		if (currentIndex - 1 > 0) {
			currentIndex--;
		}
		return currentIndex;
	}

	/**
	 * 当优化常量的时候，判断是否可以结合。
	 * @author YanRong
	 * @param preOfpreOperator
	 * 常量的前一个的前一个操作符
	 * @param preOperator
	 * 常量的前一个操作符
	 * @return
	 * true: 可以结合
	 * false:　不可以结合
	 */
	private boolean isAssociative(BinaryOperatorImpl preOfpreOperator, BinaryOperatorImpl preOperator) {

		// TODO:这个方法需要改进

		// 前提条件：优先级的判断
		if(preOperator.getPriority()<preOfpreOperator.getPriority()) {
			return false;
		}
		// 如果优先级大，那么就可以结合了
		if(preOperator.getPriority()>preOfpreOperator.getPriority()) {
			return true;
		}
		// 以下要处理的就是优先级相同的情况

		// 加法，减法的情况
		if("+".equals(preOperator.getName()) || "-".equals(preOperator.getName())) {
			return true;
		}

		// 处理乘号
		if("*".equals(preOperator.getName())) {
			if("*".equals(preOfpreOperator.getName())) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

}
