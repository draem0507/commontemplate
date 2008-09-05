package org.commontemplate.standard.operator.object;

import java.util.ArrayList;

import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.standard.operator.UnaryOperatorHandlerTester;
import org.commontemplate.standard.operator.collection.UserInfo;
import org.commontemplate.util.Function;
/**
 * ClassStaticFunctionOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class ClassStaticFunctionOperatorHandlerTester extends UnaryOperatorHandlerTester {

	protected UnaryOperatorHandler newUnaryOperatorHandler() {
		return new ClassStaticFunctionOperatorHandler();
	}

	/**
	 * 对2元操作符 . 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个Class和静态方法的名字。
	 * @result
	 * 结果<br>
	 * 执行静态方法并返回。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{

		assertEvaluation(new Function(UserInfo.class.getName() + ".getCountry", new ArrayList()), UserInfo.getCountry());
		assertEvaluation(new Function(UserInfo.class.getName() + ".getCountry.toLowerCase", new ArrayList()), UserInfo.getCountry().toLowerCase());
		ArrayList args = new ArrayList();
		args.add(new Integer(1));
		args.add(new Integer(3));
		assertEvaluation(new Function(UserInfo.class.getName() + ".getCountry.toLowerCase.substring", args), UserInfo.getCountry().toLowerCase().substring(1,3));
		args.clear();
		args.add("Jason Green");
		args.add(new Integer(1));
		assertEvaluation(new Function(UserInfo.class.getName(),args), new UserInfo("Jason Green",1));
	
	}

}
