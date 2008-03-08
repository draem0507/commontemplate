package org.commontemplate.standard.operator.bool;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * BooleanXorOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class BooleanXorOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	
	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	
	/**
	 * 对2元操作符 ^ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个boolean 对象。
	 * @result
	 * 结果<br>
	 * 返回两个boolean 对象的异或结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("^");
		
		Boolean b1 = Boolean.TRUE;
		Boolean b2 = Boolean.FALSE;		
		assertEquals(Boolean.TRUE, handler.doEvaluate(b1, b2));
		
		b1 = Boolean.FALSE;
		b2 = Boolean.TRUE;		
		assertEquals(Boolean.TRUE, handler.doEvaluate(b1, b2));
		
		b1 = Boolean.TRUE;
		b2 = Boolean.TRUE;		
		assertEquals(Boolean.FALSE, handler.doEvaluate(b1, b2));
		
		b1 = Boolean.FALSE;
		b2 = Boolean.FALSE;		
		assertEquals(Boolean.FALSE, handler.doEvaluate(b1, b2));
		
	}
		
}
