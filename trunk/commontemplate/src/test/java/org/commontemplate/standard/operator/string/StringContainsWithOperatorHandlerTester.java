package org.commontemplate.standard.operator.string;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
/**
 * StringContainsWithOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class StringContainsWithOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new StringContainsWithOperatorHandler();
	}
	/**
	 * 对2元操作符 *= 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个string 对象。
	 * @result
	 * 结果<br>
	 * 返回第一个string 是否包含第二个string的结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		assertEvaluation("Hello World!", "orld!", Boolean.TRUE);
		assertEvaluation("Hello World!", " orld", Boolean.FALSE);
		assertEvaluation("Hello World!", "orld", Boolean.TRUE);
	}
}
