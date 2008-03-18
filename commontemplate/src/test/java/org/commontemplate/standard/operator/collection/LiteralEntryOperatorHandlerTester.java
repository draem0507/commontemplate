package org.commontemplate.standard.operator.collection;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.util.MapEntry;

import junit.framework.TestCase;
/**
 * LiteralEntryOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class LiteralEntryOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	BinaryOperatorHandlerChain handler;
	
	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
		handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler(":");
	}
	
	/**
	 * 对2元操作符 : 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个对象。
	 * @result
	 * 结果<br>
	 * 返回一个MapEntry对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		MapEntry mapEntry = (MapEntry) handler.doEvaluate("a", "b");
		assertEquals("a", mapEntry.getKey());
		assertEquals("b", mapEntry.getValue());
		
	}
	
	/**
	 * 对2元操作符 = 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个对象。
	 * @result
	 * 结果<br>
	 * 返回一个MapEntry对象。
	 * @throws Exception
	 */
	public void testDoEvaluate2() throws Exception{
		
		MapEntry mapEntry = (MapEntry) handler.doEvaluate("a", "b");
		assertEquals("a", mapEntry.getKey());
		assertEquals("b", mapEntry.getValue());
		
	}
}
