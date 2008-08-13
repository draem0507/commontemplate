package org.commontemplate.engine.expression;

import org.commontemplate.config.Keywords;
import org.commontemplate.config.OperatorHandlerProvider;
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

	private final boolean functionAvailable;

	ExpressionProvider(OperatorHandlerProvider operatorHandlerProvider, Keywords keywords, boolean functionAvailable) {
		this.operatorHandlerProvider = operatorHandlerProvider;
		this.keywords = keywords;
		this.functionAvailable = functionAvailable;
	}

	boolean hasUnaryExpression(String name) {
		if (Infinitude.SYMBOL.equals(name))
			return true;
		return operatorHandlerProvider.hasUnaryOperatorHandler(name.trim());
	}

	boolean hasBinaryExpression(String name) {
		return operatorHandlerProvider.hasBinaryOperatorHandler(name.trim());
	}

	Expression getUnaryExpression(Token token) throws ParsingException {
		return getUnaryExpression(token, false);
	}

	Expression getFunctionExpression(Token token) throws ParsingException {
		String name = token.getMessage().trim();
		return new UnaryOperatorImpl(name, token.getLocation(), Integer.MAX_VALUE, new FunctionUnaryOperatorHandler(name));
	}

	Expression getUnaryExpression(Token token, boolean isFunction) throws ParsingException {
		String name = token.getMessage().trim();
		// 字符串
		if (TypeUtils.isEscapeString(name))
			return new ConstantImpl(StringConvertUtils.convertLiteral(name.substring(1, name.length() - 1)), token.getLocation());

		// 非转义字符串
		if (TypeUtils.isNoEscapeString(name))
			return new ConstantImpl(name.substring(1, name.length() - 1), token.getLocation());

		// 数字
		if (TypeUtils.isNumber(name))
			return new ConstantImpl(NumberUtils.parseNumber(name), token.getLocation());

		// 关键字
		if (keywords.getNullKeyword().equals(name)) return new ConstantImpl(null, token.getLocation());
		if (keywords.getTrueKeyword().equals(name)) return new ConstantImpl(Boolean.TRUE, token.getLocation());
		if (keywords.getFalseKeyword().equals(name)) return new ConstantImpl(Boolean.FALSE, token.getLocation());
		if (Infinitude.SYMBOL.equals(name)) return new ConstantImpl(Infinitude.POSITIVE, token.getLocation());
		if ("(".equals(name)) return Parenthesis.LEFT_PARENTHESIS;

		// 名称
		if (TypeUtils.isNamed(name)) {
			if (! isFunction)
				return new VariableImpl(name, token.getLocation());

			if (! functionAvailable)
				throw new ParsingException(token.getLocation(), "ExpressionFactory.function.forbidden", new Object[]{name});
		}

		// 一元操作符
		if (operatorHandlerProvider.hasUnaryOperatorHandler(name))
			return new UnaryOperatorImpl(name, token.getLocation(),
					operatorHandlerProvider.getUnaryOperatorPriority(name),
					operatorHandlerProvider.getUnaryOperatorHandler(name));

		throw new ParsingException(token.getLocation(), "ExpressionFactory.invaild.unary.operator.name", new Object[]{name});
	}

	Expression getBinaryExpression(Token token) throws ParsingException {
		String name = token.getMessage().trim();
		// 关键字
		if (")".equals(name) || "]".equals(name))
			return Parenthesis.RIGHT_PARENTHESIS;

		// 二元操作符
		if (operatorHandlerProvider.hasBinaryOperatorHandler(name))
			return new BinaryOperatorImpl(name, token.getLocation(),
					operatorHandlerProvider.getBinaryOperatorPriority(name),
					operatorHandlerProvider.getBinaryOperatorHandler(name));

		throw new ParsingException(token.getLocation(), "ExpressionFactory.invaild.binary.operator.name", new Object[]{name});
	}

}
