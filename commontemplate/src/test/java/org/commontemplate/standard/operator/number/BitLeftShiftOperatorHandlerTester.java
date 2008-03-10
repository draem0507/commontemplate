package org.commontemplate.standard.operator.number;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * BitLeftShiftOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class BitLeftShiftOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	
	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	
	/**
	 * 对2元操作符 << 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个integer 对象。
	 * @result
	 * 结果<br>
	 * 返回两个integer 对象的左移位运算操作结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("<<");
		
		Integer int1 = new Integer(3);
		Integer int2 = new Integer(1);
		
		assertEquals(Integer.valueOf("6"), handler.doEvaluate(int1, int2));
		
	}
}
