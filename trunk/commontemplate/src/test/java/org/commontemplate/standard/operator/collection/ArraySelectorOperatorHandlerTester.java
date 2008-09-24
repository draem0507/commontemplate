package org.commontemplate.standard.operator.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.array.ArraySelectorOperatorHandler;

import junit.framework.TestCase;
/**
 * ArraySelectorOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class ArraySelectorOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	protected void setUp() throws Exception {
		handler = new ArraySelectorOperatorHandler();
	}
	
	/**
	 * 对二元操作符 [ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个对象数组, 一个entry, entry.key 为对象的属性名称，entry.value 为要查询的属性值。
	 * @result
	 * 结果<br>
	 * 得到查询结果的对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		UserInfo[] userInfos = new UserInfo[]{new UserInfo("Tom", 20),
												new UserInfo("Rose", 18)};
		
		Map paramterMap = new HashMap();
		paramterMap.put("userName", "Rose");
		Set entrySet = paramterMap.entrySet();
		Iterator it = entrySet.iterator();
		Entry entry = (Entry) it.next();
		
		UserInfo result = (UserInfo) handler.doEvaluate(userInfos, entry);
		
		assertEquals("Rose", result.getUserName());
		assertEquals(18, result.getUserAge());
	}
}
