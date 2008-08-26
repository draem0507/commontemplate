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
		directiveFactory = new DirectiveProvider(config.getSyntax(), config.getDirectiveHandlerProvider(), expressionParser, config.getTextFilter(), config.getRenderInterceptors());
	}

	public void testCountNoParseSlash() {
		String src = "aaa\\!$bbb";
		int loc = src.indexOf("!$");
		super.assertEquals(4, loc);
		super.assertEquals(1, directiveFactory.countStringLastSlash(src, loc));
	}

	public void testUnCleanNoParseEscape() {
		super.assertEquals("\\", directiveFactory.cleanStringEscape("\\\\", "!$"));
		super.assertEquals("aaa!$bbb\\", directiveFactory.cleanStringEscape("aaa\\!$bbb\\\\", "!$"));
		super.assertEquals("aaa\\!$bbb\\\\", directiveFactory.cleanStringEscape("aaa\\\\\\!$bbb\\\\\\\\", "!$"));
		super.assertEquals("aaa\\\\!$bbb\\\\\\", directiveFactory.cleanStringEscape("aaa\\\\\\\\\\!$bbb\\\\\\\\\\\\", "!$"));
	}

	public void testUnCleanNoParseSlash() {
		super.assertEquals("aaa!$bbb\\c", directiveFactory.cleanStringEscape("aaa\\!$bbb\\c", "!$"));
		super.assertEquals("aaa\\!$bbb\\\\c", directiveFactory.cleanStringEscape("aaa\\\\\\!$bbb\\\\c", "!$"));
		super.assertEquals("aaa\\\\!$bbb\\\\\\c", directiveFactory.cleanStringEscape("aaa\\\\\\\\\\!$bbb\\\\\\c", "!$"));
	}

	public void testCleanNoParseEscape() {
		super.assertEquals("aaa!$bbb", directiveFactory.cleanStringEscape("aaa\\!$bbb", "!$"));
		super.assertEquals("aaa\\!$bbb", directiveFactory.cleanStringEscape("aaa\\\\\\!$bbb", "!$"));
		super.assertEquals("aaa\\\\!$bbb", directiveFactory.cleanStringEscape("aaa\\\\\\\\\\!$bbb", "!$"));

		super.assertEquals("!$bbb", directiveFactory.cleanStringEscape("\\!$bbb", "!$"));
		super.assertEquals("\\!$bbb", directiveFactory.cleanStringEscape("\\\\\\!$bbb", "!$"));
		super.assertEquals("\\\\!$bbb", directiveFactory.cleanStringEscape("\\\\\\\\\\!$bbb", "!$"));

		super.assertEquals("aaa!$", directiveFactory.cleanStringEscape("aaa\\!$", "!$"));
		super.assertEquals("aaa\\!$", directiveFactory.cleanStringEscape("aaa\\\\\\!$", "!$"));
		super.assertEquals("aaa\\\\!$", directiveFactory.cleanStringEscape("aaa\\\\\\\\\\!$", "!$"));
	}

	public void testCleanCommentEscape() {
		super.assertEquals("aaa*$bbb", directiveFactory.cleanStringEscape("aaa\\*$bbb", "*$"));
		super.assertEquals("aaa\\*$bbb", directiveFactory.cleanStringEscape("aaa\\\\\\*$bbb", "*$"));
		super.assertEquals("aaa\\\\*$bbb", directiveFactory.cleanStringEscape("aaa\\\\\\\\\\*$bbb", "*$"));

		super.assertEquals("*$bbb", directiveFactory.cleanStringEscape("\\*$bbb", "*$"));
		super.assertEquals("\\*$bbb", directiveFactory.cleanStringEscape("\\\\\\*$bbb", "*$"));
		super.assertEquals("\\\\*$bbb", directiveFactory.cleanStringEscape("\\\\\\\\\\*$bbb", "*$"));

		super.assertEquals("aaa*$", directiveFactory.cleanStringEscape("aaa\\*$", "*$"));
		super.assertEquals("aaa\\*$", directiveFactory.cleanStringEscape("aaa\\\\\\*$", "*$"));
		super.assertEquals("aaa\\\\*$", directiveFactory.cleanStringEscape("aaa\\\\\\\\\\*$", "*$"));
	}

}
