package org.commontemplate.standard.operator.object;

import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.standard.operator.UnaryOperatorHandlerTester;
/**
 * ClassPackageOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class ClassPackageOperatorHandlerTester extends UnaryOperatorHandlerTester {

	protected UnaryOperatorHandler newUnaryOperatorHandler() {
		return new ClassPackageOperatorHandler();
	}
	/**
	 * 测试 ClassPackageOperatorHandler。
	 * @condition
	 * 条件<br>
	 * 一个class的名字。
	 * @result
	 * 结果<br>
	 * 应该会找到该class。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
				
		assertEvaluationResultNotNull("org.commontemplate.standard.operator.object.ClassPackageOperatorHandlerTester");
		assertEvaluationResultNull("xxxxxxx");
	}
	
}
