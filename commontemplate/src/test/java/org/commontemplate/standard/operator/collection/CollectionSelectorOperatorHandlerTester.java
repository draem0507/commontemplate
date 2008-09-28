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
 * ListSelectorOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class CollectionSelectorOperatorHandlerTester extends TestCase {
	
	BinaryOperatorHandler handler;
	
	protected void setUp() throws Exception {
		handler = new CollectionSelectorOperatorHandler();
	}
	
	/**
	 * 对二元操作符 [ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个List对象, 一个entry, entry.key 为对象的属性名称，entry.value 为要查询的属性值。
	 * @result
	 * 结果<br>
	 * 得到查询结果的对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		List list = new ArrayList();
		list.add(new UserInfo("Tom", 20));
		list.add(new UserInfo("Rose", 18));
		
		Map paramterMap = new HashMap();
		paramterMap.put("userName", "Rose");
		Set entrySet = paramterMap.entrySet();
		Iterator it = entrySet.iterator();
		Entry entry = (Entry) it.next();
		
		UserInfo result = (UserInfo) handler.doEvaluate(list, entry);
		
		assertEquals("Rose", result.getUserName());
		assertEquals(18, result.getUserAge());
	}
}
