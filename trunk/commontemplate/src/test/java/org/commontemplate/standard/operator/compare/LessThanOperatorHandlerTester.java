package org.commontemplate.standard.operator.compare;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
/**
 * LessThanOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class LessThanOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new LessThanOperatorHandler();
	}
	
	/**
	 * 对2元操作符 < 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个对象。
	 * @result
	 * 结果<br>
	 * 返回两个对象的比较结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
				
		assertEvaluation(new Integer(3), new Integer(3), Boolean.FALSE);
		assertEvaluation(new Integer(3), new Integer(2), Boolean.FALSE);
		assertEvaluation(new Integer(2), new Integer(3), Boolean.TRUE);
		assertEvaluation("b", "a", Boolean.FALSE);	
	}
}
