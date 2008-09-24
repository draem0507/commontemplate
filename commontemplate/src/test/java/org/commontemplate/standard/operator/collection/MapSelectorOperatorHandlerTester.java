package org.commontemplate.standard.operator.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.map.MapSelectorOperatorHandler;
import org.commontemplate.util.MapEntry;

import junit.framework.TestCase;
/**
 * MapSelectorOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class MapSelectorOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	protected void setUp() throws Exception {
		handler = new MapSelectorOperatorHandler();
	}
	
	/**
	 * 对二元操作符 [ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个map, 一个entry, entry.key 为"key"，entry.value 为要查询的key的名称。
	 * @result
	 * 结果<br>
	 * 得到查询结果的 MapEntry 对象。
	 * @throws Exception
	 */
	public void testDoEvaluateGetByKey() throws Exception{
		
		Map map = new HashMap();
		map.put("Tom", new Integer(20));
		map.put("Jack", new Integer(25));
		
		Map paramterMap = new HashMap();
		paramterMap.put("key", "Jack");
		Set entrySet = paramterMap.entrySet();
		Iterator it = entrySet.iterator();
		Entry entry = (Entry) it.next();
		
		MapEntry resultEntry = (MapEntry) handler.doEvaluate(map, entry);
		
		assertEquals("Jack", resultEntry.getKey());
		assertEquals(new Integer(25), resultEntry.getValue());
	}
	
	/**
	 * 对二元操作符 [ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个map, 一个entry, entry.key 为"value"，entry.value 为要查询的value的名称。
	 * @result
	 * 结果<br>
	 * 得到查询结果的 Entry 对象。
	 * @throws Exception
	 */
	public void testDoEvaluateGetByValue() throws Exception{
		
		Map map = new HashMap();
		map.put("Tom", new Integer(20));
		map.put("Jack", new Integer(25));
		
		Map paramterMap = new HashMap();
		paramterMap.put("value", new Integer(20));
		Set entrySet = paramterMap.entrySet();
		Iterator it = entrySet.iterator();
		Entry entry = (Entry) it.next();
		
		Entry resultEntry = (Entry) handler.doEvaluate(map, entry);
		
		assertEquals("Tom", resultEntry.getKey());
		assertEquals(new Integer(20), resultEntry.getValue());
	}
	
}
