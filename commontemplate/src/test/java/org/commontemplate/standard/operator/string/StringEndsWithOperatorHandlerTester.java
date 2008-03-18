package org.commontemplate.standard.operator.string;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
/**
 * StringEndsWithOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class StringEndsWithOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new StringEndsWithOperatorHandler();
	}
	
	/**
	 * 对2元操作符 $= 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个string 对象。
	 * @result
	 * 结果<br>
	 * 返回第一个string 的结尾是否是第二个string的结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		assertEvaluation("Hello World!", "orld!", Boolean.TRUE);
		assertEvaluation("Hello World!", " orld", Boolean.FALSE);
		assertEvaluation("Hello World!", "orld", Boolean.FALSE);
	}
}
