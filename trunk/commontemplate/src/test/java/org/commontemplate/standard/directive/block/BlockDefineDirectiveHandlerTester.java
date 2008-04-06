package org.commontemplate.standard.directive.block;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.engine.Engine;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * BlockDefineDirectiveHandler 的测试。
 * @author yanrong
 *
 */
public class BlockDefineDirectiveHandlerTester extends TestCase {

	private Engine engine = new Engine(PropertiesConfigurationLoader.loadStandardConfiguration());
	private Context context;
	private BlockDefineDirectiveHandler blockDirectiveHandler;

	protected void setUp() throws Exception {

		// 构造context
		Writer out = new StringWriter();
		context = engine.createContext(out);
		// 构造一个上级内容上下文。
		context.pushLocalContext("ParentContext");
		blockDirectiveHandler = new BlockDefineDirectiveHandler();
	}
	/**
	 *  测试 BlockDefineDirectiveHandler。
	 *  @condition
	 *  条件<br>
	 *  构造一个内容上下文，block名称，和一个Element对象列表。
	 *  @result
	 *  结果<br>
	 *  应该根据block名称，把Element对象列表放入到内容上下文的上一级内容上下文中。
	 * @throws Exception
	 */
	public void testDoRender() throws Exception {
		
		List innerElements = new ArrayList();
		// 此处实际上应该增加 Element 对象，但是我们仅仅测试List的内容，
		// 所以装入了字符串。
		innerElements.add("a");
		innerElements.add("b");
		
		blockDirectiveHandler.doRender(context, "test", "blockName", innerElements);
		
		assertEquals(innerElements, context.getSuperLocalContext().lookupObject(BlockDefineDirectiveHandler.BLOCK_TYPE, "blockName"));
		
	}
	
	/**
	 *  测试 BlockDefineDirectiveHandler。
	 *  @condition
	 *  条件<br>
	 *  构造一个内容上下文，block名称为空。
	 *  @result
	 *  结果<br>
	 *  应该抛出异常。
	 * @throws Exception
	 */
	public void testDoRenderExpectException() throws Exception {
		
		boolean expect = false;
		
		try {
			// 如果Block名称为空，就会抛出异常，所以我们就没有必要再构造 Element List 对象了。
			blockDirectiveHandler.doRender(context, "test", null, null);
		} catch (java.lang.IllegalArgumentException e) {
			expect = true;
		}
		assertTrue(expect);
	}
	
}
