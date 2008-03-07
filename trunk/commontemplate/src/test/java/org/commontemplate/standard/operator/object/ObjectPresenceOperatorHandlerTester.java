package org.commontemplate.standard.operator.object;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * ObjectPresenceOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class ObjectPresenceOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	
	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	/**
	 * 对一元操作符 ？ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个Object。
	 * @result
	 * 结果<br>
	 * 根据Object是否为空，而返回boolean类型。。
	 * @throws Exception
	 */
	public void testDoEvaluateForMap() throws Exception{
		
		UnaryOperatorHandler handler = operatorHandlerProvider.getUnaryOperatorHandler("?");
		
		Object obj = null;
		assertFalse(((Boolean)handler.doEvaluate(obj)).booleanValue());
		
		// 只要obj不等于null就可以
		obj = this;
		assertTrue(((Boolean)handler.doEvaluate(obj)).booleanValue());
	}
}
