package org.commontemplate.standard.property.system;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * UuidPropertyHandler 的测试。
 * @author YanRong
 *
 */
public class UuidPropertyHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;

	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	/**
	 * 对UuidPropertyHandler的测试。<br>
	 * @condition
	 * 条件<br>
	 * 得到 UuidPropertyHandler。
	 * @result
	 * 结果<br>
	 * 得到UUID这个对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		UnaryOperatorHandler handler = operatorHandlerProvider.getUnaryOperatorHandler(".");
		Object obj = handler.doEvaluate("uuid");
		
		assertTrue(obj instanceof org.commontemplate.util.coder.UUID);
		
	}
}
