package org.commontemplate.standard.operator.number;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
/**
 * BitXorOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class BitXorOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new BitXorOperatorHandler();
	}
	
	/**
	 * 对2元操作符 ^ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个integer 对象。
	 * @result
	 * 结果<br>
	 * 返回两个integer 对象的异或结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		assertEvaluation(Integer.valueOf("1"), Integer.valueOf("2"), Integer.valueOf("3"));
		assertEvaluation(Integer.valueOf("7"), Integer.valueOf("8"), Integer.valueOf("15"));
		assertEvaluation(Integer.valueOf("10"), Integer.valueOf("20"), Integer.valueOf("30"));
		
	}
}
