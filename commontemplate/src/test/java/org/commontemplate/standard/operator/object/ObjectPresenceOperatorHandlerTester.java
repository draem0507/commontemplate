package org.commontemplate.standard.operator.object;

import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.standard.operator.UnaryOperatorHandlerTester;
/**
 * ObjectPresenceOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class ObjectPresenceOperatorHandlerTester extends UnaryOperatorHandlerTester {

	protected UnaryOperatorHandler newUnaryOperatorHandler() {
		return new ObjectPresenceOperatorHandler();
	}
	/**
	 * 对一元操作符 ？ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个Object。
	 * @result
	 * 结果<br>
	 * 根据Object是否为空，而返回boolean类型。。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		assertEvaluation(null, Boolean.FALSE);
		// 只要obj不等于null就可以
		assertEvaluation(this, Boolean.TRUE);		
	}
}
