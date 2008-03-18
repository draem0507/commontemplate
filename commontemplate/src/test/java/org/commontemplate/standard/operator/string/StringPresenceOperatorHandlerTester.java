package org.commontemplate.standard.operator.string;

import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.standard.operator.UnaryOperatorHandlerTester;
/**
 * StringPresenceOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class StringPresenceOperatorHandlerTester extends UnaryOperatorHandlerTester {

	protected UnaryOperatorHandler newUnaryOperatorHandler() {
		return new StringPresenceOperatorHandler();
	}
	/**
	 * 对一元操作符 ？ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个string。
	 * @result
	 * 结果<br>
	 * 根据string是否为空，以及length是否大于零而返回boolean类型。。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		assertEvaluation(null, Boolean.FALSE);
		assertEvaluation("", Boolean.FALSE);
		assertEvaluation("   ", Boolean.FALSE);
		assertEvaluation("1", Boolean.TRUE);
		
	}
		
}
