package org.commontemplate.standard.operator.number;

import junit.framework.TestCase;

import org.commontemplate.config.BinaryOperatorHandler;
/**
 * NumberPowerOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class NumberPowerOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	public void setUp() {

		handler = new NumberPowerOperatorHandler();
	}
	
	/**
	 * 对2元操作符 ** 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number 对象。
	 * @result
	 * 结果<br>
	 * 返回两个Number 对象的幂运算的结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		assertEquals(4, ((Double)handler.doEvaluate(Integer.valueOf("2"), Integer.valueOf("2"))).doubleValue(), 0.00001);
		assertEquals(8, ((Double)handler.doEvaluate(Integer.valueOf("2"), Integer.valueOf("3"))).doubleValue(), 0.00001);
	}
}
