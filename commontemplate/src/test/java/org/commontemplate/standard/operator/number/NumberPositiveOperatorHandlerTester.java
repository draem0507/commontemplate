package org.commontemplate.standard.operator.number;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * NumberPositiveOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class NumberPositiveOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;

	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	/**
	 * 对一元操作符　+ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个数字。
	 * @result
	 * 结果<br>
	 * 仍然得到该数字。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{

		UnaryOperatorHandler handler = operatorHandlerProvider.getUnaryOperatorHandler("+");

		Number number = new Integer(10);
		assertEquals(10, ((Number)handler.doEvaluate(number)).intValue());

		number = new Integer(-10);
		assertEquals(-10, ((Number)handler.doEvaluate(number)).intValue());

		number = new Double(11.1);
		assertTrue(11.1 == ((Number)handler.doEvaluate(number)).doubleValue());

		number = new Double(-11.1);
		assertTrue(-11.1 == ((Number)handler.doEvaluate(number)).doubleValue());

	}
}
