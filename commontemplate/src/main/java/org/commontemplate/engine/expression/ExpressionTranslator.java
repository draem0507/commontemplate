package org.commontemplate.engine.expression;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.core.BinaryOperator;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ExpressionFactory;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.UnaryOperator;
import org.commontemplate.util.Position;
import org.commontemplate.util.TypeUtils;
import org.commontemplate.util.scanner.Token;

/**
 * 表达式转译器
 *
 * @author liangfei0201@163.com
 */
final class ExpressionTranslator {

	private final ExpressionFactory expressionFactory;

	private final ExpressionProvider expressionProvider;

	private final boolean functionAvailable;

	ExpressionTranslator(ExpressionFactory expressionFactory, ExpressionProvider expressionProvider, boolean functionAvailable) {
		this.expressionFactory = expressionFactory;
		this.expressionProvider = expressionProvider;
		this.functionAvailable = functionAvailable;
	}

	/**
	 * 将表达式片断转译成相应表达式序列
	 *
	 * @param tokens 表达式片断, 类型: List&lt;Token&gt;
	 * @return 表达式序列, 类型: List&lt;Expression&gt;
	 * @throws ParsingException 解析出错时抛出
	 */
	List translate(final List tokens) throws ParsingException {
		if (tokens == null
				|| tokens.size() == 0)
			return null;
		List expressions = new ArrayList(tokens.size());
		boolean dotNamed = false;
		StringBuffer dotNamedBuffer = new StringBuffer();
		for (int i = 0, n = tokens.size(); i < n; i ++) {
			Token token = (Token)tokens.get(i);

			// 在连续的两个括号之间加null，即：() 变成 (null)
			if (i > 0) {
				String pre = ((Token)tokens.get(i - 1)).getMessage().trim();
				String cur = token.getMessage().trim();
				if (("(".equals(pre) && (")".equals(cur))
							|| ("[".equals(pre) && "]".equals(cur)))) {
					expressions.add(expressionFactory.createConstant(null)); // TODO 丢失Location信息
				}
			}

			// 处理带点号名称
			if (dotNamed) {
				String cur = token.getMessage().trim();
				if (".".equals(cur) || TypeUtils.isNamed(cur)) {
					dotNamedBuffer.append(cur);
					continue;
				} else {
					if ("(".equals(cur)) {
						appendFunction(expressions, new Token(dotNamedBuffer.toString(), Position.ZERO)); // TODO 丢失Location信息
					} else {
						expressions.add(expressionFactory.createConstant(dotNamedBuffer.toString())); // TODO 丢失Location信息
					}
					dotNamedBuffer.setLength(0);
					dotNamed = false;
				}
			}

			// 处理函数
			boolean isFunction = TypeUtils.isNamed(token.getMessage().trim())
						&& i + 1 < n && "(".equals(((Token)tokens.get(i + 1)).getMessage().trim());
			if (isFunction && expressions.size() > 0) {
				Expression prev = (Expression)expressions.get(expressions.size() - 1);
				if (prev instanceof BinaryOperatorImpl) {
					BinaryOperatorImpl prevOperator = (BinaryOperatorImpl)prev;
					if (prevOperator.isRightOperandFunctioned()) {
						appendFunction(expressions, token);
						continue;
					}
				} else if (prev instanceof UnaryOperatorImpl) {
					UnaryOperatorImpl prevOperator = (UnaryOperatorImpl)prev;
					if (prevOperator.isOperandFunctioned()) {
						appendFunction(expressions, token);
						continue;
					}
				}
			}

			// 正常添加
			appendExpression(expressions, token, isFunction);
			if (expressions.size() > 0) {
				Expression prev = (Expression)expressions.get(expressions.size() - 1);
				if (prev instanceof BinaryOperatorImpl) {
					BinaryOperatorImpl prevOperator = (BinaryOperatorImpl)prev;
					if (prevOperator.isRightOperandDotNamed()) {
						dotNamed = true;
					}
				} else if (prev instanceof UnaryOperatorImpl) {
					UnaryOperatorImpl prevOperator = (UnaryOperatorImpl)prev;
					if (prevOperator.isOperandDotNamed()) {
						dotNamed = true;
					}
				}
			}
			if ("[".equals(token.getMessage().trim()))
				appendExpression(expressions, new Token("(", token.getBeginPosition()), false);
		}

		if (dotNamed) { // 处理最后的点号名称
			expressions.add(expressionFactory.createConstant(dotNamedBuffer.toString())); // TODO 丢失Location信息
		}

		return expressions;
	}

	// 添加函数到表达式序列中
	private void appendFunction(final List expressions, final Token token) throws ParsingException {
		if (! functionAvailable)
			throw new ParsingException(token.getLocation(), "ExpressionTranslator.function.forbidden", new Object[]{token.getMessage()});
		expressions.add(expressionProvider.getFunctionExpression(token));
	}

	// 将name所对应的表达式添加到表达式列表中
	private void appendExpression(final List expressions, final Token token, final boolean isFunction) throws ParsingException {
		Expression beforeExpression = null;
		if (expressions.size() > 0)
			beforeExpression = (Expression)expressions.get(expressions.size() - 1);
		if (beforeExpression == null
				|| beforeExpression == Parenthesis.LEFT_PARENTHESIS
				|| beforeExpression instanceof BinaryOperator
				|| beforeExpression instanceof UnaryOperator) { // 则当前表达式一定为一元操作符或数据
			if (TypeUtils.isMultiOperator(token.getMessage())) { // 处理未分割一元操作符
				appendUnaryOperator(expressions, token, 0);
			} else {
				expressions.add(expressionProvider.getUnaryExpression(token, isFunction));
			}
		} else { // 否则当前表达式一定为二元操作符
			if (TypeUtils.isMultiOperator(token.getMessage())) { // 处理未分割二元操作符
				int offset = maxBinaryOperator(token, token.getMessage());
				expressions.add(expressionProvider.getBinaryExpression(token.subToken(0, offset)));
				// 二元操作符后的所有操作符均为一元操作符
				appendUnaryOperator(expressions, token, offset);
			} else {
				expressions.add(expressionProvider.getBinaryExpression(token));
			}
		}
	}

	// 递归最大匹配(并添加)一元操作符
	private void appendUnaryOperator(final List expressions, final Token token, int offset) throws ParsingException {
		String name = token.getMessage().substring(offset);
		if (name.trim().length() > 0) {
			int nextOffset = offset + maxUnaryOperator(token, name);
			expressions.add(expressionProvider.getUnaryExpression(token.subToken(offset, nextOffset)));
			appendUnaryOperator(expressions, token, nextOffset);
		}
	}

	// 最大匹配一元操作符
	private int maxUnaryOperator(final Token token, final String name) throws ParsingException {
		for (int i = name.length(); i > 0; i --)
			if (expressionProvider.hasUnaryExpression(name.substring(0, i)))
				return i;
		throw new ParsingException(token.getLocation(), "ExpressionTranslator.invaild.unary.operator.name", new Object[]{name});
	}

	// 最大匹配二元操作符
	private int maxBinaryOperator(final Token token, final String name) throws ParsingException {
		for (int i = name.length(); i > 0; i --)
			if (expressionProvider.hasBinaryExpression(name.substring(0, i)))
				return i;
		throw new ParsingException(token.getLocation(), "ExpressionTranslator.invaild.binary.operator.name", new Object[]{name});
	}

}
