package org.commontemplate.standard.operator.compare;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * EqualsOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class EqualsOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	
	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	
	/**
	 * 对2元操作符 == 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个对象。
	 * @result
	 * 结果<br>
	 * 返回两个对象的比较结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("==");
		
		Character character = new Character('a');
		String str = "a";		
		assertEquals(Boolean.TRUE, (Boolean) handler.doEvaluate(character, str));
		assertEquals(Boolean.TRUE, (Boolean) handler.doEvaluate(str, character));
		
		character = new Character('a');
		str = "aa";		
		assertEquals(Boolean.FALSE, (Boolean) handler.doEvaluate(character, str));
		assertEquals(Boolean.FALSE, (Boolean) handler.doEvaluate(str, character));
		
		character = new Character('a');
		str = "b";
		
		assertEquals(Boolean.FALSE, (Boolean) handler.doEvaluate(character, str));
		assertEquals(Boolean.FALSE, (Boolean) handler.doEvaluate(str, character));
		
		assertEquals(Boolean.TRUE, (Boolean) handler.doEvaluate("abc", "abc"));
	}
}
