package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.sequence.IntegerSequence;

import junit.framework.TestCase;
/**
 * ListSubOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class ListSubOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	protected void setUp() throws Exception {
		handler = new ListSubOperatorHandler();
	}
	
	/**
	 * 对二元操作符 [ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个List数组, 一个IntegerSequence。
	 * @result
	 * 结果<br>
	 * 根据Integer的List, 得到一个子对象List。
	 * @throws Exception
	 */
	public void testDoEvaluateByIntegerSequence() throws Exception{
		
		List list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		
		IntegerSequence sequence = new IntegerSequence(0, 3);
		
		List resultList = (List) handler.doEvaluate(list, sequence);
		
		list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		
		assertEquals(list, resultList);
	
	}
	
	/**
	 * 对二元操作符 [ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个List数组, 一个Integer的List。
	 * @result
	 * 结果<br>
	 * 根据Integer的List, 得到一个子对象List。
	 * @throws Exception
	 */
	public void testDoEvaluateByIntegerList() throws Exception{
		
		List list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		
		List intList = new ArrayList();
		intList.add(new Integer(0));
		intList.add(new Integer(1));
		intList.add(new Integer(2));
		intList.add(new Integer(3));
		
		List resultList = (List) handler.doEvaluate(list, intList);
		
		list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		
		assertEquals(list, resultList);
	
	}
}
