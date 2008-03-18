package org.commontemplate.standard.operator.context;

import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.standard.operator.UnaryOperatorHandlerTester;
/**
 * VariableOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class VariableOperatorHandlerTester extends UnaryOperatorHandlerTester {

	protected UnaryOperatorHandler newUnaryOperatorHandler() {
		return new VariableOperatorHandler();
	}
	/**
	 * 对一元操作符　\ 的测试。<br>
	 * 这个操作符是用来转义的。
	 * @condition
	 * 条件<br>
	 * 输入一个对象。
	 * @result
	 * 结果<br>
	 * 得到原来的对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		assertEvaluation(this, this);		
	}
}
