package org.commontemplate.engine.template;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import junit.framework.TestCase;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.Syntax;
import org.commontemplate.core.BlockDirective;
import org.commontemplate.engine.expression.ExpressionEngine;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.util.scanner.ScanningException;

/**
 * 模板元素树归约器测试
 *
 * @author Yan Rong (yananay@126.com)
 *
 */
public class DirectiveReducerTester extends TestCase {

	private DirectiveTokenizer directiveTokenizer;
	private DirectiveProvider directiveFactory;
	private DirectiveTranslator directiveTranslator;
	private DirectiveReducer directiveReducer;

	public void setUp() {
		directiveTokenizer = new DirectiveTokenizer(Syntax.DEFAULT);

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		ExpressionEngine expressionParser = new ExpressionEngine(config);
		directiveFactory = new DirectiveProvider(config.getSyntax(),
				config.getDirectiveHandlerProvider(), expressionParser,
				config.getTextFilter(), config.getRenderingInterceptors());

		directiveTranslator = new DirectiveTranslator(directiveFactory);

		directiveReducer = new DirectiveReducer();
	}

	/**
	 * DirectiveReducer类的reduce方法的测试。<br>
	 * @conditoin
	 * 条件：<br>
	 *   字符串为 "aaa $if{i==1} bbbb $else  ccc $end ddd "<br>
	 * @result
	 * 结果：<br>
	 *   应该生成4个指令。<br>
	 *   指令1是 aaa<br>
	 *   指令2是 if,这是一个block指令，其中还会包含一个 text 指令 bbbb<br>
	 *   指令3是 else,这是一个block指令，其中还会包含一个 text 指令 ccc<br>
	 *   指令4是 ddd
	 * @throws IOException
	 * @throws ScanningException
	 */
	public void testReduce() throws IOException, ScanningException{

		String template = "aaa $if{i==1} bbbb $else  ccc $end ddd ";
		List tokens = directiveTokenizer.split(new StringReader(template));
		List directives = directiveTranslator.translate(tokens);

		assertEquals(directives.size(), 7);

		BlockDirective root = directiveReducer.reduce(directives);

		assertEquals(root.getElements().size(), 4);

		List innerDirectiveLists = root.getElements();

		assertTrue(innerDirectiveLists.get(0) instanceof TextImpl);

		assertTrue(innerDirectiveLists.get(1) instanceof BlockDirectiveImpl);
		BlockDirective blockDirective = (BlockDirective) innerDirectiveLists.get(1);
		assertEquals(blockDirective.getElements().size(), 1);
		assertTrue(blockDirective.getElements().get(0) instanceof TextImpl);
		assertNotNull(blockDirective.getExpression());

		assertTrue(innerDirectiveLists.get(2) instanceof MiddleBlockDirectiveImpl);
		blockDirective = (BlockDirective) innerDirectiveLists.get(2);
		assertEquals(blockDirective.getElements().size(), 1);
		assertTrue(blockDirective.getElements().get(0) instanceof TextImpl);

		assertTrue(innerDirectiveLists.get(3) instanceof TextImpl);

	}

}
