package org.commontemplate.standard.operator.number;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.standard.operator.UnaryOperatorHandlerTester;
/**
 * NumberNegativeOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class NumberNegativeOperatorHandlerTester extends UnaryOperatorHandlerTester {

	protected UnaryOperatorHandler newUnaryOperatorHandler() {
		return new NumberNegativeOperatorHandler();
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

		assertEvaluation(new Integer(10), new Integer(-10));		
		assertEvaluation(new Integer(-10), new Integer(10));
		
		assertEvaluation(Short.valueOf("10"), Short.valueOf("-10"));
		assertEvaluation(Short.valueOf("-10"), Short.valueOf("10"));
		
		assertEvaluation(new Long(10), new Long(-10));		
		assertEvaluation(new Long(-10), new Long(10));
		
		assertEvaluation(new Float((float)10.11), new Float((float)-10.11));		
		assertEvaluation(new Float((float)-10.11), new Float((float)10.11));
		
		assertEvaluation(new Double((double)10.10), new Double((double)-10.10));		
		assertEvaluation(new Double((double)-10.10), new Double((double)10.10));
		
		assertEvaluation(new BigDecimal((double)10.10), new BigDecimal((double)-10.10));		
		assertEvaluation(new BigDecimal((double)-10.10), new BigDecimal((double)10.10));
		
		assertEvaluation(new BigInteger(String.valueOf(10)), new BigInteger(String.valueOf(-10)));		
		assertEvaluation(new BigInteger(String.valueOf(-10)), new BigInteger(String.valueOf(10)));
		
	}
}
