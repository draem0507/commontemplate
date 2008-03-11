package org.commontemplate.standard.operator.compare;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * CompareOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class CompareOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	
	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	
	/**
	 * 对2元操作符 <=> 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个对象。
	 * @result
	 * 结果<br>
	 * 返回两个对象的比较结果。<br>
	 * 相等返回0, 大于返回1, 小于返回-1
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("<=>");
		
		assertEquals(new Integer(0), handler.doEvaluate("abc", "abc"));
		assertEquals(new Integer(1), handler.doEvaluate("abc", "aac"));
		assertEquals(new Integer(1), handler.doEvaluate(new Integer(2), new Integer(1)));
		assertEquals(new Integer(-1), handler.doEvaluate("abc", "bac"));
		assertEquals(new Integer(-1), handler.doEvaluate(new Integer(1), new Integer(2)));
	}
}
