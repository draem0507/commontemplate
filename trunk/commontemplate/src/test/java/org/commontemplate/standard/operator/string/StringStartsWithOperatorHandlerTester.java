package org.commontemplate.standard.operator.string;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
/**
 * StringStartsWithOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class StringStartsWithOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new StringStartsWithOperatorHandler();
	}
	
	/**
	 * 对2元操作符 ^= 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个string 对象。
	 * @result
	 * 结果<br>
	 * 返回第一个string 的开头是否是第二个string的结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		assertEvaluation("Hello World!", "Hell", Boolean.TRUE);
		assertEvaluation("Hello World!", " Hell", Boolean.FALSE);
		assertEvaluation("Hello World!", " hell", Boolean.FALSE);
		
	}
}
