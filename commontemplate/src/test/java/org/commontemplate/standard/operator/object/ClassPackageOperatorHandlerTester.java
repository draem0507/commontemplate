package org.commontemplate.standard.operator.object;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * ClassPackageOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class ClassPackageOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;

	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	/**
	 * 测试 ClassPackageOperatorHandler。
	 * @condition
	 * 条件<br>
	 * 一个class的名字。
	 * @result
	 * 结果<br>
	 * 应该会找到该class。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		UnaryOperatorHandler handler = operatorHandlerProvider.getUnaryOperatorHandler("&");
		
		Class clazz = (Class) handler.doEvaluate("org.commontemplate.standard.operator.object.ClassPackageOperatorHandlerTester");		
		assertNotNull(clazz);
		
		clazz = (Class) handler.doEvaluate("xxxxxxxx");		
		assertNull(clazz);
	}
	
}
