package org.commontemplate.standard.operator.string;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
/**
 * StringRepeat2OperatorHandler 的测试。
 * @author YanRong
 *
 */
public class StringRepeat2OperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new StringRepeat2OperatorHandler();
	}
	
	/**
	 * 对2元操作符 * 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个integer对象, 一个string对象。
	 * @result
	 * 结果<br>
	 * 返回string对象重复N次的结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		assertEvaluation(Integer.valueOf("3"), "a", "aaa");
		assertEvaluation(Integer.valueOf("3"), " a", " a a a");
	}
}
