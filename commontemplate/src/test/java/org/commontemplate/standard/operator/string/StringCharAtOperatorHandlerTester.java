package org.commontemplate.standard.operator.string;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
/**
 * StringCharAtOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class StringCharAtOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new StringCharAtOperatorHandler();
	}
	
	/**
	 * 对2元操作符 [ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为string 对象， Integer 对象。
	 * @result
	 * 结果<br>
	 * 返回Integer的整型数值所表示的字符。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		assertEvaluation("abcde", new Integer(3), new Character('d'));
	}
}
