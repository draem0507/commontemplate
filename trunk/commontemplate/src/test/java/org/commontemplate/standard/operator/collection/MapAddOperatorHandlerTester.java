package org.commontemplate.standard.operator.collection;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * MapAddOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class MapAddOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	
	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Map对象。
	 * @result
	 * 结果<br>
	 * 返回两个Map对象的合的新的Map对象。
	 * @throws Exception
	 */
	public void testDoEvaluateForHashMap() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
		Map map1 = new HashMap();
		map1.put("a", "A");
		map1.put("b", "B");
		Map map2 = new HashMap();
		map2.put("c", "C");
		map2.put("d", "D");
		
		Map map3 = (Map) handler.doEvaluate(map1, map2);
		
		assertTrue(map3 instanceof HashMap);
		
		Map map4 = new HashMap();
		map4.putAll(map1);
		map4.putAll(map2);
		assertEquals(map3, map4);
		
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Map对象。
	 * @result
	 * 结果<br>
	 * 返回两个Map对象的合的新的Map对象。
	 * @throws Exception
	 */
	public void testDoEvaluateForHashTable() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		
		Map map1 = new Hashtable();
		map1.put("a", "A");
		map1.put("b", "B");
		Map map2 = new Hashtable();
		map2.put("c", "C");
		map2.put("d", "D");
		
		Map map3 = (Map) handler.doEvaluate(map1, map2);
		
		assertTrue(map3 instanceof Hashtable);
		
		Map map4 = new Hashtable();
		map4.putAll(map1);
		map4.putAll(map2);
		assertEquals(map3, map4);
		
	}
}
