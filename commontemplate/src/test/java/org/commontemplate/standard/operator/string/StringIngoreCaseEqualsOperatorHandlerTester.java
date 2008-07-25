package org.commontemplate.standard.operator.string;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
/**
 * StringIngoreCaseEqualsOperatorHanlder 的测试。
 * @author YanRong
 *
 */
public class StringIngoreCaseEqualsOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new StringIgnoreCaseEqualsOperatorHandler();
	}
	
	/**
	 * 对2元操作符 ~= 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个string 对象。
	 * @result
	 * 结果<br>
	 * 返回两个 string 对象的比较结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		assertEvaluation("abc  ", "  abc", Boolean.TRUE);
		assertEvaluation("Abc  ", "  aBc", Boolean.TRUE);
		assertEvaluation("abe  ", "  abc", Boolean.FALSE);
		
	}
}
