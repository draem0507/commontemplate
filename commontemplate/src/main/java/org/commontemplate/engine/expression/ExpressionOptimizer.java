package org.commontemplate.engine.expression;

import java.util.List;

import org.commontemplate.core.Constant;
import org.commontemplate.core.Expression;
import org.commontemplate.core.Operator;
import org.commontemplate.core.UnaryOperator;
import org.commontemplate.util.NumberArithmetic;

/**
 * 四则运算预优化处理器
 *
 * @author YanRong
 *
 */
final class ExpressionOptimizer {

	private final List evaluateInterceptors;

	ExpressionOptimizer(List evaluateInterceptors) {
		this.evaluateInterceptors = evaluateInterceptors;
	}

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
	List optimize(List expressions) {
		if (expressions == null)
			return null;

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
			if (expression.getClass() == ConstantImpl.class) {

				// 取出前一个操作符
				if(i == 0 || expressions.size() < i) {
					continue;
				}
				prevOperator = (Operator) expressions.get(i - 1);

				// 如果前一个操作符是左括号的话，那么不处理
				if (prevOperator == Parenthesis.LEFT_PARENTHESIS) {

					continue;
				}

				// 如果不是2元操作符的情况
				if(prevOperator.getClass() != BinaryOperatorImpl.class) {

					// 判断是不是一元操作符
					if(prevOperator.getClass() == UnaryOperatorImpl.class) {
						UnaryOperatorImpl preUnaryOperator = (UnaryOperatorImpl) prevOperator;
						preUnaryOperator.setOperand(expression);
						expression = new ConstantImpl(preUnaryOperator.evaluate(null), null, evaluateInterceptors);
						// 重新设置 Expressions
						i = resetExpressions(i, 1, expressions, expression);

						continue;
					}

					continue;

				} else {
					// 如果是2元操作符的情况
					// 如果是2元操作符的情况，那么i必须大于等于2
					if(i < 2) {
						continue;
					}
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
														expression.getLocation(), evaluateInterceptors));

										expression = new ConstantImpl(preBinaryOperator.evaluate(null), null, evaluateInterceptors);
										flag = true;
									}
								}
							}
						}
						if(!flag) {
							preBinaryOperator.setOperands(prevExpression, expression);
							expression = new ConstantImpl(preBinaryOperator.evaluate(null), null, evaluateInterceptors);
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
