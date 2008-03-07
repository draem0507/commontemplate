package org.commontemplate.standard.operator.collection;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * ArrayReverseOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class ArrayReverseOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;

	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	/**
	 * 对一元操作符　- 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个对象数组。
	 * @result
	 * 结果<br>
	 * 得到倒序的数组。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		UnaryOperatorHandler handler = operatorHandlerProvider.getUnaryOperatorHandler("-");
		
		String strs[] = new String[]{"1", "2", "3", "4"};
		Object reversStrs[] = (Object[]) handler.doEvaluate(strs);
		
		for(int i = 0, m = strs.length; i < m; i++) {
			
			assertEquals(strs[i], reversStrs[m -i -1]);
		}
		
	}
}
