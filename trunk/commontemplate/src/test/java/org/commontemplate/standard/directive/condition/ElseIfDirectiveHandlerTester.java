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
 * ElseIfDirectiveHandler 的测试。
 * @author YanRong
 *
 */
public class ElseIfDirectiveHandlerTester extends TestCase {

	private Engine engine = new Engine(PropertiesConfigurationLoader.loadStandardConfiguration());
	private Context context;
	private ElseIfDirectiveHandler directiveHandler;

	protected void setUp() throws Exception {

		// 构造context
		Writer out = new StringWriter();
		context = engine.createContext(out);
		// 构造一个上级内容上下文。
		context.pushLocalContext("ParentContext");
		directiveHandler = new ElseIfDirectiveHandler();
	}
	
	/**
	 * 对doRender 方法的测试。<br>
	 * @condition
	 * 条件<br>
	 * 之前的 if 指令的条件为 false, 当前指令的条件为 true。
	 * @result
	 * 结果<br>
	 * 会调用 innerElements 里的 render 方法。
	 */
	public void testDoRenderIFIsFalseCurrentIsTrueCondition() throws Exception {
		
		List innerElements = new ArrayList();
		innerElements.add(new MockDirective("a"));
		innerElements.add(new MockDirective("b"));
		innerElements.add(new MockDirective("c"));
		
		context.getSuperLocalContext().setStatus(IfDirectiveHandler.IF_STATUS, Boolean.valueOf(false));
		
		directiveHandler.doRender(context, "directiveName", Boolean.TRUE, innerElements);
		Writer out = context.getOut();
		assertEquals("abc", out.toString());
		
		assertTrue(((Boolean)context.getSuperLocalContext().getStatus(IfDirectiveHandler.IF_STATUS)).booleanValue());
	}
	
	/**
	 * 对doRender 方法的测试。<br>
	 * @condition
	 * 条件<br>
	 * 之前的 if 指令的条件为 false, 当前指令的条件为 false。
	 * @result
	 * 结果<br>
	 * 不会调用 innerElements 里的 render 方法。
	 */
	public void testDoRenderIFIsFalseCurrentIsFalseCondition() throws Exception {
		
		List innerElements = new ArrayList();
		innerElements.add(new MockDirective("a"));
		innerElements.add(new MockDirective("b"));
		innerElements.add(new MockDirective("c"));
		
		context.getSuperLocalContext().setStatus(IfDirectiveHandler.IF_STATUS, Boolean.valueOf(false));
		
		directiveHandler.doRender(context, "directiveName", Boolean.FALSE, innerElements);
		Writer out = context.getOut();
		assertEquals("", out.toString());
		
		assertFalse(((Boolean)context.getSuperLocalContext().getStatus(IfDirectiveHandler.IF_STATUS)).booleanValue());
	}
	
	/**
	 * 对doRender 方法的测试。<br>
	 * @condition
	 * 条件<br>
	 * 之前的 if 指令的条件为 true。
	 * @result
	 * 结果<br>
	 * 不会调用 innerElements 里的 render 方法。
	 */
	public void testDoRenderIFIsTrueCondition() throws Exception {
		
		List innerElements = new ArrayList();
		innerElements.add(new MockDirective("a"));
		innerElements.add(new MockDirective("b"));
		innerElements.add(new MockDirective("c"));
		
		context.getSuperLocalContext().setStatus(IfDirectiveHandler.IF_STATUS, Boolean.valueOf(true));
		
		directiveHandler.doRender(context, "directiveName", Boolean.TRUE, innerElements);
		Writer out = context.getOut();
		assertEquals("", out.toString());
		
		assertTrue(((Boolean)context.getSuperLocalContext().getStatus(IfDirectiveHandler.IF_STATUS)).booleanValue());
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
