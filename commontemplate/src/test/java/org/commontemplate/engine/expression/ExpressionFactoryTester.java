package org.commontemplate.engine.expression;

import org.commontemplate.config.Configuration;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ParsingException;
import org.commontemplate.engine.expression.ExpressionFactory;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.util.Location;
import org.commontemplate.util.Position;
import org.commontemplate.util.scanner.Token;

import junit.framework.TestCase;

public class ExpressionFactoryTester extends TestCase {
	
	private ExpressionFactory expressionFactory;
	
	public void setUp() {
		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		expressionFactory = new ExpressionFactory(config.getOperatorHandlerProvider(), config.getKeywords(), config.isFunctionAvailable());
	}
	
	public void testNumberExpression() throws ParsingException {
		Expression expression = expressionFactory.getUnaryExpression(new Token("3", 1, new Position(1, 1)));
		super.assertEquals("3", expression.getName());
		super.assertEquals(new Location(1, 1, 1, 2), expression.getLocation());
	}
	
	public void testNullExpression() throws ParsingException {
		Expression expression = expressionFactory.getUnaryExpression(new Token("null", 1, new Position(1, 1)));
		super.assertEquals("null", expression.getName());
		super.assertEquals(new Location(1, 1, 1, 5), expression.getLocation());
	}
	
	public void testNull() throws ParsingException {
		try {
			expressionFactory.getUnaryExpression(null);
			fail("传入空的表达式名, 应该返回空指针异常!");
		} catch (NullPointerException e) {
			// right
		}
	}

}
