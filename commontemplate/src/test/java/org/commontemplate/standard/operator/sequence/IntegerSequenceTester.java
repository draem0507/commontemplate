package org.commontemplate.standard.operator.sequence;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
 * IntegerSequence 的测试。
 * @author YanRong
 *
 */
public class IntegerSequenceTester extends TestCase{

	IntegerSequence integerSequence;
	List list;
	
	/**
	 * 对IntegerSequence的升序的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个整数。
	 * @result
	 * 结果<br>
	 * 返回一个数字序列对象。
	 * @throws Exception
	 */
	public void testASCSortConstructor() {
		
		integerSequence = new IntegerSequence(2, 10);
		
		assertEquals(2, integerSequence.getBegin());
		assertEquals(10, integerSequence.getEnd());
		assertEquals(9, integerSequence.size());
		assertTrue(integerSequence.isAsc());
	}
	
	/**
	 * 对IntegerSequence的降序的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个整数。
	 * @result
	 * 结果<br>
	 * 返回一个数字序列对象。
	 * @throws Exception
	 */
	public void testDESCSortConstructor() {
		
		integerSequence = new IntegerSequence(10, 2);
		
		assertEquals(10, integerSequence.getBegin());
		assertEquals(2, integerSequence.getEnd());
		assertEquals(9, integerSequence.size());
		assertFalse(integerSequence.isAsc());
	}
	
	/**
	 * 对IntegerSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个IntegerSequence对象。
	 * @result
	 * 结果<br>
	 * 永远不为空。
	 * @throws Exception
	 */
	public void testIsEmpty() {
		
		list = new IntegerSequence(2, 10);
		
		assertFalse(list.isEmpty());
	}
	
	/**
	 * 对IntegerSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个IntegerSequence对象。
	 * @result
	 * 结果<br>
	 * List中应该包含序列范围内的数字。
	 * @throws Exception
	 */
	public void testContains() {
		
		list = new IntegerSequence(2, 10);
		
		for(int i = 2; i <= 10; i++) {
			assertTrue(list.contains(new Integer(i)));
		}
		
		assertFalse(list.contains(new Integer(1)));
		assertFalse(list.contains(new Integer(11)));
		
		list = new IntegerSequence(10, 2);
		
		for(int i = 2; i <= 10; i++) {
			assertTrue(list.contains(new Integer(i)));
		}
		
		assertFalse(list.contains(new Integer(1)));
		assertFalse(list.contains(new Integer(11)));
	}
	
	/**
	 * 对IntegerSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个IntegerSequence对象。
	 * @result
	 * 结果<br>
	 * 应该得到正确的数组。
	 * @throws Exception
	 */
	public void testToArray() {
		
		list = new IntegerSequence(2, 10);
		Integer[] expectArray = new Integer[]{new Integer(2),
												new Integer(3),
												new Integer(4),
												new Integer(5),
												new Integer(6),
												new Integer(7),
												new Integer(8),
												new Integer(9),
												new Integer(10)};
		Integer[] integers = (Integer[]) list.toArray();
		
		for(int i = 0, m = expectArray.length; i < m; i++) {
			assertEquals(expectArray[i], integers[i]);
		}
		
		list = new IntegerSequence(10, 2);
		expectArray = new Integer[]{new Integer(10),
												new Integer(9),
												new Integer(8),
												new Integer(7),
												new Integer(6),
												new Integer(5),
												new Integer(4),
												new Integer(3),
												new Integer(2)};
		integers = (Integer[]) list.toArray();
		for(int i = 0, m = expectArray.length; i < m; i++) {
			assertEquals(expectArray[i], integers[i]);
		}
	}
	
	/**
	 * 对IntegerSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个IntegerSequence对象。
	 * @result
	 * 结果<br>
	 * 应该得到正确的数组。
	 * @throws Exception
	 */
	public void testToArray2() {
		
		list = new IntegerSequence(2, 10);
		Integer[] expectArray = new Integer[]{new Integer(2),
												new Integer(3),
												new Integer(4),
												new Integer(5),
												new Integer(6),
												new Integer(7),
												new Integer(8),
												new Integer(9),
												new Integer(10)};
		Integer[] integers = (Integer[]) list.toArray(new Integer[list.size()]);
		
		for(int i = 0, m = expectArray.length; i < m; i++) {
			assertEquals(expectArray[i], integers[i]);
		}
		
		list = new IntegerSequence(10, 2);
		expectArray = new Integer[]{new Integer(10),
												new Integer(9),
												new Integer(8),
												new Integer(7),
												new Integer(6),
												new Integer(5),
												new Integer(4),
												new Integer(3),
												new Integer(2)};
		integers = (Integer[]) list.toArray(new Integer[list.size()]);
		for(int i = 0, m = expectArray.length; i < m; i++) {
			assertEquals(expectArray[i], integers[i]);
		}
		
		list = new IntegerSequence(10, 2);
		expectArray = new Integer[]{new Integer(10),
												new Integer(9),
												new Integer(8),
												new Integer(7),
												new Integer(6),
												new Integer(5),
												new Integer(4),
												new Integer(3),
												new Integer(2)};
		// 传入一个size超出界限的array
		integers = (Integer[]) list.toArray(new Integer[list.size()+10]);
		for(int i = 0, m = expectArray.length; i < m; i++) {
			assertEquals(expectArray[i], integers[i]);
		}
	}
	
	/**
	 * 对IntegerSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个IntegerSequence对象, 一个Collection 对象。
	 * @result
	 * 结果<br>
	 * 应该得到正确的IntegerSequence对Collection对象的包含关系。
	 * @throws Exception
	 */
	public void testContainsAll() {
		
		list = new IntegerSequence(2, 10);
		List intList = new ArrayList();
		intList.add(new Integer(5));
		intList.add(new Integer(8));
		
		assertTrue(list.containsAll(intList));
		
		intList = new ArrayList();
		intList.add(new Integer(1));
		intList.add(new Integer(11));
		
		assertFalse(list.containsAll(intList));
		
		intList = new ArrayList();
		intList.add(new Integer(5));
		intList.add(new Integer(11));
		
		assertFalse(list.containsAll(intList));
	}
	
	/**
	 * 对IntegerSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个IntegerSequence对象。
	 * @result
	 * 结果<br>
	 * 应该得到正确的对象。
	 * @throws Exception
	 */
	public void testGet() {
		
		list = new IntegerSequence(2, 10);
		
		for(int i = 0, c = 2, m = list.size(); i < m; i++, c++) {
			
			assertEquals(new Integer(c), (Integer) list.get(i)); 
		}
		
		list = new IntegerSequence(10, 2);
		
		for(int i = 0, c = 10, m = list.size(); i < m; i++, c--) {
			
			assertEquals(new Integer(c), (Integer) list.get(i)); 
		}
	}
	
	/**
	 * 对IntegerSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个IntegerSequence对象。
	 * @result
	 * 结果<br>
	 * 应该根据对象得到正确的索引号。
	 * @throws Exception
	 */
	public void testIndexOf() {
		
		list = new IntegerSequence(2, 10);
		
		for(int i = 0, c = 2, m = list.size(); i < m; i++, c++) {
			
			assertEquals(i, list.indexOf(new Integer(c))); 
		}
		
		list = new IntegerSequence(10, 2);
		
		for(int i = 0, c = 10, m = list.size(); i < m; i++, c--) {
			
			assertEquals(i, list.indexOf(new Integer(c))); 
		}
	}
	
}
