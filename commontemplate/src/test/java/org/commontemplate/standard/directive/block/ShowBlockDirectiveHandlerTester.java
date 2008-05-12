package org.commontemplate.standard.directive.block;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.engine.Engine;
import org.commontemplate.standard.directive.MockDirective;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * ShowBlockDirectiveHandler 的测试。
 * @author YanRong
 *
 */
public class ShowBlockDirectiveHandlerTester extends TestCase {

	private Engine engine = new Engine(PropertiesConfigurationLoader.loadStandardConfiguration());
	private Context context;
	private ShowBlockDirectiveHandler showDirectiveHandler;

	protected void setUp() throws Exception {

		// 构造context
		Writer out = new StringWriter();
		context = engine.createContext(out);
		showDirectiveHandler = new ShowBlockDirectiveHandler();
	}
	
	/**
	 * 对doRender 方法的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个引用模块的名称。
	 * @result
	 * 结果<br>
	 * 应该调用所引用的模块的全部Element的render方法。
	 */
	public void testDoRender() throws Exception{
		
		List elementList = new ArrayList();
		elementList.add(new MockDirective("aa"));
		elementList.add(new MockDirective("bb"));
		elementList.add(new MockDirective("cc"));
		elementList.add(new MockDirective("dd"));
		
		context.putProperty(BlockDefineDirectiveHandler.BLOCK_TYPE, "showBlock", elementList);
		
		showDirectiveHandler.doRender(context, "testShowBlock", "showBlock");
		Writer out = context.getOut();
		assertEquals("aabbccdd", out.toString());
	}
	
	/**
	 * 对doRender 方法的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入引用模块的名称为空。
	 * @result
	 * 结果<br>
	 * 应该抛出异常。
	 */
	public void testDoRenderExpectException() {
		
		boolean expect = false;
		try {
			
			showDirectiveHandler.doRender(context, null, null);
		} catch (Exception e) {
			expect = true;
		}
		assertTrue(expect);
	}
	
}
