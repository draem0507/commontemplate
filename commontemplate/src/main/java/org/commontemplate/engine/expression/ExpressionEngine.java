package org.commontemplate.engine.expression;

import java.io.IOException;
import java.util.List;

import org.commontemplate.config.ExpressionConfiguration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.core.BinaryOperator;
import org.commontemplate.core.Constant;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ExpressionBuilder;
import org.commontemplate.core.ExpressionFactory;
import org.commontemplate.core.ExpressionParser;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.UnaryOperator;
import org.commontemplate.core.Variable;
import org.commontemplate.util.Assert;
import org.commontemplate.util.Location;
import org.commontemplate.util.scanner.ScanningException;

/**
 * 表达式引擎 (线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public final class ExpressionEngine implements ExpressionParser {

	private final ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();

	private final ExpressionTranslator expressionTranslator;

	private final ExpressionOptimizer expressionOptimizer;

	private final ExpressionReducer expressionReducer;

	private final ExpressionFactory expressionFactory;

	private final OperatorHandlerProvider operatorHandlerProvider;

	private final List evaluateInterceptors;

	public ExpressionEngine(ExpressionConfiguration config) {
		Assert.assertNotNull(config, "ExpressionEngine.config.required");
		config.validate(); // 配置自验证
		operatorHandlerProvider = config.getOperatorHandlerProvider();
		evaluateInterceptors = config.getEvaluateInterceptors();
		ExpressionProvider expressionProvider = new ExpressionProvider(
				operatorHandlerProvider, evaluateInterceptors,
				config.getKeywords(), config.isFunctionAvailable());
		expressionTranslator = new ExpressionTranslator(expressionProvider, config.isFunctionAvailable());
		expressionFactory = new ExpressionFactoryImpl(operatorHandlerProvider, evaluateInterceptors);
		expressionReducer = new ExpressionReducer(evaluateInterceptors);
		expressionOptimizer = new ExpressionOptimizer(evaluateInterceptors);
	}

	public final Expression parseExpression(String expressionText) throws ParsingException {
		try {
			List tokens = expressionTokenizer.split(expressionText);
			List expressions = expressionTranslator.translate(tokens);
			// expressions = expressionOptimizer.optimize(expressions); // TODO 此优化方案待测试
			Expression root = expressionReducer.reduce(expressions);
			return root;
		} catch (ParsingException e) {
			throw e;
		} catch (ScanningException e) {
			throw new ParsingException(new Location(e.getPosition(), e.getPosition()), e);
		} catch (IOException e) { // 因为是字符串输入，一般不会出现IOException
			throw new RuntimeException(e);
		}
	}

	public BinaryOperator createBinaryOperator(String operatorName,
			Expression leftOprand, Expression rightOprand) {
		return expressionFactory.createBinaryOperator(operatorName, leftOprand,
				rightOprand);
	}

	public Constant createConstant(Object constantValue) {
		return expressionFactory.createConstant(constantValue);
	}

	public UnaryOperator createUnaryOperator(String operatorName,
			Expression oprand) {
		return expressionFactory.createUnaryOperator(operatorName, oprand);
	}

	public Variable createVariable(String variableName) {
		return expressionFactory.createVariable(variableName);
	}

	public ExpressionBuilder getExpressionBuilder() {
		return new ExpressionBuilderImpl(operatorHandlerProvider, evaluateInterceptors);
	}

}
