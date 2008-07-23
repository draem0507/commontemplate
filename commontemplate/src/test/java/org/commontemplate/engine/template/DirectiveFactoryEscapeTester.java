package org.commontemplate.engine.template;

import junit.framework.TestCase;

import org.commontemplate.config.Configuration;
import org.commontemplate.engine.expression.ExpressionEngine;
import org.commontemplate.tools.PropertiesConfigurationLoader;

public class DirectiveFactoryEscapeTester extends TestCase {

	DirectiveProvider directiveFactory;

	public void setUp() {
		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		ExpressionEngine expressionParser = new ExpressionEngine(config);
		directiveFactory = new DirectiveProvider(config.getSyntax(), config.getDirectiveHandlerProvider(), expressionParser, config.getTextFilter(), config.getRenderingInterceptors());
	}

	public void testCountNoParseSlash() {
		String src = "aaa\\!$bbb";
		int loc = src.indexOf("!$");
		super.assertEquals(4, loc);
		super.assertEquals(1, directiveFactory.countInnerLastSlash(src, loc));
	}

	public void testUnCleanNoParseEscape() {
		super.assertEquals("aaa!$bbb\\", directiveFactory.cleanInnerEscape("aaa\\!$bbb\\\\", "!$"));
		super.assertEquals("aaa\\!$bbb\\\\", directiveFactory.cleanInnerEscape("aaa\\\\\\!$bbb\\\\\\\\", "!$"));
		super.assertEquals("aaa\\\\!$bbb\\\\\\", directiveFactory.cleanInnerEscape("aaa\\\\\\\\\\!$bbb\\\\\\\\\\\\", "!$"));
	}

	public void testUnCleanNoParseSlash() {
		super.assertEquals("aaa!$bbb\\c", directiveFactory.cleanInnerEscape("aaa\\!$bbb\\c", "!$"));
		super.assertEquals("aaa\\!$bbb\\\\c", directiveFactory.cleanInnerEscape("aaa\\\\\\!$bbb\\\\c", "!$"));
		super.assertEquals("aaa\\\\!$bbb\\\\\\c", directiveFactory.cleanInnerEscape("aaa\\\\\\\\\\!$bbb\\\\\\c", "!$"));
	}

	public void testCleanNoParseEscape() {
		super.assertEquals("aaa!$bbb", directiveFactory.cleanInnerEscape("aaa\\!$bbb", "!$"));
		super.assertEquals("aaa\\!$bbb", directiveFactory.cleanInnerEscape("aaa\\\\\\!$bbb", "!$"));
		super.assertEquals("aaa\\\\!$bbb", directiveFactory.cleanInnerEscape("aaa\\\\\\\\\\!$bbb", "!$"));

		super.assertEquals("!$bbb", directiveFactory.cleanInnerEscape("\\!$bbb", "!$"));
		super.assertEquals("\\!$bbb", directiveFactory.cleanInnerEscape("\\\\\\!$bbb", "!$"));
		super.assertEquals("\\\\!$bbb", directiveFactory.cleanInnerEscape("\\\\\\\\\\!$bbb", "!$"));

		super.assertEquals("aaa!$", directiveFactory.cleanInnerEscape("aaa\\!$", "!$"));
		super.assertEquals("aaa\\!$", directiveFactory.cleanInnerEscape("aaa\\\\\\!$", "!$"));
		super.assertEquals("aaa\\\\!$", directiveFactory.cleanInnerEscape("aaa\\\\\\\\\\!$", "!$"));
	}

	public void testCleanCommentEscape() {
		super.assertEquals("aaa*$bbb", directiveFactory.cleanInnerEscape("aaa\\*$bbb", "*$"));
		super.assertEquals("aaa\\*$bbb", directiveFactory.cleanInnerEscape("aaa\\\\\\*$bbb", "*$"));
		super.assertEquals("aaa\\\\*$bbb", directiveFactory.cleanInnerEscape("aaa\\\\\\\\\\*$bbb", "*$"));

		super.assertEquals("*$bbb", directiveFactory.cleanInnerEscape("\\*$bbb", "*$"));
		super.assertEquals("\\*$bbb", directiveFactory.cleanInnerEscape("\\\\\\*$bbb", "*$"));
		super.assertEquals("\\\\*$bbb", directiveFactory.cleanInnerEscape("\\\\\\\\\\*$bbb", "*$"));

		super.assertEquals("aaa*$", directiveFactory.cleanInnerEscape("aaa\\*$", "*$"));
		super.assertEquals("aaa\\*$", directiveFactory.cleanInnerEscape("aaa\\\\\\*$", "*$"));
		super.assertEquals("aaa\\\\*$", directiveFactory.cleanInnerEscape("aaa\\\\\\\\\\*$", "*$"));
	}

}
