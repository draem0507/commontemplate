package org.commontemplate.standard.operator.context;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * VariableOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class VariableOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;

	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	/**
	 * 对一元操作符　\ 的测试。<br>
	 * 这个操作符是用来转义的。
	 * @condition
	 * 条件<br>
	 * 输入一个对象。
	 * @result
	 * 结果<br>
	 * 得到原来的对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		UnaryOperatorHandler handler = operatorHandlerProvider.getUnaryOperatorHandler("\\");
		
		Object obj = this;
		
		assertEquals(this, handler.doEvaluate(obj));		
	}
}
