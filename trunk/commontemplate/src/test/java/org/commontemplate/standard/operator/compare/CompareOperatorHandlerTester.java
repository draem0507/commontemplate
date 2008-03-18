package org.commontemplate.standard.operator.compare;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
/**
 * CompareOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class CompareOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new CompareOperatorHandler();
	}
	
	/**
	 * 对2元操作符 <=> 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个对象。
	 * @result
	 * 结果<br>
	 * 返回两个对象的比较结果。<br>
	 * 相等返回0, 大于返回1, 小于返回-1
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		assertEvaluation(new Integer(3), new Integer(3), new Integer(0));
		assertEvaluation(new Integer(3), new Integer(2), new Integer(1));
		assertEvaluation(new Integer(2), new Integer(3), new Integer(-1));
		assertEvaluation("b", "a", new Integer(1));	
	}
}
