package org.commontemplate.standard.operator.sequence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import junit.framework.TestCase;

/**
 * CharacterSequence 的测试。
 * @author YanRong
 *
 */
public class CharacterSequenceTester extends TestCase{

	CharacterSequence characterSequence;
	List list;

	/**
	 * 对CharacterSequence的升序的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个整数。
	 * @result
	 * 结果<br>
	 * 返回一个数字序列对象。
	 * @throws Exception
	 */
	public void testASCSortConstructor() {

		characterSequence = new CharacterSequence('b', 'e');

		assertEquals('b', characterSequence.getBegin());
		assertEquals('e', characterSequence.getEnd());
		assertEquals(4, characterSequence.size());
		assertTrue(characterSequence.isAsc());
	}

	/**
	 * 对CharacterSequence的降序的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个整数。
	 * @result
	 * 结果<br>
	 * 返回一个数字序列对象。
	 * @throws Exception
	 */
	public void testDESCSortConstructor() {

		characterSequence = new CharacterSequence('e', 'b');

		assertEquals('e', characterSequence.getBegin());
		assertEquals('b', characterSequence.getEnd());
		assertEquals(4, characterSequence.size());
		assertFalse(characterSequence.isAsc());
	}

	/**
	 * 对CharacterSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个characterSequence对象。
	 * @result
	 * 结果<br>
	 * 永远不为空。
	 * @throws Exception
	 */
	public void testIsEmpty() {

		list = new CharacterSequence('b', 'e');

		assertFalse(list.isEmpty());
	}

	/**
	 * 对CharacterSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个characterSequence对象。
	 * @result
	 * 结果<br>
	 * List中应该包含序列范围内的数字。
	 * @throws Exception
	 */
	public void testContains() {

		list = new CharacterSequence('b', 'e');

		for(char c = 'b'; c <= 'e'; c++) {
			assertTrue(list.contains(new Character(c)));
		}

		assertFalse(list.contains(new Character('a')));
		assertFalse(list.contains(new Character('f')));

		list = new CharacterSequence('e', 'b');

		for(char c = 'e'; c <= 'b'; c--) {
			assertTrue(list.contains(new Character(c)));
		}

		assertFalse(list.contains(new Character('a')));
		assertFalse(list.contains(new Character('f')));
	}

	/**
	 * 对CharacterSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个characterSequence对象。
	 * @result
	 * 结果<br>
	 * 应该得到正确的数组。
	 * @throws Exception
	 */
	public void testToArray() {

		list = new CharacterSequence('b', 'e');
		Character[] expectArray = new Character[]{new Character('b'),
												new Character('c'),
												new Character('d'),
												new Character('e')};
		Object[] characters = list.toArray();

		for(int i = 0, m = characters.length; i < m; i++) {
			assertEquals(expectArray[i], characters[i]);
		}

		list = new CharacterSequence('e', 'b');
		expectArray = new Character[]{new Character('e'),
										new Character('d'),
										new Character('c'),
										new Character('b')};
		characters = list.toArray();
		for(int i = 0, m = characters.length; i < m; i++) {
			assertEquals(expectArray[i], characters[i]);
		}
	}

	/**
	 * 对CharacterSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个characterSequence对象。
	 * @result
	 * 结果<br>
	 * 应该得到正确的数组。
	 * @throws Exception
	 */
	public void testToArray2() {

		list = new CharacterSequence('b', 'e');
		Character[] expectArray = new Character[]{new Character('b'),
												new Character('c'),
												new Character('d'),
												new Character('e')};
		Character[] characters = (Character[]) list.toArray(new Character[list.size()]);

		for(int i = 0, m = expectArray.length; i < m; i++) {
			assertEquals(expectArray[i], characters[i]);
		}

		list = new CharacterSequence('e', 'b');
		expectArray = new Character[]{new Character('e'),
										new Character('d'),
										new Character('c'),
										new Character('b')};
		characters = (Character[]) list.toArray(new Character[list.size()]);
		for(int i = 0, m = characters.length; i < m; i++) {
			assertEquals(expectArray[i], characters[i]);
		}

		list = new CharacterSequence('e', 'b');
		expectArray = new Character[]{new Character('e'),
										new Character('d'),
										new Character('c'),
										new Character('b')};
		// 传入一个size超出界限的array
		characters = (Character[]) list.toArray(new Character[list.size()+10]);
		for(int i = 0, m = characters.length; i < m && i < list.size(); i++) {
			assertEquals(expectArray[i], characters[i]);
		}
	}

