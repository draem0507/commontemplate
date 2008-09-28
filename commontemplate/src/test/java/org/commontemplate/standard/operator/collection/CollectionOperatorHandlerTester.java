package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.commontemplate.config.BinaryOperatorHandler;
/**
 * LiteralListOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class CollectionOperatorHandlerTester extends TestCase {

	private BinaryOperatorHandler handler;

	protected void setUp() throws Exception {
		handler = new CollectionOperatorHandler();
	}

	/**
	 * 对2元操作符 , 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个List对象，一个Object对象。
	 * @result
	 * 结果<br>
	 * 返回一个List对象，包含参数中的Object对象。
	 * @throws Exception
	 */
	public void testDoEvaluateForListObject() throws Exception{

		List list = (List) handler.doEvaluate(new ArrayList(), "a");
		assertTrue(list.contains("a"));
	}

	/**
	 * 对2元操作符 , 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个Object对象，一个Object对象。
	 * @result
	 * 结果<br>
	 * 返回一个List对象，包含参数中的2个Object对象。
	 * @throws Exception
	 */
	public void testDoEvaluateForObjectObject() throws Exception{

		List list = (List) handler.doEvaluate("b", "a");
		assertTrue(list.contains("a"));
		assertTrue(list.contains("b"));
	}

}
