package org.commontemplate.engine.expression;

import java.io.IOException;
import java.util.List;

import org.commontemplate.config.ExpressionConfiguration;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.ExpressionParser;
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

	private final ExpressionTokenizer expressionTokenizer;

	private final ExpressionTranslator expressionTranslator;

	private final ExpressionReducer expressionReducer;

	public ExpressionEngine(ExpressionConfiguration config) {
		Assert.assertNotNull(config, "ExpressionEngine.config.required");
		config.validate(); // 配置自验证

		expressionTokenizer = new ExpressionTokenizer();
		expressionTranslator = new ExpressionTranslator(new ExpressionFactory(
				config.getOperatorHandlerProvider(), config.getKeywords(),
				config.isFunctionAvailable()), config.isFunctionAvailable());
		expressionReducer = new ExpressionReducer();
	}

	public final Expression parseExpression(String expressionText) throws ParsingException {
		try {
			List tokens = expressionTokenizer.split(expressionText);
			List expressions = expressionTranslator.translate(tokens);
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

}
