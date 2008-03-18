package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.Collection;

import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.standard.operator.UnaryOperatorHandlerTester;
/**
 * CollectionPresenceOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class CollectionPresenceOperatorHandlerTester extends UnaryOperatorHandlerTester {

	protected UnaryOperatorHandler newUnaryOperatorHandler() {
		return new CollectionPresenceOperatorHandler();
	}
	/**
	 * 对一元操作符 ？ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个collection。
	 * @result
	 * 结果<br>
	 * 根据collection是否为空，以及size是否大于零而返回boolean类型。。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		assertEvaluation(null, Boolean.FALSE);
		assertEvaluation(new ArrayList(), Boolean.FALSE);
		
		Collection collection = new ArrayList();
		collection.add("a");
		assertEvaluation(collection, Boolean.TRUE);
	}
}
