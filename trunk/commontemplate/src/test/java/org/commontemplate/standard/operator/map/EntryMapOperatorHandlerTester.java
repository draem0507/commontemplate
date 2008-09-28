package org.commontemplate.standard.operator.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import junit.framework.TestCase;

import org.commontemplate.config.BinaryOperatorHandler;

public class EntryMapOperatorHandlerTester extends TestCase {

	private BinaryOperatorHandler handler;

	protected void setUp() throws Exception {
		handler = new EntryMapOperatorHandler();
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

}
