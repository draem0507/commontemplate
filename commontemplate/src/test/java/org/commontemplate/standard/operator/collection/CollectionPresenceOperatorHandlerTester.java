package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.Collection;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * CollectionPresenceOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class CollectionPresenceOperatorHandlerTester extends TestCase {

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
	 * 参数为一个collection。
	 * @result
	 * 结果<br>
	 * 根据collection是否为空，以及size是否大于零而返回boolean类型。。
	 * @throws Exception
	 */
	public void testDoEvaluateForCollection() throws Exception{
		
		UnaryOperatorHandler handler = operatorHandlerProvider.getUnaryOperatorHandler("?");
		
		Collection collection = null;
		assertFalse(((Boolean)handler.doEvaluate(collection)).booleanValue());
		
		collection = new ArrayList();
		assertFalse(((Boolean)handler.doEvaluate(collection)).booleanValue());
		
		collection.add("a");
		assertTrue(((Boolean)handler.doEvaluate(collection)).booleanValue());
	}
}
