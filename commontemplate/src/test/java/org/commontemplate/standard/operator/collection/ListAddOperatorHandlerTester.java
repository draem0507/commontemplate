package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Vector;

import junit.framework.TestCase;

import org.commontemplate.config.BinaryOperatorHandler;
/**
 * ListAddOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class ListAddOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	public void setUp() {

		handler = new ListAddOperatorHandler();
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个collection对象。
	 * @result
	 * 结果<br>
	 * 返回两个collection对象的合的新的collection对象。
	 * @throws Exception
	 */
	public void testDoEvaluateForArrayList() throws Exception{
		
		Collection c1 = new ArrayList();
		c1.add("1");
		c1.add("2");
		
		Collection c2 = new ArrayList();
		c2.add("3");
		c2.add("4");
		
		Collection c3 = (Collection) handler.doEvaluate(c1, c2);
		
		assertTrue(c3 instanceof ArrayList);
		assertEquals(4, c3.size());
		
		Collection c4 = new ArrayList();
		c4.addAll(c1);
		c4.addAll(c2);
		assertEquals(c4, c3);
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个collection对象。
	 * @result
	 * 结果<br>
	 * 返回两个collection对象的合的新的collection对象。
	 * @throws Exception
	 */
	public void testDoEvaluateForHashSet() throws Exception{
		
		Collection c1 = new HashSet();
		c1.add("1");
		c1.add("2");
		
		Collection c2 = new HashSet();
		c2.add("3");
		c2.add("4");
		
		Collection c3 = (Collection) handler.doEvaluate(c1, c2);
		
		assertTrue(c3 instanceof HashSet);
		assertEquals(4, c3.size());
		
		Collection c4 = new HashSet();
		c4.addAll(c1);
		c4.addAll(c2);
		assertEquals(c4, c3);
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个collection对象。
	 * @result
	 * 结果<br>
	 * 返回两个collection对象的合的新的collection对象。
	 * @throws Exception
	 */
	public void testDoEvaluateForLinkedList() throws Exception{
		
		Collection c1 = new LinkedList();
		c1.add("1");
		c1.add("2");
		
		Collection c2 = new LinkedList();
		c2.add("3");
		c2.add("4");
		
		Collection c3 = (Collection) handler.doEvaluate(c1, c2);
		
		assertTrue(c3 instanceof LinkedList);
		assertEquals(4, c3.size());
		
		Collection c4 = new LinkedList();
		c4.addAll(c1);
		c4.addAll(c2);
		assertEquals(c4, c3);
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个collection对象。
	 * @result
	 * 结果<br>
	 * 返回两个collection对象的合的新的collection对象。
	 * @throws Exception
	 */
	public void testDoEvaluateForVector() throws Exception{
		
		Collection c1 = new Vector();
		c1.add("1");
		c1.add("2");
		
		Collection c2 = new Vector();
		c2.add("3");
		c2.add("4");
		
		Collection c3 = (Collection) handler.doEvaluate(c1, c2);
		
		assertTrue(c3 instanceof Vector);
		assertEquals(4, c3.size());
		
		Collection c4 = new Vector();
		c4.addAll(c1);
		c4.addAll(c2);
		assertEquals(c4, c3);
	}
}
