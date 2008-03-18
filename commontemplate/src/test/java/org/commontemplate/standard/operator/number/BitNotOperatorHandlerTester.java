package org.commontemplate.standard.operator.number;

import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.standard.operator.UnaryOperatorHandlerTester;
/**
 * BitNotOperatorHandler 的测试。<br>
 * 补码取反的操作。
 * @author YanRong
 *
 */
public class BitNotOperatorHandlerTester extends UnaryOperatorHandlerTester {

	protected UnaryOperatorHandler newUnaryOperatorHandler() {
		return new BitNotOperatorHandler();
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
		
		assertEvaluation(new Integer(5), new Integer(-6));
		assertEvaluation(new Integer(7), new Integer(-8));
	}

}
