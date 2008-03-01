package org.commontemplate.engine.expression;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.core.BinaryOperator;
import org.commontemplate.core.Expression;
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
	
	private final boolean functionAvailable;
	
	ExpressionTranslator(ExpressionFactory expressionResolver, boolean functionAvailable) {
		this.expressionFactory = expressionResolver;
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
		if (tokens == null || tokens.size() == 0) 
			return null;
		
		List expressions = new ArrayList(tokens.size());
		for (int i = 0, n = tokens.size(); i < n; i ++) {
			Token token = (Token)tokens.get(i);

			// 在连续的两个括号之间加null，即：() 变成 (null)
			if (i > 1 && ("(".equals(((Token)tokens.get(i - 1)).getMessage().trim()) || "[".equals(((Token)tokens.get(i - 1)).getMessage().trim()))
					&& (")".equals(token.getMessage().trim()) || "]".equals(token.getMessage().trim()))) {
				appendExpression(expressions, new Token("null", 0, Position.ZERO), false);
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
			if ("[".equals(token.getMessage().trim()))
				appendExpression(expressions, new Token("(", token.getOffset()), false);
		}
		return expressions;
	}
	
	// 添加函数到表达式序列中
	private void appendFunction(final List expressions, final Token token) throws ParsingException {
		if (! functionAvailable) 
			throw new ParsingException(token.getLocation(), "禁止调用函数! 函数名:" + token.getMessage() + " (配置项functionAvailable=false)");
		expressions.add(expressionFactory.getFunctionExpression(token));
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
				expressions.add(expressionFactory.getUnaryExpression(token, isFunction));
			}
		} else { // 否则当前表达式一定为二元操作符
			if (TypeUtils.isMultiOperator(token.getMessage())) { // 处理未分割二元操作符
				int offset = maxBinaryOperator(token, token.getMessage());
				expressions.add(expressionFactory.getBinaryExpression(token.subToken(0, offset)));
				// 二元操作符后的所有操作符均为一元操作符
				appendUnaryOperator(expressions, token, offset);
			} else {
				expressions.add(expressionFactory.getBinaryExpression(token));
			}
		}
	}
	
	// 递归最大匹配(并添加)一元操作符
	private void appendUnaryOperator(final List expressions, final Token token, int offset) throws ParsingException {
		String name = token.getMessage().substring(offset);
		if (name.trim().length() > 0) {
			int nextOffset = offset + maxUnaryOperator(token, name);
			expressions.add(expressionFactory.getUnaryExpression(token.subToken(offset, nextOffset)));
			appendUnaryOperator(expressions, token, nextOffset);
		}
	}
	
	// 最大匹配一元操作符
	private int maxUnaryOperator(final Token token, final String name) throws ParsingException {
		for (int i = name.length(); i > 0; i --) 
			if (expressionFactory.hasUnaryExpression(name.substring(0, i))) 
				return i;
		throw new ParsingException(token.getLocation(), name + " 为非法的一元运算符！");
	}
	
	// 最大匹配二元操作符
	private int maxBinaryOperator(final Token token, final String name) throws ParsingException {
		for (int i = name.length(); i > 0; i --) 
			if (expressionFactory.hasBinaryExpression(name.substring(0, i))) 
				return i;
		throw new ParsingException(token.getLocation(), name + " 为非法的二元运算符！");
	}

}
