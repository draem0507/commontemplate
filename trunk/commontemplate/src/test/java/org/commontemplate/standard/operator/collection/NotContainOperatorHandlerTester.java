package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.Collection;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * NotContainOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class NotContainOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	
	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	
	/**
	 * 对2元操作符 !~ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个List 和 一个 Object。
	 * @result
	 * 结果<br>
	 * 返回List 中是否包含 Object 的boolean对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("!~");
		
		Collection list = new ArrayList();
		list.add("a");
		list.add("b");
		
		assertEquals(Boolean.FALSE, handler.doEvaluate("a", list));
		assertEquals(Boolean.TRUE, handler.doEvaluate("c", list));
		
	}
}
