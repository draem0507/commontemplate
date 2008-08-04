package org.commontemplate.standard.directive.condition;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.commontemplate.core.Context;
import org.commontemplate.engine.Engine;
import org.commontemplate.standard.directive.MockDirective;
import org.commontemplate.tools.PropertiesConfigurationLoader;
/**
 * IfDirectiveHandler 的测试。
 * @author YanRong
 *
 */
public class IfDirectiveHandlerTester extends TestCase {

	private Engine engine = new Engine(PropertiesConfigurationLoader.loadStandardConfiguration());
	private Context context;
	private IfDirectiveHandler directiveHandler;

	protected void setUp() throws Exception {

		// 构造context
		Writer out = new StringWriter();
		context = engine.createContext(out);
		// 构造一个上级内容上下文。
		context.pushLocalContext("ParentContext");
		directiveHandler = new IfDirectiveHandler();
	}
	
	/**
	 * 对doRender 方法的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个boolean相关的变量。
	 * @result
	 * 结果<br>
	 * 如果 bool值为 true，那么会调用 innerElements 里的 render 方法。
	 */
	public void testDoRenderTrueCondition() throws Exception {
		
		List innerElements = new ArrayList();
		innerElements.add(new MockDirective("a"));
		innerElements.add(new MockDirective("b"));
		innerElements.add(new MockDirective("c"));
		
		directiveHandler.doRender(context, "directiveName", "true", innerElements);
		
		Writer out = context.getOut();
		assertEquals("abc", out.toString());
		assertTrue(((Boolean)(context.getParentLocalContext().getStatus(IfDirectiveHandler.IF_STATUS))).booleanValue());
	}
	
	/**
	 * 对doRender 方法的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个boolean相关的变量。
	 * @result
	 * 结果<br>
	 * 如果 bool值为 false，那么不会调用 innerElements 里的 render 方法。
	 */
	public void testDoRenderFalseCondition() throws Exception {
		
		List innerElements = new ArrayList();
		innerElements.add(new MockDirective("a"));
		innerElements.add(new MockDirective("b"));
		innerElements.add(new MockDirective("c"));
		
		directiveHandler.doRender(context, "directiveName", null, innerElements);
		
		Writer out = context.getOut();
		assertEquals("", out.toString());
		assertFalse(((Boolean)(context.getParentLocalContext().getStatus(IfDirectiveHandler.IF_STATUS))).booleanValue());
	}
	
}
