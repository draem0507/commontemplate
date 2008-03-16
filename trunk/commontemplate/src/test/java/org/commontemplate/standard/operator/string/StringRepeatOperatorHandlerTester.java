package org.commontemplate.standard.operator.string;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * StringRepeatOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class StringRepeatOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	
	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个string对象，一个integer对象。
	 * @result
	 * 结果<br>
	 * 返回string对象重复N次的结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("*");
		
		assertEquals("aaa", handler.doEvaluate("a", Integer.valueOf("3")));
		assertEquals(" a a a", handler.doEvaluate(" a", Integer.valueOf("3")));
	}
}
