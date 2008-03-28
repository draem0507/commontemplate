package org.commontemplate.standard.operator.sequence;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
/**
 * StringSequence 的测试。
 * @author YanRong
 *
 */
public class StringSequenceTester extends TestCase {
	
	StringSequence sequence;
	
	/**
	 * 对StringSequence的构造函数的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数1：字符串，用逗号分开<br>
	 * 参数2: 是否允许cycle<br>
	 * 参数3：是否忽略大小写<br>
	 * @result
	 * 结果<br>
	 * 判断得到的sequence, cycle, isIgnoreCase 是否正确。
	 * @throws Exception
	 */
	public void testConstructorString() {
		
		List list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		sequence = new StringSequence("a,b,c,d,e", true, true);
		
		assertEquals(list, sequence.getSequence());
		assertTrue(sequence.isCycle());
		assertTrue(sequence.isIgnoreCase());
		
		sequence = new StringSequence("a,b,c,d,e", false, true);
		
		assertEquals(list, sequence.getSequence());
		assertFalse(sequence.isCycle());
		assertTrue(sequence.isIgnoreCase());
		
		sequence = new StringSequence("a,b,c,d,e", false, false);
		
		assertEquals(list, sequence.getSequence());
		assertFalse(sequence.isCycle());
		assertFalse(sequence.isIgnoreCase());
	}
	
	/**
	 * 对StringSequence的构造函数的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数1：字符串数组。<br>
	 * 参数2: 是否允许cycle<br>
	 * 参数3：是否忽略大小写<br>
	 * @result
	 * 结果<br>
	 * 判断得到的sequence, cycle, isIgnoreCase 是否正确。
	 * @throws Exception
	 */
	public void testConstructorStringArray() {
		
		List list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		sequence = new StringSequence(new String[]{"a","b","c","d","e"}, true, true);
		
		assertEquals(list, sequence.getSequence());
		assertTrue(sequence.isCycle());
		assertTrue(sequence.isIgnoreCase());
		
		sequence = new StringSequence(new String[]{"a","b","c","d","e"}, false, true);
		
		assertEquals(list, sequence.getSequence());
		assertFalse(sequence.isCycle());
		assertTrue(sequence.isIgnoreCase());
		
		sequence = new StringSequence(new String[]{"a","b","c","d","e"}, false, false);
		
		assertEquals(list, sequence.getSequence());
		assertFalse(sequence.isCycle());
		assertFalse(sequence.isIgnoreCase());
	}
	
	/**
	 * 对StringSequence的构造函数的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数1：List对象。<br>
	 * 参数2: 是否允许cycle<br>
	 * 参数3：是否忽略大小写<br>
	 * @result
	 * 结果<br>
	 * 判断得到的sequence, cycle, isIgnoreCase 是否正确。
	 * @throws Exception
	 */
	public void testConstructorList() {
		
		List list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		sequence = new StringSequence(list, true, true);
		
		assertEquals(list, sequence.getSequence());
		assertTrue(sequence.isCycle());
		assertTrue(sequence.isIgnoreCase());
		
		sequence = new StringSequence("a,b,c,d,e", false, true);
		
		assertEquals(list, sequence.getSequence());
		assertFalse(sequence.isCycle());
		assertTrue(sequence.isIgnoreCase());
		
		sequence = new StringSequence(list, false, false);
		
		assertEquals(list, sequence.getSequence());
		assertFalse(sequence.isCycle());
		assertFalse(sequence.isIgnoreCase());
	}
	
	/**
	 * 对ContainSequence函数的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入两个字符串。
	 * @result
	 * 结果<br>
	 * StringSequence的范围是否包含以这两个字符串为范围的字符串序列。
	 * @throws Exception
	 */
	public void testContainSequenceIngoreCase() {
		
		sequence = new StringSequence("a,b,c,d,e", true, true);
		
		assertTrue(sequence.containSequence("b", "E"));
		assertTrue(sequence.containSequence("A", "d"));
		assertTrue(sequence.containSequence("A", "C"));
		
		assertFalse(sequence.containSequence("a", "f"));
		assertFalse(sequence.containSequence("b", "g"));
		assertFalse(sequence.containSequence("f", "i"));
		
		assertFalse(sequence.containSequence("f", null));
		assertFalse(sequence.containSequence(null, null));
	}
	
