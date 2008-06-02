package org.commontemplate.standard.operator.string;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;

public class StringAbbreviateOperatorHandlerTester extends
		BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		
		return new StringAbbreviateOperatorHandler();
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为 "abcdefghijk" 对象， Integer(2) 对象。
	 * @result
	 * 结果<br>
	 * 返回Integer的整型数值所表示的字符。
	 * @throws Exception
	 */
	public void testAbbreviate() throws Exception {
		
		assertEvaluation("abcdefghijk", new Integer(2), "a...");
	}
	
	/**
	 * 对2元操作符 % 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为 "abcdefghijk" 对象， Integer(5) 对象。
	 * @result
	 * 结果<br>
	 * 返回Integer的整型数值所表示的字符。
	 * @throws Exception
	 */
	public void testAbbreviate2() throws Exception {
		
		assertEvaluation("abcdefghijk", new Integer(5), "ab...");
	}
	
}
