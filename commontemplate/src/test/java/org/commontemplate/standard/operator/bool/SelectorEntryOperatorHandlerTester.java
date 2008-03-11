package org.commontemplate.standard.operator.bool;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * SelectorEntryOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class SelectorEntryOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	
	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	
	/**
	 * 对2元操作符 : 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个Selector对象，一个Object。
	 * @result
	 * 结果<br>
	 * 如果 Selector.isSelected 为 true，那么返回 Selector 的 selectedValue。<br>
	 * 否则返回 Object。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler(":");
		
		Selector selector = new Selector(true, "1");
		assertEquals("1", handler.doEvaluate(selector, "2"));
		
		selector = new Selector(false, "1");
		assertEquals("2", handler.doEvaluate(selector, "2"));
		
	}
}
