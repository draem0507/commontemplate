package org.commontemplate.standard.operator.context;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * StaticFunctionOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class StaticFunctionOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;

	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	/**
	 * 对一元操作符　. 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个函数名字。
	 * @result
	 * 结果<br>
	 * 执行该函数。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		UnaryOperatorHandler handler = operatorHandlerProvider.getUnaryOperatorHandler(".");
	}
}
