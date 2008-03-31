package org.commontemplate.standard.operator.object;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
import org.commontemplate.standard.operator.collection.UserInfo;
import org.commontemplate.util.Function;
/**
 * ObjectFunctionOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class ObjectFunctionOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new ObjectFunctionOperatorHandler();
	}
	
	/**
	 * 对2元操作符 . 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个Object和Function 对象。
	 * @result
	 * 结果<br>
	 * 正确执行对象的方法。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		UserInfo userInfo = new UserInfo("Tom", 20);
		
		Function function = new Function("getMessage", new ArrayList());
		
		assertEvaluation(userInfo, function, "hello man");
		
		List list = new ArrayList();
		list.add(new Integer(10));
		function = new Function("getMessage", list);
		
		assertEvaluation(userInfo, function, "hello man " + 10);
	}
	
}
