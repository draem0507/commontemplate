package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.commontemplate.config.BinaryOperatorHandler;

import junit.framework.TestCase;
/**
 * LiteralListOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class LiteralListOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	protected void setUp() throws Exception {
		
		handler = new LiteralListOperatorHandler();
	}
	
	/**
	 * 对2元操作符 , 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个Map对象，一个Entry对象。
	 * @result
	 * 结果<br>
	 * 返回一个Map对象，包含参数中的Entry对象。
	 * @throws Exception
	 */
	public void testDoEvaluateForMapEntry() throws Exception{
		
		Map map = new HashMap();
		
		Map map2 = new HashMap();
		map2.put("a", "1");
		Set set = map2.entrySet();
		Iterator it = set.iterator();
		Entry entry = (Entry) it.next();
		
		map = (Map) handler.doEvaluate(map, entry);
		
		assertEquals(1, map.size());
		set = map.entrySet();
		it = set.iterator();
		entry = (Entry) it.next();
		assertEquals("a", entry.getKey());
		assertEquals("1", entry.getValue());
		
	}
	
	/**
	 * 对2元操作符 , 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个Entry对象，一个Entry对象。
	 * @result
	 * 结果<br>
	 * 返回一个Map对象，包含参数中的2个Entry对象。
	 * @throws Exception
	 */
	public void testDoEvaluateForEntryEntry() throws Exception{
		
		Map map = new HashMap();
		map.put("a", "1");
		map.put("b", "2");
		Set set = map.entrySet();
		Iterator it = set.iterator();
		Entry entry1 = (Entry) it.next();
		Entry entry2 = (Entry) it.next();
		
		map = (Map) handler.doEvaluate(entry1, entry2);
		
		assertEquals(2, map.size());
		set = map.entrySet();
		it = set.iterator();
		Entry entry = (Entry) it.next();
		assertEquals("a", entry.getKey());
		assertEquals("1", entry.getValue());
		
		entry = (Entry) it.next();
		assertEquals("b", entry.getKey());
		assertEquals("2", entry.getValue());
		
	}
	
	/**
	 * 对2元操作符 , 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个List对象，一个Object对象。
	 * @result
	 * 结果<br>
	 * 返回一个List对象，包含参数中的Object对象。
	 * @throws Exception
	 */
	public void testDoEvaluateForListObject() throws Exception{
		
		List list = (List) handler.doEvaluate(new ArrayList(), "a");
		assertTrue(list.contains("a"));
	}
	
	/**
	 * 对2元操作符 , 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个Object对象，一个Object对象。
	 * @result
	 * 结果<br>
	 * 返回一个List对象，包含参数中的2个Object对象。
	 * @throws Exception
	 */
	public void testDoEvaluateForObjectObject() throws Exception{
		
		List list = (List) handler.doEvaluate("b", "a");
		assertTrue(list.contains("a"));
		assertTrue(list.contains("b"));
	}
	
}
