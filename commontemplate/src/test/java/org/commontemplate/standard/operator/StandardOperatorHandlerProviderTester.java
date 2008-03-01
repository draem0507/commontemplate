package org.commontemplate.standard.operator;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * StandardOperatorHandlerProviderTester 测试
 *
 * @author Yan Rong
 *
 */
public class StandardOperatorHandlerProviderTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;

	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}

	/**
	 * 测试是否能正确地构造操作符。
	 * @condition
	 * 条件<br>
	 * 在配置文件中设置加号操作符。
	 * @result
	 * 结果<br>
	 * 能够正确地读取属性。
	 */
	public void testGetBinaryOperatorHandler() {

		// 因为 +号 是 SpecialBinaryOperatorHandler，所以直接转换成这个类
		BinaryOperatorHandlerChain handler =
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");

		// 验证从配置文件中读取的是否正确
		assertEquals(true, handler.isAssociativeLaw());
		assertEquals(true, handler.isCommutativeLaw());
	}

}
