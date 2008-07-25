package org.commontemplate.standard.property.system;

import java.util.Date;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * NowPropertyHanlder 的测试。
 * @author YanRong
 *
 */
public class NowPropertyHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;

	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	/**
	 * 对NowPropertyHanlder的测试。<br>
	 * @condition
	 * 条件<br>
	 * 得到 NowPropertyHanlder。
	 * @result
	 * 结果<br>
	 * 得到Date这个对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		UnaryOperatorHandler handler = operatorHandlerProvider.getUnaryOperatorHandler(".");
		Object obj = handler.doEvaluate("now");
		
		assertTrue(obj instanceof Date);
		
	}
}
