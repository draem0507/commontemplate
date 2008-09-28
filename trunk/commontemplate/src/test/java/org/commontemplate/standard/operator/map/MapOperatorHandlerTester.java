package org.commontemplate.standard.operator.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import junit.framework.TestCase;

import org.commontemplate.config.BinaryOperatorHandler;

public class MapOperatorHandlerTester extends TestCase {

	private BinaryOperatorHandler handler;

	protected void setUp() throws Exception {
		handler = new MapOperatorHandler();
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

}
