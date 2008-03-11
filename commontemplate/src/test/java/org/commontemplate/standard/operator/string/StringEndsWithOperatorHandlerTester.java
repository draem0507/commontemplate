package org.commontemplate.standard.operator.string;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * StringEndsWithOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class StringEndsWithOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	
	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	
	/**
	 * 对2元操作符 $= 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个string 对象。
	 * @result
	 * 结果<br>
	 * 返回第一个string 的结尾是否是第二个string的结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("$=");
		
		assertEquals(Boolean.TRUE, handler.doEvaluate("Hello World!", "orld!"));
		assertEquals(Boolean.FALSE, handler.doEvaluate("Hello World!", " orld"));
		assertEquals(Boolean.FALSE, handler.doEvaluate("Hello World!", "orld"));
	}
}
