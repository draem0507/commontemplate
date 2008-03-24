package org.commontemplate.standard.operator.sequence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
	public void testLastIndexOf() {
		
		list = new IntegerSequence(2, 10);
		
		for(int i = 0, c = 2, m = list.size(); i < m; i++, c++) {
			
			assertEquals(i, list.lastIndexOf(new Integer(c))); 
		}
		
		list = new IntegerSequence(10, 2);
		
		for(int i = 0, c = 10, m = list.size(); i < m; i++, c--) {
			
			assertEquals(i, list.lastIndexOf(new Integer(c))); 
		}
	}
	
	/**
	 * 对IntegerSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个IntegerSequence对象。
	 * @result
	 * 结果<br>
	 * 应该根据对象得到一个非空的ListIterator对象。
	 * @throws Exception
	 */
	public void testListIterator() {
		
		list = new IntegerSequence(2, 10);
		
		ListIterator it = list.listIterator();
		assertNotNull(it);
	}
	
	/**
	 * 对IntegerSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个IntegerSequence对象。
	 * @result
	 * 结果<br>
	 * 应该根据对象得到一个非空的ListIterator对象。
	 * @throws Exception
	 */
	public void testListIetrator2() {
		
		list = new IntegerSequence(2, 10);
		
		ListIterator it = list.listIterator(2);
		assertNotNull(it);
	}
	
	/**
	 * 对IntegerSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个IntegerSequence对象。
	 * @result
	 * 结果<br>
	 * 应该根据对象得到正确的SubList对象。
	 * @throws Exception
	 */
	public void testSubList() {
		
		list = new IntegerSequence(2, 10);
		
		List subList = list.subList(1, 3);
		
		assertEquals(4, subList.size());
		Integer[] expectInt = new Integer[]{new Integer(3), new Integer(4), new Integer(5), new Integer(6)};
		for(int i = 0, m = expectInt.length; i < m; i++) {
			assertEquals(expectInt[i], subList.get(i));
		}
		
		list = new IntegerSequence(10, 2);
		subList = list.subList(1, 3);
		assertEquals(4, subList.size());
		expectInt = new Integer[]{new Integer(9), new Integer(8), new Integer(7), new Integer(6)};
		for(int i = 0, m = expectInt.length; i < m; i++) {
			assertEquals(expectInt[i], subList.get(i));
		}
	}
	
	/**
	 * 对IntegerSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个IntegerSequence对象。
	 * @result
	 * 结果<br>
	 * 应该根据对象得到正确的toString结果。
	 * @throws Exception
	 */
	public void testToString() {
		
		list = new IntegerSequence(2, 10);
		String s = list.toString();
		assertEquals("[2,3,4,5,6,7,8,9,10]", s);
		
		list = new IntegerSequence(10, 2);
		s = list.toString();
		assertEquals("[10,9,8,7,6,5,4,3,2]", s);
	}
	
	/**
	 * 对IntegerSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个IntegerSequence对象。
	 * @result
	 * 结果<br>
	 * 迭代器应该正常显示。
	 * @throws Exception
	 */
	public void testOnlyIterator() {
		
		list = new IntegerSequence(2, 10);
		Iterator it = list.iterator();
		int index = 2;
		while(it.hasNext()) {
			
			assertEquals(new Integer(index), it.next());
			index++;
		}		
		
		list = new IntegerSequence(10, 2);
		it = list.iterator();
		index = 10;
		while(it.hasNext()) {
			
			assertEquals(new Integer(index), it.next());
			index--;
		}
	}
	
	/**
	 * 对IntegerSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个IntegerSequence对象。
	 * @result
	 * 结果<br>
	 * 迭代器应该正常显示。
	 * @throws Exception
	 */
	public void testIteratorWithListIterator() {
		// ---  升序的测试
		list = new IntegerSequence(2, 10);
		Iterator it = list.listIterator();
		int index = 2;
		while(it.hasNext()) {
			
			assertEquals(new Integer(index), it.next());
			index++;
		}
		
		ListIterator ListIt = list.listIterator();
		index = 0;
		while(ListIt.hasNext()){
			
			assertEquals(index, ListIt.nextIndex());
			index++;
		}
		index = 10;
		while(ListIt.hasPrevious()) {
			
			assertEquals(new Integer(index), ListIt.previous());
			index--;
		}

		// --- 降序的测试
		list = new IntegerSequence(10, 2);
		it = list.listIterator();
		index = 10;
		while(it.hasNext()) {
			
			assertEquals(new Integer(index), it.next());
			index--;
		}
		
		ListIt = list.listIterator();
		index = 0;
		while(ListIt.hasNext()){
			
			assertEquals(index, ListIt.nextIndex());
			index++;
		}
		index = 2;
		while(ListIt.hasPrevious()) {
			
			assertEquals(new Integer(index), ListIt.previous());
			index++;
		}
	}
	
	/**
	 * 对IntegerSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个IntegerSequence对象。
	 * @result
	 * 结果<br>
	 * 迭代器应该正常显示。
	 * @throws Exception
	 */
	public void testIteratorWithListIterator2() {
		
		list = new IntegerSequence(2, 10);
		Iterator it = list.listIterator(1);
		int index = 3;
		while(it.hasNext()) {
			
			assertEquals(new Integer(index), it.next());
			index++;
		}
		
		ListIterator ListIt = list.listIterator();
		index = 0;
		while(ListIt.hasNext()){
			
			assertEquals(index, ListIt.nextIndex());
			index++;
		}
		
		list = new IntegerSequence(10, 2);
		it = list.listIterator(1);
		index = 9;
		while(it.hasNext()) {
			
			assertEquals(new Integer(index), it.next());
			index--;
		}
		
		ListIt = list.listIterator();
		index = 0;
		while(ListIt.hasNext()){
			
			assertEquals(index, ListIt.nextIndex());
			index++;
		}
	}
	
}
