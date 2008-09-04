package org.commontemplate.engine.expression;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.commontemplate.config.Configuration;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ParsingException;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.util.Position;
import org.commontemplate.util.scanner.Token;

public class ExpressionTranslatorTester extends TestCase {

	private ExpressionTranslator expressionTranslator;

	public void setUp() {
		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		expressionTranslator = new ExpressionTranslator(
				new ExpressionFactoryImpl(config.getOperatorHandlerProvider(),
						config.getEvaluateInterceptors()),
				new ExpressionProvider(config.getOperatorHandlerProvider(),
						config.getEvaluateInterceptors(), config.getKeywords()), false);
	}

	public void testExpression() throws ParsingException {
		List tokens = new ArrayList();
		tokens.add(new Token(" 3", new Position(1, 1, 1)));
		tokens.add(new Token("  !=", new Position(1, 1, 2)));
		tokens.add(new Token(" 2 ", new Position(1, 1, 4)));
		List expressions = expressionTranslator.translate(tokens);
		super.assertEquals(3, expressions.size());
		super.assertEquals("3", ((Expression)expressions.get(0)).getName());
		super.assertEquals("!=", ((Expression)expressions.get(1)).getName());
		super.assertEquals("2", ((Expression)expressions.get(2)).getName());
	}

	public void testMutilOperator() throws ParsingException {
		List tokens = new ArrayList();
		tokens.add(new Token(" a", new Position(1, 1, 1)));
		tokens.add(new Token(" ||!", new Position(1, 1, 2)));
		tokens.add(new Token(" b", new Position(1, 1, 5)));
		List expressions = expressionTranslator.translate(tokens);
		super.assertEquals(4, expressions.size());
		super.assertEquals("a", ((Expression)expressions.get(0)).getName());
		super.assertEquals("||", ((Expression)expressions.get(1)).getName());
		super.assertEquals("!", ((Expression)expressions.get(2)).getName());
		super.assertEquals("b", ((Expression)expressions.get(3)).getName());
	}

	public void testBracket() throws ParsingException {
		List tokens = new ArrayList();
		tokens.add(new Token(" num", new Position(1, 1, 1)));
		tokens.add(new Token(" :", new Position(1, 1, 2)));
		tokens.add(new Token(" (", new Position(1, 1, 5)));
		tokens.add(new Token(" 1", new Position(1, 1, 5)));
		tokens.add(new Token(" ..", new Position(1, 1, 5)));
		tokens.add(new Token(" 9", new Position(1, 1, 5)));
		tokens.add(new Token(") ", new Position(1, 1, 5)));
		List expressions = expressionTranslator.translate(tokens);
		super.assertEquals(7, expressions.size());
		super.assertEquals("num", ((Expression)expressions.get(0)).getName());
		super.assertEquals(":", ((Expression)expressions.get(1)).getName());
		super.assertEquals("(", ((Expression)expressions.get(2)).getName());
		super.assertEquals("1", ((Expression)expressions.get(3)).getName());
		super.assertEquals("..", ((Expression)expressions.get(4)).getName());
		super.assertEquals("9", ((Expression)expressions.get(5)).getName());
		super.assertEquals(")", ((Expression)expressions.get(6)).getName());
	}

	public void testConjointOperatorExpression() throws ParsingException {
		List tokens = new ArrayList();
		tokens.add(new Token("3", new Position(1, 1, 1)));
		tokens.add(new Token("*-", new Position(1, 1, 2)));
		tokens.add(new Token("2 ", new Position(1, 1, 3)));
		List expressions = expressionTranslator.translate(tokens);
		assertEquals(4, expressions.size());
		assertEquals("3", ((Expression)expressions.get(0)).getName());
		assertEquals("*", ((Expression)expressions.get(1)).getName());
		assertEquals("-", ((Expression)expressions.get(2)).getName());
		assertEquals("2", ((Expression)expressions.get(3)).getName());
	}

}
