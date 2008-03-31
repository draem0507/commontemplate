package org.commontemplate.standard.operator.collection;

import java.util.HashMap;
import java.util.Map;

import org.commontemplate.config.BinaryOperatorHandler;

import junit.framework.TestCase;
/**
 * MapGetterOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class MapGetterOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	protected void setUp() throws Exception {
		handler = new MapGetterOperatorHandler();
	}
	
	/**
	 * 对二元操作符 [ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个map, 一个要查询的key的名称。
	 * @result
	 * 结果<br>
	 * 得到查询结果的 Value 对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		Map map = new HashMap();
		map.put("Tom", new Integer(20));
		map.put("Jack", new Integer(25));
		
		Integer result  = (Integer) handler.doEvaluate(map, "Jack");
		assertEquals(new Integer(25), result);
		
		result  = (Integer) handler.doEvaluate(map, "jack");
		assertNull(result);
	}
}
