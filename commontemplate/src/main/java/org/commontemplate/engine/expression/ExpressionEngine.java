package org.commontemplate.engine.expression;

import java.util.List;

import org.commontemplate.config.ExpressionConfiguration;
import org.commontemplate.config.ExpressionFilter;
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
import org.commontemplate.util.Position;
import org.commontemplate.util.scanner.ScanningException;
import org.commontemplate.util.scanner.Token;

/**
 * 表达式引擎. (线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public class ExpressionEngine implements ExpressionParser {

	private final ExpressionFilter expressionFilter;

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
		expressionFilter = config.getExpressionFilter();
		operatorHandlerProvider = config.getOperatorHandlerProvider();
		evaluateInterceptors = config.getEvaluateInterceptors();
		expressionFactory = new ExpressionFactoryImpl(operatorHandlerProvider, evaluateInterceptors);
		ExpressionProvider expressionProvider = new ExpressionProvider(
				operatorHandlerProvider, evaluateInterceptors,
				config.getKeywords());
		expressionTranslator = new ExpressionTranslator(expressionFactory, expressionProvider, config.isFunctionAvailable());
		expressionReducer = new ExpressionReducer(evaluateInterceptors);
		expressionOptimizer = new ExpressionOptimizer(evaluateInterceptors);
	}

	public Expression parseExpression(String expressionText) throws ParsingException {
		try {
			if (expressionFilter != null)
				expressionText = expressionFilter.filter(expressionText);
			List tokens = expressionTokenizer.split(expressionText);
			List expressions = expressionTranslator.translate(tokens);
			expressions = expressionOptimizer.optimize(expressions);
			Expression root = expressionReducer.reduce(expressions);
			return root;
		} catch (ParsingException e) {
			e.setSource(new SourceImpl(expressionText));
			throw e;
		} catch (ScanningException e) {
			ParsingException pe = new ParsingException(new Location(e.getPosition(), e.getPosition()), e);
			pe.setSource(new SourceImpl(expressionText));
			throw pe;
		} catch (Exception e) {
			ParsingException pe = new ParsingException(new Token(expressionText, Position.ZERO).getLocation(), e);
			pe.setSource(new SourceImpl(expressionText));
			throw pe;
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

	public ExpressionBuilder createExpressionBuilder() {
		return new ExpressionBuilderImpl(operatorHandlerProvider, evaluateInterceptors);
	}

}