	/**
	 * 对CharacterSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个characterSequence对象, 一个Collection 对象。
	 * @result
	 * 结果<br>
	 * 应该得到正确的characterSequence对Collection对象的包含关系。
	 * @throws Exception
	 */
	public void testContainsAll() {

		list = new CharacterSequence('b', 'e');
		List charList = new ArrayList();
		charList.add(new Character('c'));
		charList.add(new Character('d'));

		assertTrue(list.containsAll(charList));

		charList = new ArrayList();
		charList.add(new Character('a'));
		charList.add(new Character('f'));

		assertFalse(list.containsAll(charList));

		charList = new ArrayList();
		charList.add(new Character('c'));
		charList.add(new Character('f'));

		assertFalse(list.containsAll(charList));
	}

	/**
	 * 对CharacterSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个characterSequence对象。
	 * @result
	 * 结果<br>
	 * 应该得到正确的对象。
	 * @throws Exception
	 */
	public void testGet() {

		list = new CharacterSequence('b', 'e');

		char c = 'b';
		for(int i = 0, m = list.size(); i < m; i++, c++) {

			assertEquals(new Character(c), (Character) list.get(i));
		}

		list = new CharacterSequence('e', 'b');

		c = 'e';
		for(int i = 0, m = list.size(); i < m; i++, c--) {

			assertEquals(new Character(c), (Character) list.get(i));
		}
	}

	/**
	 * 对CharacterSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个characterSequence对象。
	 * @result
	 * 结果<br>
	 * 应该根据对象得到正确的索引号。
	 * @throws Exception
	 */
	public void testIndexOf() {

		list = new CharacterSequence('b', 'e');

		char c = 'b';
		for(int i = 0, m = list.size(); i < m; i++, c++) {

			assertEquals(i, list.indexOf(new Character(c)));
		}

		list = new CharacterSequence('e', 'b');

		c = 'e';
		for(int i = 0, m = list.size(); i < m; i++, c--) {

			assertEquals(i, list.indexOf(new Character(c)));
		}
	}

	/**
	 * 对CharacterSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个characterSequence对象。
	 * @result
	 * 结果<br>
	 * 应该根据对象得到正确的索引号。
	 * @throws Exception
	 */
	public void testLastIndexOf() {

		list = new CharacterSequence('b', 'e');

		char c = 'b';
		for(int i = 0, m = list.size(); i < m; i++, c++) {

			assertEquals(i, list.lastIndexOf(new Character(c)));
		}

		list = new CharacterSequence('e', 'b');

		c = 'e';
		for(int i = 0, m = list.size(); i < m; i++, c--) {

			assertEquals(i, list.lastIndexOf(new Character(c)));
		}
	}

	/**
	 * 对CharacterSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个characterSequence对象。
	 * @result
	 * 结果<br>
	 * 应该根据对象得到一个非空的ListIterator对象。
	 * @throws Exception
	 */
	public void testListIterator() {

		list = new CharacterSequence('b', 'e');

		ListIterator it = list.listIterator();
		assertNotNull(it);
	}

	/**
	 * 对CharacterSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个characterSequence对象。
	 * @result
	 * 结果<br>
	 * 应该根据对象得到一个非空的ListIterator对象。
	 * @throws Exception
	 */
	public void testListIetrator2() {

		list = new CharacterSequence('b', 'e');

		ListIterator it = list.listIterator(2);
		assertNotNull(it);
	}

	/**
	 * 对CharacterSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个characterSequence对象。
	 * @result
	 * 结果<br>
	 * 应该根据对象得到正确的SubList对象。
	 * @throws Exception
	 */
	public void testSubList() {

		list = new CharacterSequence('b', 'e');

		List subList = list.subList(1, 1);

		assertEquals(2, subList.size());
		Character[] expectCharacter = new Character[]{new Character('c'), new Character('d')};
		for(int i = 0, m = expectCharacter.length; i < m; i++) {
			assertEquals(expectCharacter[i], subList.get(i));
		}

		list = new CharacterSequence('e', 'b');
		subList = list.subList(1, 1);
		assertEquals(2, subList.size());
		expectCharacter = new Character[]{new Character('d'), new Character('c')};
		for(int i = 0, m = expectCharacter.length; i < m; i++) {
			assertEquals(expectCharacter[i], subList.get(i));
		}
	}

