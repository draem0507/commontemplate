package org.commontemplate.standard.operator.number;

import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.standard.operator.UnaryOperatorHandlerTester;
/**
 * NumberPositiveOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class NumberPositiveOperatorHandlerTester extends UnaryOperatorHandlerTester {

	protected UnaryOperatorHandler newUnaryOperatorHandler() {
		return new NumberPositiveOperatorHandler();
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

		assertEvaluation(new Integer(10), new Integer(10));
		assertEvaluation(new Integer(-10), new Integer(-10));
		assertEvaluation(new Double(11.1), new Double(11.1));
		assertEvaluation(new Double(-11.1), new Double(-11.1));

	}
}
