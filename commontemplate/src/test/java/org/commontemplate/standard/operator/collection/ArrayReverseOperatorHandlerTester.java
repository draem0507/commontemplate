package org.commontemplate.standard.operator.collection;

import junit.framework.TestCase;

import org.commontemplate.config.UnaryOperatorHandler;
/**
 * ArrayReverseOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class ArrayReverseOperatorHandlerTester extends TestCase {

	UnaryOperatorHandler handler;

	public void setUp() {
		handler = new ArrayReverseOperatorHandler();
	}
	/**
	 * 对一元操作符　- 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个对象数组。
	 * @result
	 * 结果<br>
	 * 得到倒序的数组。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		String strs[] = new String[]{"1", "2", "3", "4"};
		Object reversStrs[] = (Object[]) handler.doEvaluate(strs);
		
		for(int i = 0, m = strs.length; i < m; i++) {
			
			assertEquals(strs[i], reversStrs[m -i -1]);
		}
		
	}
}
