package org.commontemplate.engine.expression;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.commontemplate.config.Configuration;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ParsingException;
import org.commontemplate.engine.expression.ExpressionFactory;
import org.commontemplate.engine.expression.ExpressionTranslator;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.util.Position;
import org.commontemplate.util.scanner.Token;

public class ExpressionTranslatorTester extends TestCase {
	
	private ExpressionTranslator expressionTranslator;
	
	public void setUp() {
		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		expressionTranslator = new ExpressionTranslator(new ExpressionFactory(config.getOperatorHandlerProvider(), config.getKeywords(), config.isFunctionAvailable()), false);
	}
	
	public void testExpression() throws ParsingException {
		List tokens = new ArrayList();
		tokens.add(new Token(" 3", 1, new Position(1, 1)));
		tokens.add(new Token("  !=", 1, new Position(1, 2)));
		tokens.add(new Token(" 2 ", 1, new Position(1, 4)));
		List expressions = expressionTranslator.translate(tokens);
		super.assertEquals(3, expressions.size());
		super.assertEquals("3", ((Expression)expressions.get(0)).getName());
		super.assertEquals("!=", ((Expression)expressions.get(1)).getName());
		super.assertEquals("2", ((Expression)expressions.get(2)).getName());
	}
	
	public void testMutilOperator() throws ParsingException {
		List tokens = new ArrayList();
		tokens.add(new Token(" a", 1, new Position(1, 1)));
		tokens.add(new Token(" ||!", 1, new Position(1, 2)));
		tokens.add(new Token(" b", 1, new Position(1, 5)));
		List expressions = expressionTranslator.translate(tokens);
		super.assertEquals(4, expressions.size());
		super.assertEquals("a", ((Expression)expressions.get(0)).getName());
		super.assertEquals("||", ((Expression)expressions.get(1)).getName());
		super.assertEquals("!", ((Expression)expressions.get(2)).getName());
		super.assertEquals("b", ((Expression)expressions.get(3)).getName());
	}
	
	public void testBracket() throws ParsingException {
		List tokens = new ArrayList();
		tokens.add(new Token(" num", 1, new Position(1, 1)));
		tokens.add(new Token(" :", 1, new Position(1, 2)));
		tokens.add(new Token(" (", 1, new Position(1, 5)));
		tokens.add(new Token(" 1", 1, new Position(1, 5)));
		tokens.add(new Token(" ..", 1, new Position(1, 5)));
		tokens.add(new Token(" 9", 1, new Position(1, 5)));
		tokens.add(new Token(") ", 1, new Position(1, 5)));
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

}
