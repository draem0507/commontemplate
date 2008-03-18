package org.commontemplate.standard.operator.number;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
/**
 * BitOrOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class BitOrOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new BitOrOperatorHandler();
	}
	
	/**
	 * 对2元操作符 | 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个integer 对象。
	 * @result
	 * 结果<br>
	 * 返回两个integer 对象的或操作结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		assertEvaluation(new Integer(1), new Integer(2), new Integer(3));
		assertEvaluation(new Integer(2), new Integer(3), new Integer(3));
		
	}
}
