package org.commontemplate.standard.directive.block;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import org.commontemplate.core.Context;
import org.commontemplate.engine.Engine;
import org.commontemplate.standard.directive.macro.MacroDirectiveHandler;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * UsingAsMacroDirectiveHandler 的测试。
 * @author YanRong
 *
 */
public class UsingAsMacroDirectiveHandlerTester extends TestCase {

	private Engine engine = new Engine(PropertiesConfigurationLoader.loadStandardConfiguration());
	private Context context;
	private UsingAsMacroDirectiveHandler directiveHandler;

	protected void setUp() throws Exception {

		// 构造context
		Writer out = new StringWriter();
		context = engine.createContext(out);
		directiveHandler = new UsingAsMacroDirectiveHandler();
	}
	
	/**
	 * 对doRender 方法的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个Entry。
	 * @result
	 * 结果<br>
	 * 以Entry的value作为key,在context中寻找object，<br>
	 * 再将entry的key作为key，object 作为value，放入到context中。
	 */
	public void testDoRenderEntry() throws Exception{
		
		HashMap map = new HashMap();
		map.put("maroName1", "maroTemplate1");
		Set set = map.entrySet();
		Entry entry = (Entry) (set.iterator()).next();
		
		context.putObject(BlockDefineDirectiveHandler.BLOCK_TYPE, "maroTemplate1", "testit!");
		
		directiveHandler.doRender(context, "directiveName", entry);
		
		assertEquals("testit!", context.lookupObject(MacroDirectiveHandler.MACRO_TYPE, "maroName1"));
	}
	
	/**
	 * 对doRender 方法的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个Map。
	 * @result
	 * 结果<br>
	 * 循环 Map 中的每一个 Etnry，作如下操作 {
	 * 以Entry的value作为key,在context中寻找object，<br>
	 * 再将entry的key作为key，object 作为value，放入到context中。
	 * }
	 */
	public void testDoRenderMap() throws Exception{
		
		HashMap map = new HashMap();
		map.put("maroName1", "maroTemplate1");
		map.put("maroName2", "maroTemplate2");
		
		context.putObject(BlockDefineDirectiveHandler.BLOCK_TYPE, "maroTemplate1", "testit1!");
		context.putObject(BlockDefineDirectiveHandler.BLOCK_TYPE, "maroTemplate2", "testit2!");
		
		directiveHandler.doRender(context, "directiveName", map);
		
		assertEquals("testit1!", context.lookupObject(MacroDirectiveHandler.MACRO_TYPE, "maroName1"));
		assertEquals("testit2!", context.lookupObject(MacroDirectiveHandler.MACRO_TYPE, "maroName2"));
	}
	
	/**
	 * 对doRender 方法的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个非Entry 和 非 Map 的对象。 
	 * @result
	 * 结果<br>
	 * 会捕获到 java.lang.IllegalArgumentException。
	 */
	public void testExpectException() {
		
		boolean expect = false;
		try {
			directiveHandler.doRender(context, "directiveName", "test");
		} catch (java.lang.IllegalArgumentException e) {
			expect = true;
		} catch (Exception e) {
			
		}
		assertTrue(expect);
	}
	
}
