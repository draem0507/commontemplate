package org.commontemplate.standard.operator.collection;

import java.util.Arrays;

import org.commontemplate.config.BinaryOperatorHandler;

import junit.framework.TestCase;
/**
 * ListGetterOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class ListGetterOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	protected void setUp() throws Exception {
		handler = new ListGetterOperatorHandler();
	}
	
	/**
	 * 对二元操作符 [ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个对象数组, 索引号。
	 * @result
	 * 结果<br>
	 * 得到查询结果的对象。
	 * @throws Exception
	 */
	public void testDoEvaluateASC() throws Exception {
		
		String[] strs = new String[]{"a", "b", "c", "d"};
		String result = (String)handler.doEvaluate(Arrays.asList(strs), new Integer(2));
		assertEquals("c", result);
	}
	
	/**
	 * 对二元操作符 [ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个对象数组, 索引号。
	 * @result
	 * 结果<br>
	 * 得到查询结果的对象。
	 * @throws Exception
	 */
	public void testDoEvaluateDESC() throws Exception {
		
		String[] strs = new String[]{"a", "b", "c", "d"};
		String result = (String)handler.doEvaluate(Arrays.asList(strs), new Integer(-2));
		assertEquals("b", result);
	}
	
	/**
	 * 对二元操作符 [ 的测试，测试越界的情况。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个对象数组, 索引号。
	 * @result
	 * 结果<br>
	 * 得到查询结果的对象为空。
	 * @throws Exception
	 */
	public void testOverBound() throws Exception {
		
		String[] strs = new String[]{"a", "b", "c", "d"};
		String result = (String)handler.doEvaluate(Arrays.asList(strs), new Integer(-4));
		assertNull(result);
		result = (String)handler.doEvaluate(Arrays.asList(strs), new Integer(4));
		assertNull(result);
	}
	
}
