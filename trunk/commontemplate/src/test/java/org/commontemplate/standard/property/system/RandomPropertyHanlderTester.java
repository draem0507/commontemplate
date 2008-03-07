package org.commontemplate.standard.property.system;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * 对RandomPropertyHanlder的测试。
 * @author YanRong
 *
 */
public class RandomPropertyHanlderTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;

	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	/**
	 * 对RandomPropertyHanlder的测试。<br>
	 * @condition
	 * 条件<br>
	 * 得到 RandomPropertyHanlder。
	 * @result
	 * 结果<br>
	 * 得到一个Double对象的随机数。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		UnaryOperatorHandler handler = operatorHandlerProvider.getUnaryOperatorHandler(".");
		Object obj = handler.doEvaluate("random");
		
		assertTrue(obj instanceof Double);
		
	}
}
