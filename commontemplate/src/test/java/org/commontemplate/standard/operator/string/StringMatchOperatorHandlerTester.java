package org.commontemplate.standard.operator.string;

import junit.framework.TestCase;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.tools.PropertiesConfigurationLoader;
/**
 * StringMatchOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class StringMatchOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	
	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	
	/**
	 * 对一元操作符 ~ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个string和一个正则表达式。
	 * @result
	 * 结果<br>
	 * 返回string和正则表达式是否匹配的boolean对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("~");
		
		String str = "tan";
		String regx = "t[aeno]n";
		assertEquals(Boolean.TRUE, handler.doEvaluate(str, regx));
		
		str = "taan";
		assertEquals(Boolean.FALSE, handler.doEvaluate(str, regx));
	}
		
}
