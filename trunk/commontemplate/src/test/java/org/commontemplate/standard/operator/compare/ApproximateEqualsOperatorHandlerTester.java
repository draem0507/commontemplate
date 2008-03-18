package org.commontemplate.standard.operator.compare;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
/**
 * ApproximateEqualsOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class ApproximateEqualsOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new ApproximateEqualsOperatorHandler();
	}
	
	/**
	 * 对2元操作符 ~= 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个对象。
	 * @result
	 * 结果<br>
	 * 返回两个对象的比较结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		StringBuffer buffer1 = new StringBuffer();
		StringBuffer buffer2 = new StringBuffer();
		
		assertEvaluation(null, null, Boolean.TRUE);
		assertEvaluation(buffer1.append("Abc"), buffer2.append("abc"), Boolean.TRUE);
		assertEvaluation(buffer1.append("Abc "), buffer2.append("Abc"), Boolean.FALSE);
		assertEvaluation(buffer1.append("Abc "), buffer2.append("abc"), Boolean.FALSE);
		assertEvaluation(null, "a", Boolean.FALSE);
		assertEvaluation("a", null, Boolean.FALSE);
		assertEvaluation("b", "a", Boolean.FALSE);
		assertEvaluation(new Integer(1), new Integer(1), Boolean.TRUE);
		assertEvaluation(new Integer(1), new Integer(2), Boolean.FALSE);
		
	}
}
