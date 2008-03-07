package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * ListReverseOperatorHandler　的测试。
 * @author YanRong
 *
 */
public class ListReverseOperatorHandlerTester extends TestCase {

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
	 * 输入一个list。
	 * @result
	 * 结果<br>
	 * 得到倒序的list。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		UnaryOperatorHandler handler = operatorHandlerProvider.getUnaryOperatorHandler("-");
		
		List list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("1");
		list.add("2");
		
		List reversList = (List) handler.doEvaluate(list);
		
		for(int i = 0, m = list.size(); i < m; i++) {
			
			assertEquals(list.get(i), reversList.get(m - i -1));
		}
		
	}
}
