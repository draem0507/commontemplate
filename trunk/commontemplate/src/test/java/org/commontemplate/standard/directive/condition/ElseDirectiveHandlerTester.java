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
 * ElseDirectiveHandler 的测试。
 * @author YanRong
 *
 */
public class ElseDirectiveHandlerTester extends TestCase {

	private Engine engine = new Engine(PropertiesConfigurationLoader.loadStandardConfiguration());
	private Context context;
	private ElseDirectiveHandler directiveHandler;

	protected void setUp() throws Exception {

		// 构造context
		Writer out = new StringWriter();
		context = engine.createContext(out);
		// 构造一个上级内容上下文。
		context.pushLocalContext("ParentContext");
		directiveHandler = new ElseDirectiveHandler();
	}
	
	/**
	 * 对doRender 方法的测试。<br>
	 * @condition
	 * 条件<br>
	 * @result
	 * 结果<br>
	 * 如果之前的 if 指令的条件为 false,那么调用 innerElements 的 render方法。
	 */
	public void testDoRenderFalseCondition() throws Exception{
		
		List innerElements = new ArrayList();
		innerElements.add(new MockDirective("a"));
		innerElements.add(new MockDirective("b"));
		innerElements.add(new MockDirective("c"));
		
		context.getSuperLocalContext().setStatus(IfDirectiveHandler.IF_STATUS, Boolean.valueOf(false));
		
		directiveHandler.doRender(context, "directiveName", null, innerElements);
		
		Writer out = context.getOut();
		assertEquals("abc", out.toString());
		
		assertNull(context.getSuperLocalContext().getStatus(IfDirectiveHandler.IF_STATUS));
		
	}
	
	/**
	 * 对doRender 方法的测试。<br>
	 * @condition
	 * 条件<br>
	 * @result
	 * 结果<br>
	 * 如果之前的 if 指令的条件为 true,那么不调用 innerElements 的 render方法。
	 */
	public void testDoRenderTrueCondition() throws Exception{
		
		List innerElements = new ArrayList();
		innerElements.add(new MockDirective("a"));
		innerElements.add(new MockDirective("b"));
		innerElements.add(new MockDirective("c"));
		
		context.getSuperLocalContext().setStatus(IfDirectiveHandler.IF_STATUS, Boolean.valueOf(true));
		
		directiveHandler.doRender(context, "directiveName", null, innerElements);
		
		Writer out = context.getOut();
		assertEquals("", out.toString());
		
		assertNull(context.getSuperLocalContext().getStatus(IfDirectiveHandler.IF_STATUS));
		
	}
	
	/**
	 * 对doRender 方法的测试。<br>
	 * @condition
	 * 条件<br>
	 * @result
	 * 结果<br>
	 * 如果之前缺少 if 指令，那么会抛出异常。
	 */
	public void testDoRenderException() throws Exception{
		
		boolean expect = false;
		
		try {
		
			directiveHandler.doRender(context, "directiveName", null, null);
		} catch (Exception e) {
			expect = true;
		}
		assertTrue(expect);
	}
	
}
