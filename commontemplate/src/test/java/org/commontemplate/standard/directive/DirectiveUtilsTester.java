package org.commontemplate.standard.directive;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.commontemplate.core.BreakVisitException;
import org.commontemplate.core.Context;
import org.commontemplate.core.Element;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Visitor;
import org.commontemplate.engine.Engine;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.util.Location;

import junit.framework.TestCase;
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
	
	class MockDirective extends Element {
		
		private String name;
		
		public MockDirective(String name) {
			this.name = name;
		}
		
		public void render(Context context) throws RenderingException {
			
			Writer out = context.getOut();
			try {
				out.write(name);
			} catch (IOException e) {
				
			}
		}
		
		/**
		 * 获取模板元素的名称
		 * 
		 * @return 模板元素名称
		 */
		public String getName() {
			return name;
		}

		/**
		 * 获取模板元素在模板中的位置
		 * 
		 * @return 元素在模板中的位置
		 */
		public Location getLocation() {
			return null;
		}
		
		/**
		 * 获取模板元素的标准组成
		 * 
		 * @return 模板元素的标准组成
		 */
		public String getCanonicalForm() {
			return null;
		}

		/**
		 * 返回模板元素的标准组成, 同getCanonicalForm()
		 * 
		 * @return 模板元素的标准组成
		 */
		public String toString() {
			return getCanonicalForm();
		}
		
		/**
		 * 接收访问者, 并带领访问者遍历整个树 (中序遍历)
		 * 
		 * @param visitor 访问者
		 */
		public void accept(Visitor visitor) throws BreakVisitException {
			
		}

		public void setName(String name) {
			this.name = name;
		}
	}
	
}
