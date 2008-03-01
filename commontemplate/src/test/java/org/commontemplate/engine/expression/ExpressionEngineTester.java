package org.commontemplate.engine.expression;

import java.io.IOException;

import org.commontemplate.config.Configuration;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ParsingException;
import org.commontemplate.engine.expression.ExpressionEngine;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;

public class ExpressionEngineTester extends TestCase {
	
	private ExpressionEngine expressionEngine;
	
	public void setUp() {
		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		expressionEngine = new ExpressionEngine(config);
	}
	
	public void testNullExpression() throws IOException, ParsingException {
		Expression expression = expressionEngine.parseExpression("3");
		super.assertEquals("3", expression.getName());
	}

}
