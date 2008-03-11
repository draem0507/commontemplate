package org.commontemplate.standard.operator.string;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * StringIngoreCaseEqualsOperatorHanlder 的测试。
 * @author YanRong
 *
 */
public class StringIngoreCaseEqualsOperatorHanlderTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	
	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	
	/**
	 * 对2元操作符 ~= 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个string 对象。
	 * @result
	 * 结果<br>
	 * 返回两个 string 对象的比较结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("~=");
		
		assertEquals(Boolean.TRUE, handler.doEvaluate("abc  ", "  abc"));
		assertEquals(Boolean.TRUE, handler.doEvaluate("Abc  ", "  aBc"));
		assertEquals(Boolean.FALSE, handler.doEvaluate("abe  ", "  abc"));
		
	}
}
