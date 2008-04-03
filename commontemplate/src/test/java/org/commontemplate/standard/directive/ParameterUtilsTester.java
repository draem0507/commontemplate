package org.commontemplate.standard.directive;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import junit.framework.TestCase;
/**
 * ParameterUtils 的测试。
 * @author YanRong
 *
 */
public class ParameterUtilsTester extends TestCase {

	/**
	 * 对getParameters 方法的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个Entry对象。
	 * @result
	 * 结果<br>
	 * 得到一个Map对象。
	 */
	public void testGetParametersForEntry() {
		
		Map map = new HashMap();
		map.put("a", "1");
		Set set = map.entrySet();
		Iterator it = set.iterator();
		Entry entry = (Entry) it.next();
		
		Map resultMap = ParameterUtils.getParameters(entry);
		
		assertEquals("1", resultMap.get("a"));
	}
	
	/**
	 * 对getParameters 方法的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个Map对象。
	 * @result
	 * 结果<br>
	 * 得到一个Map对象。
	 */
	public void testGetParametersForMap() {
		
		Map map = new HashMap();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");
		
		Map resultMap = ParameterUtils.getParameters(map);
		
		assertEquals(map, resultMap);
	}
	
}
