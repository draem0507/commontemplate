package org.commontemplate.standard.operator.number;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * BitXorOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class BitXorOperatorHandlerTester extends TestCase {

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
	 * 参数为两个integer 对象。
	 * @result
	 * 结果<br>
	 * 返回两个integer 对象的异或结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("^");
		
		Integer int1 = Integer.valueOf("1");
		Integer int2 = Integer.valueOf("2");		
		assertEquals(Integer.valueOf("3"), handler.doEvaluate(int1, int2));
		
		int1 = Integer.valueOf("7");
		int2 = Integer.valueOf("8");		
		assertEquals(Integer.valueOf("15"), handler.doEvaluate(int1, int2));
		
		int1 = Integer.valueOf("10");
		int2 = Integer.valueOf("20");		
		assertEquals(Integer.valueOf("30"), handler.doEvaluate(int1, int2));
		
	}
}
