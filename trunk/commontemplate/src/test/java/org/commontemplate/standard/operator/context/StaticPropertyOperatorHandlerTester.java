package org.commontemplate.standard.operator.context;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * StaticPropertyOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class StaticPropertyOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	UnaryOperatorHandler handler;

	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
		handler = operatorHandlerProvider.getUnaryOperatorHandler(".");
	}
	/**
	 * 对一元操作符　. 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个属性名称。
	 * @result
	 * 结果<br>
	 * 得到处理该属性的handler。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		String properties[] = new String[]{"system", "engine", "now", "random", "uuid"};
		
		for(int i = 0, m = properties.length; i < m; i++) {
			
			Object obj = handler.doEvaluate(properties[i]);
			assertNotNull(obj);
		}
		
	}
}
