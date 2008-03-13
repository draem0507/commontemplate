package org.commontemplate.standard.operator.number;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * BitNotOperatorHandler 的测试。<br>
 * 补码取反的操作。
 * @author YanRong
 *
 */
public class BitNotOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;

	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	/**
	 * 测试补码取反的操作。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个整数。
	 * @result
	 * 结果<br>
	 * 得到该整数的补码取反后的整数。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{

		UnaryOperatorHandler handler = operatorHandlerProvider.getUnaryOperatorHandler("~");

		Integer integer = new Integer(5);
		Integer resultInt = (Integer) handler.doEvaluate(integer);
		assertEquals(-6, resultInt.intValue());

		integer = new Integer(7);
		resultInt = (Integer) handler.doEvaluate(integer);
		assertEquals(-8, resultInt.intValue());
	}

}
