package org.commontemplate.standard.operator;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * UnaryOperatorHandlerChain 一元操作符的测试。
 * @author YanRong
 *
 */
public class UnaryOperatorHandlerChainTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;

	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	
	/**
	 * 测试载入handler。
	 * @condition
	 * 条件<br>
	 * 参数一元操作符的名称。
	 * @result
	 * 结果<br>
	 * 正确地载入handler。
	 *
	 */
	public void testUnaryOperatorHandlerChain() {
		
		String handlers[] = new String[]{"!", "~", "&", "=>", "?", "\\", "+", "-", "[", "."};
		
		UnaryOperatorHandler handler = null;
		
		for(int i = 0, m = handlers.length; i < m; i++) {
			handler = operatorHandlerProvider.getUnaryOperatorHandler(handlers[i]);			
			assertNotNull(handler);
			System.out.println(handlers[i]);
		}
		
	}

}
