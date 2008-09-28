package org.commontemplate.standard.operator.array;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.array.ArraySubOperatorHandler;

import junit.framework.TestCase;
/**
 * ArraySubOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class ArraySubOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	protected void setUp() throws Exception {
		handler = new ArraySubOperatorHandler();
	}
	
	/**
	 * 对二元操作符 [ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个对象数组, 一个Integer的List。
	 * @result
	 * 结果<br>
	 * 根据Integer的List，得到一个子对象数组。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		String[] strs = new String[]{"a", "b", "c", "d", "e"};
		List intList = new ArrayList();
		intList.add(new Integer(1));
		intList.add(new Integer(3));
		
		Object[] result = (Object[]) handler.doEvaluate(strs, intList);
		assertEquals("b", result[0]);
		assertEquals("d", result[1]);
	}
	
}
