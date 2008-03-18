package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.Collection;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
/**
 * NotContainOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class NotContainOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new NotContainOperatorHandler();
	}
	
	/**
	 * 对2元操作符 !~ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个List 和 一个 Object。
	 * @result
	 * 结果<br>
	 * 返回List 中是否包含 Object 的boolean对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		Collection list = new ArrayList();
		list.add("a");
		list.add("b");
		
		assertEvaluation("a", list, Boolean.FALSE);
		assertEvaluation("c", list, Boolean.TRUE);
		
	}
}
