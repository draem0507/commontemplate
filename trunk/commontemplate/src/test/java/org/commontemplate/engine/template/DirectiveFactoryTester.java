package org.commontemplate.engine.template;

import java.io.IOException;

import org.commontemplate.config.Configuration;
import org.commontemplate.core.Element;
import org.commontemplate.core.ExpressionParser;
import org.commontemplate.core.ParsingException;
import org.commontemplate.engine.expression.ExpressionEngine;
import org.commontemplate.engine.template.DirectiveProvider;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.util.Location;
import org.commontemplate.util.Position;
import org.commontemplate.util.scanner.Token;

import junit.framework.TestCase;

public class DirectiveFactoryTester extends TestCase {

	DirectiveProvider directiveFactory;

	public void setUp() {
		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		ExpressionParser expressionParser = new ExpressionEngine(config);
		directiveFactory = new DirectiveProvider(config.getSyntax(), config.getDirectiveHandlerProvider(), expressionParser, config.getTextFilter(), config.getRenderInterceptors());
	}

	public void testNullDirective() throws IOException, ParsingException {
		Element directive = directiveFactory.getDirective(new Token("$if{true}", new Position(21, 10, 12)), false);
		super.assertEquals("if", directive.getName());
		super.assertEquals(new Location(21, 10, 12, 30, 10, 21), directive.getLocation());
	}

	public void testCleanEscape() {
		super.assertEquals("aaa$bbb", directiveFactory.cleanEscape("aaa\\$bbb", false));
		super.assertEquals("aaa\\$bbb", directiveFactory.cleanEscape("aaa\\\\\\$bbb", false));
	}

	public void testUnCleanEscape() {
		super.assertEquals("aaa\\bbb", directiveFactory.cleanEscape("aaa\\bbb", false));
		super.assertEquals("aaa\\\\bbb", directiveFactory.cleanEscape("aaa\\\\bbb", false));
		super.assertEquals("aaa\\\\\\bbb", directiveFactory.cleanEscape("aaa\\\\\\bbb", false));
		super.assertEquals("\\\\\\\\bbb", directiveFactory.cleanEscape("\\\\\\\\bbb", false));
	}

	public void testCleanEscapeError() {
		try {
			directiveFactory.cleanEscape("aaa\\\\$bbb", false);
			fail("偶数个反斜杠是错误的! 应抛出异常");
		} catch (IllegalStateException e) {
			// right
		}

		try {
			directiveFactory.cleanEscape("aaa\\\\\\\\$bbb", false);
			fail("偶数个反斜杠是错误的! 应抛出异常");
		} catch (IllegalStateException e) {
			// right
		}
	}

	public void testCleanEscapeLastSlash() {
		super.assertEquals("aaabbb\\", directiveFactory.cleanEscape("aaabbb\\\\", false));
		super.assertEquals("aaabbb\\\\", directiveFactory.cleanEscape("aaabbb\\\\\\\\", false));
		super.assertEquals("aaabbb\\\\\\", directiveFactory.cleanEscape("aaabbb\\\\\\\\\\\\", false));
	}

	public void testUnCleanEscapeLastSlash() {
		super.assertEquals("aaabbb\\", directiveFactory.cleanEscape("aaabbb\\", true));
		super.assertEquals("aaabbb\\\\", directiveFactory.cleanEscape("aaabbb\\\\", true));
		super.assertEquals("aaabbb\\\\\\", directiveFactory.cleanEscape("aaabbb\\\\\\", true));
		super.assertEquals("aaabbb\\\\\\\\", directiveFactory.cleanEscape("aaabbb\\\\\\\\", true));
	}

	public void testCleanEscapeLastSlashError() {
		try {
			directiveFactory.cleanEscape("aaabbb\\", false);
			fail("奇数个结尾反斜杠是错误的! 应抛出异常");
		} catch (IllegalStateException e) {
			// right
		}

		try {
			directiveFactory.cleanEscape("aaabbb\\\\\\", false);
			fail("奇数个结尾反斜杠是错误的! 应抛出异常");
		} catch (IllegalStateException e) {
			// right
		}
	}

	public void testCountSlash() {
		StringBuffer buf = new StringBuffer(60);
		buf.append("adga\\\\\\\\\\");
		super.assertEquals(5, directiveFactory.countLastSlash(buf));
	}

	public void testStartCountSlash() {
		StringBuffer buf = new StringBuffer(60);
		buf.append("\\\\\\\\\\");
		super.assertEquals(5, directiveFactory.countLastSlash(buf));
	}

	public void testZeroCountSlash() {
		StringBuffer buf = new StringBuffer(60);
		buf.append("\\\\\\\\\\aa");
		super.assertEquals(0, directiveFactory.countLastSlash(buf));
	}
}
