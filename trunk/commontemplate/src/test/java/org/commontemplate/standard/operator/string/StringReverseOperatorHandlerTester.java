package org.commontemplate.standard.operator.string;

import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.standard.operator.UnaryOperatorHandlerTester;
/**
 * StringReverseOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class StringReverseOperatorHandlerTester extends UnaryOperatorHandlerTester {

	protected UnaryOperatorHandler newUnaryOperatorHandler() {
		return new StringReverseOperatorHandler();
	}
	/**
	 * 对一元操作符　- 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个字符串。
	 * @result
	 * 结果<br>
	 * 得到倒序的字符串。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		assertEvaluation("abcd1234", "4321dcba");
		
	}
}
