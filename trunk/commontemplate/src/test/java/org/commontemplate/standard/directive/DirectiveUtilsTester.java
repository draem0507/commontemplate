package org.commontemplate.standard.directive;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.commontemplate.core.Context;
import org.commontemplate.engine.Engine;
import org.commontemplate.tools.PropertiesConfigurationLoader;
/**
 * DirectiveUtils 的测试。
 * @author YanRong
 *
 */
public class DirectiveUtilsTester extends TestCase {


	private Engine engine = new Engine(PropertiesConfigurationLoader.loadStandardConfiguration());
	private Context context;

	protected void setUp() throws Exception {

		// 构造context
		Writer out = new StringWriter();
		context = engine.createContext(out);
	}

	/**
	 * 对renderAll 方法的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个List对象，元素是 directive。
	 * @result
	 * 结果<br>
	 * 应该调用所有directive的renderAll方法。
	 */
	public void testRenderAll() {

		List elementList = new ArrayList();
		elementList.add(new MockDirective("aa"));
		elementList.add(new MockDirective("bb"));
		elementList.add(new MockDirective("cc"));
		elementList.add(new MockDirective("dd"));

		DirectiveUtils.renderAll(elementList, context);

		Writer out = context.getOut();
		assertEquals("aabbccdd", out.toString());

	}
}
