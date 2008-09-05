package org.commontemplate.standard.operator.object;

import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.standard.operator.UnaryOperatorHandlerTester;
import org.commontemplate.standard.operator.collection.UserInfo;

/**
 * ClassStaticPropertyOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class ClassStaticPropertyOperatorHandlerTester extends UnaryOperatorHandlerTester {

	protected UnaryOperatorHandler newUnaryOperatorHandler() {
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
		assertEvaluation(UserInfo.class.getName() + ".TEST_CONST", UserInfo.TEST_CONST);
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
		assertEvaluation(UserInfo.class.getName() + ".country", UserInfo.getCountry());
		assertEvaluation(UserInfo.class.getName() + ".country.toLowerCase", UserInfo.getCountry().toLowerCase());
		assertEvaluation(UserInfo.class.getName() + ".country.toLowerCase.toUpperCase", UserInfo.getCountry().toLowerCase().toUpperCase());
		assertEvaluation(UserInfo.class.getName() , UserInfo.class);
	}
}
