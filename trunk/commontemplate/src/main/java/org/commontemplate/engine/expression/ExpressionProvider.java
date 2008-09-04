package org.commontemplate.engine.expression;

import java.util.List;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.config.Keywords;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ParsingException;
import org.commontemplate.util.Infinitude;
import org.commontemplate.util.NumberUtils;
import org.commontemplate.util.StringConvertUtils;
import org.commontemplate.util.TypeUtils;
import org.commontemplate.util.scanner.Token;

/**
 * 表达式工厂
 *
 * @author liangfei0201@163.com
 *
 */
final class ExpressionProvider {

	private final OperatorHandlerProvider operatorHandlerProvider;

	private final Keywords keywords;

	private final List evaluateInterceptors;

	ExpressionProvider(OperatorHandlerProvider operatorHandlerProvider, List evaluateInterceptors, Keywords keywords) {
		this.operatorHandlerProvider = operatorHandlerProvider;
		this.evaluateInterceptors = evaluateInterceptors;
		this.keywords = keywords;
	}

	boolean hasUnaryExpression(String name) {
		if (Infinitude.SYMBOL.equals(name))
			return true;
		return operatorHandlerProvider.getUnaryOperatorHandler(name.trim()) != null;
	}

	boolean hasBinaryExpression(String name) {
		return operatorHandlerProvider.getBinaryOperatorHandler(name.trim()) != null;
	}

	Expression getFunctionExpression(Token token) throws ParsingException {
		String name = token.getMessage().trim();
		return new UnaryOperatorImpl(name, token.getLocation(), Integer.MAX_VALUE, new FunctionUnaryOperatorHandler(name), evaluateInterceptors);
	}

	Expression getUnaryExpression(Token token) throws ParsingException {
		return getUnaryExpression(token, false);
	}

	Expression getUnaryExpression(Token token, boolean isFunction) throws ParsingException {
		String name = token.getMessage().trim();
		// 字符串
		if (TypeUtils.isEscapeString(name))
			return new ConstantImpl(StringConvertUtils.convertLiteral(name.substring(1, name.length() - 1)), token.getLocation(), evaluateInterceptors);

		// 非转义字符串
		if (TypeUtils.isNoEscapeString(name))
			return new ConstantImpl(name.substring(1, name.length() - 1), token.getLocation(), evaluateInterceptors);

		// 数字
		if (TypeUtils.isNumber(name))
			return new ConstantImpl(NumberUtils.parseNumber(name), token.getLocation(), evaluateInterceptors);

		// 关键字
		if (keywords.getNullKeyword().equals(name)) return new ConstantImpl(null, token.getLocation(), evaluateInterceptors);
		if (keywords.getTrueKeyword().equals(name)) return new ConstantImpl(Boolean.TRUE, token.getLocation(), evaluateInterceptors);
		if (keywords.getFalseKeyword().equals(name)) return new ConstantImpl(Boolean.FALSE, token.getLocation(), evaluateInterceptors);
		if (Infinitude.SYMBOL.equals(name)) return new ConstantImpl(Infinitude.POSITIVE, token.getLocation(), evaluateInterceptors);

		// 括号
		if ("(".equals(name)) return Parenthesis.LEFT_PARENTHESIS;

		UnaryOperatorHandler unaryOperatorHandler = operatorHandlerProvider.getUnaryOperatorHandler(name);

		// 名称
		if (TypeUtils.isNamed(name) && ! isFunction) {
			if (unaryOperatorHandler == null || ! unaryOperatorHandler.isKeyword())
				return new VariableImpl(name, token.getLocation(), evaluateInterceptors);
		}

		// 一元操作符
		if (unaryOperatorHandler != null)
			return new UnaryOperatorImpl(name, token.getLocation(),
					operatorHandlerProvider.getUnaryOperatorPriority(name),
					unaryOperatorHandler, evaluateInterceptors);

		throw new ParsingException(token.getLocation(), "ExpressionFactory.invaild.unary.operator.name", new Object[]{name});
	}

	Expression getBinaryExpression(Token token) throws ParsingException {
		String name = token.getMessage().trim();
		// 括号
		if (")".equals(name) || "]".equals(name))
			return Parenthesis.RIGHT_PARENTHESIS;

		// 二元操作符
		BinaryOperatorHandler binaryOperatorHandler = operatorHandlerProvider.getBinaryOperatorHandler(name);
		if (binaryOperatorHandler != null)
			return new BinaryOperatorImpl(name, token.getLocation(),
					operatorHandlerProvider.getBinaryOperatorPriority(name),
					binaryOperatorHandler, evaluateInterceptors);

		throw new ParsingException(token.getLocation(), "ExpressionFactory.invaild.binary.operator.name", new Object[]{name});
	}

}
