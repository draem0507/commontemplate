package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import java.util.Map.Entry;

import junit.framework.TestCase;
/**
 * 单一对象转化成集合处理器, Object 转成 List, Entry 转成 Map 的测试。
 * @author YanRong
 *
 */
public class ToCollectionOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;

	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	/**
	 * 对一元操作符　[ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个对象。
	 * @result
	 * 结果<br>
	 * 得到集合的形式的对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		UnaryOperatorHandler handler = operatorHandlerProvider.getUnaryOperatorHandler("[");
		
		List list = new ArrayList();
		list.add("a");
		assertEquals(list, handler.doEvaluate(list));
		
		Map map = new HashMap();
		map.put("a", "1");
		assertEquals(map, handler.doEvaluate(map));
		
		Set set = map.entrySet();
		Iterator it = set.iterator();
		
		Entry entry = (Entry) it.next();
		
		Map newMap = (Map) handler.doEvaluate(entry);
		
		assertEquals(map, newMap);
		
		assertEquals(list, handler.doEvaluate("a"));
		
	}
}
