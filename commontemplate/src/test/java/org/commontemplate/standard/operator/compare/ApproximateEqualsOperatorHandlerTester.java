package org.commontemplate.standard.operator.compare;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * ApproximateEqualsOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class ApproximateEqualsOperatorHandlerTester extends TestCase {

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
	 * 参数为两个对象。
	 * @result
	 * 结果<br>
	 * 返回两个对象的比较结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("~=");
		
		StringBuffer buffer1 = new StringBuffer();
		StringBuffer buffer2 = new StringBuffer();
		
		assertEquals(Boolean.TRUE, handler.doEvaluate(null, null));
		assertEquals(Boolean.TRUE, handler.doEvaluate(buffer1.append("Abc"), buffer2.append("abc")));
		assertEquals(Boolean.FALSE, handler.doEvaluate(buffer1.append("Abc "), buffer2.append("Abc")));
		assertEquals(Boolean.FALSE, handler.doEvaluate(buffer1.append("Abc "), buffer2.append("abc")));
		assertEquals(Boolean.FALSE, handler.doEvaluate(null, "a"));
		assertEquals(Boolean.FALSE, handler.doEvaluate("a", null));
		assertEquals(Boolean.FALSE, handler.doEvaluate("b", "a"));
		assertEquals(Boolean.TRUE, handler.doEvaluate(new Integer(1), new Integer(1)));
		assertEquals(Boolean.FALSE, handler.doEvaluate(new Integer(1), new Integer(2)));
	}
}
