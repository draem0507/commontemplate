package org.commontemplate.standard.operator.number;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
/**
 * NumberApproximateEqualsOperatorHanlder 的测试。
 * @author YanRong
 *
 */
public class NumberApproximateEqualsOperatorHanlderTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new NumberApproximateEqualsOperatorHanlder();
	}
	
	/**
	 * 对2元操作符 ~= 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个integer 对象。
	 * @result
	 * 结果<br>
	 * 返回两个integer 比较的结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		assertEvaluation(new Integer(10), new Integer(10), Boolean.TRUE);
		assertEvaluation(new Integer(10), new Integer(20), Boolean.FALSE);
		
	}
}
