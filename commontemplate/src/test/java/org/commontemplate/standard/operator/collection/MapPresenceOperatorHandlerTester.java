package org.commontemplate.standard.operator.collection;

import java.util.HashMap;
import java.util.Map;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * MapPresenceOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class MapPresenceOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	
	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	/**
	 * 对一元操作符 ？ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个map。
	 * @result
	 * 结果<br>
	 * 根据map是否为空，以及size是否大于零而返回boolean类型。。
	 * @throws Exception
	 */
	public void testDoEvaluateForMap() throws Exception{
		
		UnaryOperatorHandler handler = operatorHandlerProvider.getUnaryOperatorHandler("?");
		
		Map map = null;		
		assertFalse(((Boolean)handler.doEvaluate(map)).booleanValue());
		
		map = new HashMap();		
		assertFalse(((Boolean)handler.doEvaluate(map)).booleanValue());
		
		map.put("a", "a");		
		assertTrue(((Boolean)handler.doEvaluate(map)).booleanValue());
		
	}
}