	/**
	 * 对CharacterSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个characterSequence对象。
	 * @result
	 * 结果<br>
	 * 应该根据对象得到正确的toString结果。
	 * @throws Exception
	 */
	public void testToString() {

		list = new CharacterSequence('b', 'e');
		String s = list.toString();
		assertEquals("[b,c,d,e]", s);

		list = new CharacterSequence('e', 'b');
		s = list.toString();
		assertEquals("[e,d,c,b]", s);
	}

	/**
	 * 对CharacterSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个characterSequence对象。
	 * @result
	 * 结果<br>
	 * 迭代器应该正常显示。
	 * @throws Exception
	 */
	public void testOnlyIterator() {

		list = new CharacterSequence('b', 'e');
		Iterator it = list.iterator();
		char c = 'b';
		while(it.hasNext()) {

			assertEquals(new Character(c), it.next());
			c++;
		}

		list = new CharacterSequence('e', 'b');
		it = list.iterator();
		c = 'e';
		while(it.hasNext()) {

			assertEquals(new Character(c), it.next());
			c--;
		}
	}

	/**
	 * 对CharacterSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个characterSequence对象。
	 * @result
	 * 结果<br>
	 * 迭代器应该正常显示。
	 * @throws Exception
	 */
	public void testIteratorWithListIterator() {
		// ---  升序的测试
		list = new CharacterSequence('b', 'e');
		Iterator it = list.listIterator();
		char c = 'b';
		while(it.hasNext()) {

			assertEquals(new Character(c), it.next());
			c++;
		}

		ListIterator ListIt = list.listIterator();
		int index = 0;
		while(ListIt.hasNext()){

			assertEquals(index, ListIt.nextIndex());
			index++;
		}
		c = 'e';
		while(ListIt.hasPrevious()) {

			assertEquals(new Character(c), ListIt.previous());
			c--;
		}

		while(ListIt.hasNext()){

			ListIt.nextIndex();
		}
		index = 3;
		while(ListIt.hasPrevious()) {

			assertEquals(index, ListIt.previousIndex());
			index--;
		}

		// --- 降序的测试
		list = new CharacterSequence('e', 'b');
		it = list.listIterator();
		c = 'e';
		while(it.hasNext()) {

			assertEquals(new Character(c), it.next());
			c--;
		}

		ListIt = list.listIterator();
		index = 0;
		while(ListIt.hasNext()){

			assertEquals(index, ListIt.nextIndex());
			index++;
		}
		c = 'b';
		while(ListIt.hasPrevious()) {

			assertEquals(new Character(c), ListIt.previous());
			c++;
		}
		while(ListIt.hasNext()) {
			ListIt.nextIndex();
		}
		index = 3;
		while(ListIt.hasPrevious()) {

			assertEquals(index, ListIt.previousIndex());
			index--;
		}
	}

	/**
	 * 对CharacterSequence的实现List接口的测试。<br>
	 * @condition
	 * 条件<br>
	 * 构造一个characterSequence对象。
	 * @result
	 * 结果<br>
	 * 迭代器应该正常显示。
	 * @throws Exception
	 */
	public void testIteratorWithListIterator2() {
		// --- 升序的测试
		list = new CharacterSequence('b', 'e');
		Iterator it = list.listIterator(1);
		char c = 'c';
		while(it.hasNext()) {

			assertEquals(new Character(c), it.next());
			c++;
		}

		ListIterator ListIt = list.listIterator(1);
		int index = 0;
		while(ListIt.hasNext()){

			assertEquals(index, ListIt.nextIndex());
			index++;
		}
		c = 'e';
		while(ListIt.hasPrevious()) {

			assertEquals(new Character(c), ListIt.previous());
			c--;
		}

		while(ListIt.hasNext()){

			ListIt.nextIndex();
		}
		index = 2;
		while(ListIt.hasPrevious()) {

			assertEquals(index, ListIt.previousIndex());
			index--;
		}

		// --- 降序的测试
		list = new CharacterSequence('e', 'b');
		it = list.listIterator(1);
		c = 'd';
		while(it.hasNext()) {

			assertEquals(new Character(c), it.next());
			c--;
		}

		ListIt = list.listIterator(1);
		index = 0;
		while(ListIt.hasNext()){

			assertEquals(index, ListIt.nextIndex());
			index++;
		}
		c = 'b';
		while(ListIt.hasPrevious()) {

			assertEquals(new Character(c), ListIt.previous());
			c++;
		}
		while(ListIt.hasNext()) {
			ListIt.nextIndex();
		}
		index = 2;
		while(ListIt.hasPrevious()) {

			assertEquals(index, ListIt.previousIndex());
			index--;
		}
	}

}
