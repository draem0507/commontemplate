package org.commontemplate.standard.operator.bool;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
/**
 * BooleanXorOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class BooleanXorOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new BooleanXorOperatorHandler();
	}
	
	/**
	 * 对2元操作符 ^ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个boolean 对象。
	 * @result
	 * 结果<br>
	 * 返回两个boolean 对象的异或结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		assertEvaluation(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE);
		
		assertEvaluation(Boolean.FALSE, Boolean.TRUE, Boolean.TRUE);
		
		assertEvaluation(Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
		
		assertEvaluation(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
	}
		
}
