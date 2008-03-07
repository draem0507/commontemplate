package org.commontemplate.standard.operator.number;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * NumberNegativeOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class NumberNegativeOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;

	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	/**
	 * 对一元操作符　- 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个数字。
	 * @result
	 * 结果<br>
	 * 得到该数字的负数。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		UnaryOperatorHandler handler = operatorHandlerProvider.getUnaryOperatorHandler("-");
		
		Number number = new Integer(10);
		assertEquals(-10, ((Number)handler.doEvaluate(number)).intValue());
		
		number = new Integer(-10);
		assertEquals(10, ((Number)handler.doEvaluate(number)).intValue());
		
		number = new Short((short)10);
		assertEquals(-10, ((Number)handler.doEvaluate(number)).shortValue());
		
		number = new Short((short)-10);
		assertEquals(10, ((Number)handler.doEvaluate(number)).shortValue());
		
		number = new Long(10);
		assertEquals(-10, ((Number)handler.doEvaluate(number)).longValue());
		
		number = new Long(-10);
		assertEquals(10, ((Number)handler.doEvaluate(number)).longValue());
		
		number = new Float((float)10.11);
		assertTrue((float)-10.11 == ((Number)handler.doEvaluate(number)).floatValue());
		
		number = new Float((float)-10.10);
		assertTrue((float)10.10 == ((Number)handler.doEvaluate(number)).floatValue());
		
		number = new Double((double)10.10);
		assertTrue((double)-10.10 == ((Number)handler.doEvaluate(number)).doubleValue());
		
		number = new Double((double)-10.10);
		assertTrue((double)10.10 == ((Number)handler.doEvaluate(number)).doubleValue());
		
		number = new BigDecimal((double)10.10);
		assertTrue((double)-10.10 == ((Number)handler.doEvaluate(number)).doubleValue());
		
		number = new BigDecimal((double)-10.10);
		assertTrue((double)10.10 == ((Number)handler.doEvaluate(number)).doubleValue());
		
		number = new BigInteger(String.valueOf(10));
		assertEquals(-10, ((Number)handler.doEvaluate(number)).intValue());
		
		number = new BigInteger(String.valueOf(-10));
		assertEquals(10, ((Number)handler.doEvaluate(number)).intValue());
		
	}
}
