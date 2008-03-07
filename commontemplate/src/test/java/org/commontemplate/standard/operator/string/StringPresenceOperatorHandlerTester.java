package org.commontemplate.standard.operator.string;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * StringPresenceOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class StringPresenceOperatorHandlerTester extends TestCase {

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
	 * 参数为一个string。
	 * @result
	 * 结果<br>
	 * 根据string是否为空，以及length是否大于零而返回boolean类型。。
	 * @throws Exception
	 */
	public void testDoEvaluateForString() throws Exception{
		
		UnaryOperatorHandler handler = operatorHandlerProvider.getUnaryOperatorHandler("?");
		
		String str = null;
		assertFalse(((Boolean)handler.doEvaluate(str)).booleanValue());
		
		str = "";
		assertFalse(((Boolean)handler.doEvaluate(str)).booleanValue());
		
		str = "   ";
		assertFalse(((Boolean)handler.doEvaluate(str)).booleanValue());
		
		str = "1";
		assertTrue(((Boolean)handler.doEvaluate(str)).booleanValue());
	}
		
}
