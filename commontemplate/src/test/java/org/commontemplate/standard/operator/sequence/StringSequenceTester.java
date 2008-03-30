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
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入要增加的索引位置和要增加的字符串。
	 * @result
	 * 结果<br>
	 * 新的字符串应该正常地加到StringSequence中。
	 * @throws Exception
	 */
	public void testAddByIndex() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		sequence.add(1, "x");
		
		StringSequence expect = new StringSequence("a,x,b,c,d,e,f,g", true, true);
		
		assertEquals(expect.getSequence(), sequence);
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入要增加的字符串。
	 * @result
	 * 结果<br>
	 * 新的字符串应该正常地加到StringSequence中。
	 * @throws Exception
	 */
	public void testAddObject() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		sequence.add("x");
		
		StringSequence expect = new StringSequence("a,b,c,d,e,f,g,x", true, true);
		
		assertEquals(expect.getSequence(), sequence);
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入要增加的List对象。
	 * @result
	 * 结果<br>
	 * 新的字符串应该正常地加到StringSequence中。
	 * @throws Exception
	 */
	public void testAddAll() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		StringSequence addSequence = new StringSequence("x,y,z", true, true);
		
		sequence.addAll(addSequence);
		
		StringSequence expect = new StringSequence("a,b,c,d,e,f,g,x,y,z", true, true);
		assertEquals(expect.getSequence(), sequence);
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入要增加的List对象。
	 * @result
	 * 结果<br>
	 * 新的字符串应该正常地加到StringSequence中。
	 * @throws Exception
	 */
	public void testAddAllByIndex() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		StringSequence addSequence = new StringSequence("x,y,z", true, true);
		
		sequence.addAll(1, addSequence);
		
		StringSequence expect = new StringSequence("a,x,y,z,b,c,d,e,f,g", true, true);
		assertEquals(expect.getSequence(), sequence);
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 调用 clear 方法。
	 * @result
	 * 结果<br>
	 * 字符串序列应该被清空。
	 * @throws Exception
	 */
	public void testClear() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		sequence.clear();
		assertEquals(0, sequence.size());
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 一个字符串。
	 * @result
	 * 结果<br>
	 * 正确地判断字符串是否存在于序列中。
	 * @throws Exception
	 */
	public void testContains() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		assertTrue(sequence.contains("a"));
		assertFalse(sequence.contains("x"));
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 一个字符串集合。
	 * @result
	 * 结果<br>
	 * 正确地判断字符串集合是否全部存在于序列中。
	 * @throws Exception
	 */
	public void testContainsAll() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		StringSequence compareSequence = new StringSequence("b,c,d,e,f", true, true);
		assertTrue(sequence.containsAll(compareSequence));
		compareSequence = new StringSequence("b,c,d,e,f,x", true, true);
		assertFalse(sequence.containsAll(compareSequence));
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 一个字符串集合。
	 * @result
	 * 结果<br>
	 * 正确地判断两个字符串序列是否相同。
	 * @throws Exception
	 */
	public void testEquals() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		StringSequence compareSequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		assertTrue(sequence.equals(compareSequence));
		compareSequence = new StringSequence("b,c,d,e,f,x", true, true);
		assertFalse(sequence.equals(compareSequence));
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 一个索引号。
	 * @result
	 * 结果<br>
	 * 通过所引号正确的得到字符串。
	 * @throws Exception
	 */
	public void testGet() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		char c = 'a';
		for(int i = 0; i < sequence.size(); i++) {
			
			assertEquals(String.valueOf(c), sequence.get(i));
			c++;
		}
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 一个字符串序列。
	 * @result
	 * 结果<br>
	 * 能够得到Hashcode
	 * @throws Exception
	 */
	public void testHashCode() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		assertTrue(sequence.hashCode() > 0);
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 一个字符串。
	 * @result
	 * 结果<br>
	 * 通过字符串正确的得到索引号。
	 * @throws Exception
	 */
	public void testIndexOf() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		char c = 'a';
		for(int i = 0; i < sequence.size(); i++) {
			
			assertEquals(i, sequence.indexOf(String.valueOf(c)));
			c++;
		}
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 一个字符串序列。
	 * @result
	 * 结果<br>
	 * 正确地判断字符串是否为空。
	 * @throws Exception
	 */
	public void testIsEmpty() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		assertFalse(sequence.isEmpty());
		sequence = new StringSequence("", true, true);
		assertTrue(sequence.isEmpty());
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		sequence.clear();
		assertTrue(sequence.isEmpty());
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 一个字符串序列。
	 * @result
	 * 结果<br>
	 * 能够得到一个Iterator。
	 * @throws Exception
	 */
	public void testIterator() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		assertNotNull(sequence.iterator());
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 一个字符串序列。
	 * @result
	 * 结果<br>
	 * 能够正确地得到一个字符的最后一个索引号。
	 * @throws Exception
	 */
	public void testLastIndexOf() {
		
		sequence = new StringSequence("a,b,c,d,e,d,g", true, true);
		assertEquals(5, sequence.lastIndexOf("d"));
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 一个字符串序列。
	 * @result
	 * 结果<br>
	 * 能够得到一个ListIterator。
	 * @throws Exception
	 */
	public void testListIterator() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		assertNotNull(sequence.listIterator());
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个索引号。
	 * @result
	 * 结果<br>
	 * 能够正确地删除索引号所对应的字符串。
	 * @throws Exception
	 */
	public void testRemove() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		char c = 'a';
		for(int i = 0; i < sequence.size(); i++, c++) {
			
			sequence.remove(0);
			assertFalse(sequence.contains(String.valueOf(c)));
		}
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个字符串。
	 * @result
	 * 结果<br>
	 * 能够正确地删除字符串。
	 * @throws Exception
	 */
	public void testRemoveObject() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		char c = 'a';
		for(int i = 0; i < sequence.size(); i++, c++) {
			
			sequence.remove(String.valueOf(c));
			assertFalse(sequence.contains(String.valueOf(c)));
		}
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个字符串序列。
	 * @result
	 * 结果<br>
	 * 能够正确地删除字符串序列。
	 * @throws Exception
	 */
	public void testRemoveAll() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);		
		StringSequence removeSequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		sequence.removeAll(removeSequence.getSequence());
		assertEquals(0, sequence.size());
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);		
		removeSequence = new StringSequence("a,b,c", true, true);
		StringSequence expectSequence = new StringSequence("d,e,f,g", true, true);
		sequence.removeAll(removeSequence.getSequence());
		assertEquals(expectSequence.getSequence(), sequence);
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个字符串序列。
	 * @result
	 * 结果<br>
	 * 能够正确地保留字符串序列。
	 * @throws Exception
	 */
	public void testRetainAll() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);		
		StringSequence retainSequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		sequence.retainAll(retainSequence.getSequence());
		assertEquals(7, sequence.size());
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);		
		retainSequence = new StringSequence("a,b,c", true, true);
		StringSequence expectSequence = new StringSequence("a,b,c", true, true);
		sequence.retainAll(retainSequence.getSequence());
		assertEquals(expectSequence.getSequence(), sequence);
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个索引号，字符串。
	 * @result
	 * 结果<br>
	 * 能够正确地更新字符串序列。
	 * @throws Exception
	 */
	public void testSet() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		sequence.set(0, "x");
		StringSequence expectSequence = new StringSequence("x,b,c,d,e,f,g", true, true);
		assertEquals(expectSequence, sequence);
		
		sequence.set(6, "x");
		expectSequence = new StringSequence("x,b,c,d,e,f,x", true, true);
		assertEquals(expectSequence, sequence);
		
		sequence.set(2, "x");
		expectSequence = new StringSequence("x,b,x,d,e,f,x", true, true);
		assertEquals(expectSequence, sequence);
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 一个字符串序列。
	 * @result
	 * 结果<br>
	 * 能够正确地得到size。
	 * @throws Exception
	 */
	public void testSize() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		assertEquals(7, sequence.size());
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 开始索引号，结束索引号。
	 * @result
	 * 结果<br>
	 * 能够正确地得到一个子字符序列对象。
	 * @throws Exception
	 */
	public void testSubList() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		List subList = sequence.subList(1, 5);
		StringSequence expectSequence = new StringSequence("b,c,d,e", true, true);
		assertEquals(expectSequence.getSequence(), subList);
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 一个字符串序列。
	 * @result
	 * 结果<br>
	 * 能够正确地得到一个字符串数组。
	 * @throws Exception
	 */
	public void testToArray() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		String[] expectArray = new String[]{"a","b","c","d","e","f","g"};
		Object[] resultArray = sequence.toArray();
		for(int i = 0, m = expectArray.length; i < m; i++) {
			assertEquals(expectArray[i], resultArray[i]);
		}
	}
	
	/**
	 * 对List接口实现的测试。<br>
	 * @condition
	 * 条件<br>
	 * 一个字符串序列。
	 * @result
	 * 结果<br>
	 * 能够正确地得到一个字符串数组。
	 * @throws Exception
	 */
	public void testToArray2() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		String[] expectArray = new String[]{"a","b","c","d","e","f","g"};
		String[] resultArray = (String[]) sequence.toArray(new String[sequence.size()]);
		for(int i = 0, m = expectArray.length; i < m; i++) {
			assertEquals(expectArray[i], resultArray[i]);
		}
	}
	
	public void testUnmoify() {
		
		sequence = new StringSequence("a,b,c,d,e,f,g", true, true);
		List list = sequence.getSequence();
		boolean b = false;
		try {
			list.add("x");
		} catch (Exception e) {
			b = true;
		}
		assertTrue(b);
	}
	
}
