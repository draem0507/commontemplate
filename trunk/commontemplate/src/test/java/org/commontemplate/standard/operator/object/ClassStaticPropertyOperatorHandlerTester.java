package org.commontemplate.standard.operator.object;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
import org.commontemplate.standard.operator.collection.UserInfo;
/**
 * ClassStaticPropertyOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class ClassStaticPropertyOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new ClassStaticPropertyOperatorHandler();
	}
	
	/**
	 * 对2元操作符 . 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个Class和静态变量的名字。
	 * @result
	 * 结果<br>
	 * 返回静态变量的数值。
	 * @throws Exception
	 */
	public void testDoEvaluateStaticField() throws Exception{
		
		assertEvaluation(UserInfo.class, 
				"TEST_CONST", UserInfo.TEST_CONST);
	}
	
	/**
	 * 对2元操作符 . 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个Class和静态变量的名字。
	 * @result
	 * 结果<br>
	 * 返回静态变量的数值。
	 * @throws Exception
	 */
	public void testDoEvaluateStaticMethod() throws Exception{
		
		assertEvaluation(UserInfo.class, 
				"country", UserInfo.getCountry());
	}
}