	/**
	 * 对ContainSequence函数的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入两个字符串。
	 * @result
	 * 结果<br>
	 * StringSequence的范围是否包含以这两个字符串为范围的字符串序列。
	 * @throws Exception
	 */
	public void testContainSequenceNotIngoreCase() {
		
		sequence = new StringSequence("a,b,c,d,e", true, false);
		
		assertTrue(sequence.containSequence("b", "e"));
		assertTrue(sequence.containSequence("a", "d"));
		assertTrue(sequence.containSequence("a", "c"));
		
		assertFalse(sequence.containSequence("B", "e"));
		assertFalse(sequence.containSequence("A", "D"));
		assertFalse(sequence.containSequence("A", "c"));
		assertFalse(sequence.containSequence("a", "f"));
		assertFalse(sequence.containSequence("b", "g"));
		assertFalse(sequence.containSequence("f", "i"));
		
		assertFalse(sequence.containSequence("f", null));
		assertFalse(sequence.containSequence(null, null));
	}
	
	/**
	 * 对getSequence函数的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入两个字符串。
	 * @result
	 * 结果<br>
	 * 得到以这两个字符串为范围的新的字符串序列。
	 * @throws Exception
	 */
	public void testGetSequenceNormal() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		List subList = sequence.getSequence("c", "f");
		List expectList = new ArrayList();
		expectList.add("c");
		expectList.add("d");
		expectList.add("e");
		expectList.add("f");
		assertEquals(expectList, subList);
		
		// 输入的字符串序列不存在的时候
		subList = sequence.getSequence("d", "z");
		assertEquals(0, subList.size());
	}
	
	/**
	 * 对getSequence函数的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入两个字符串。<br>
	 * 并且开始的字符串比结束的字符串大。
	 * @result
	 * 结果<br>
	 * 得到一个循环的新的字符串序列。<br>
	 * 如：字符串为 a,b,c,e,f,g ，输入 f,c<br>
	 * 则得到 f,g,a,b,c
	 * @throws Exception
	 */
	public void testGetSequenceCycle() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		List subList = sequence.getSequence("f", "c");
		List expectList = new ArrayList();
		expectList.add("f");
		expectList.add("g");
		expectList.add("a");
		expectList.add("b");
		expectList.add("c");
		assertEquals(expectList, subList);
		
		// 输入的字符串序列不存在的时候
		subList = sequence.getSequence("f", "z");
		assertEquals(0, subList.size());
	}
	
	/**
	 * 对getSequence函数的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入两个字符串。<br>
	 * 并且开始的字符串比结束的字符串大。
	 * @result
	 * 结果<br>
	 * 得到一个循环的新的字符串序列。<br>
	 * 如：字符串为 a,b,c,e,f,g ，输入 g,c<br>
	 * 则得到 g,a,b,c
	 * @throws Exception
	 */
	public void testGetSequenceCycle2() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		List subList = sequence.getSequence("g", "c");
		List expectList = new ArrayList();
		expectList.add("g");
		expectList.add("a");
		expectList.add("b");
		expectList.add("c");
		assertEquals(expectList, subList);
		
		// 输入的字符串序列不存在的时候
		subList = sequence.getSequence("f", "z");
		assertEquals(0, subList.size());
	}
	
	public void testReverseList() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", false, true);
		List subList = sequence.getSequence("g", "c");
		List expectList = new ArrayList();
		expectList.add("g");
		expectList.add("f");
		expectList.add("e");
		expectList.add("d");
		expectList.add("c");
		assertEquals(expectList, subList);
		
		// 输入的字符串序列不存在的时候
		subList = sequence.getSequence("f", "z");
		assertEquals(0, subList.size());
	}
	
}
