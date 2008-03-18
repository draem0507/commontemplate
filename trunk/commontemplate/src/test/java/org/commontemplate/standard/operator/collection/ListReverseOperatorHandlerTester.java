package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.standard.operator.UnaryOperatorHandlerTester;
/**
 * ListReverseOperatorHandler　的测试。
 * @author YanRong
 *
 */
public class ListReverseOperatorHandlerTester extends UnaryOperatorHandlerTester {

	protected UnaryOperatorHandler newUnaryOperatorHandler() {
		return new ListReverseOperatorHandler();
	}
	/**
	 * 对一元操作符　- 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个list。
	 * @result
	 * 结果<br>
	 * 得到倒序的list。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		List list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("1");
		list.add("2");
		
		List resultList = new ArrayList();
		resultList.add("2");
		resultList.add("1");
		resultList.add("b");
		resultList.add("a");
		
		assertEvaluation(list, resultList);
		
	}
}
