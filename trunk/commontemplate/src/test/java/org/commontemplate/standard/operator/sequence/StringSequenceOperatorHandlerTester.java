package org.commontemplate.standard.operator.sequence;

import java.util.List;

import junit.framework.TestCase;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.tools.PropertiesConfigurationLoader;
/**
 * StringSequenceOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class StringSequenceOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	BinaryOperatorHandler handler;
	
	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
		handler = operatorHandlerProvider.getBinaryOperatorHandler("..");
	}
	
	/**
	 * 对二元操作符　.. 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入两个字符串。
	 * @result
	 * 结果<br>
	 * 得到List对象。
	 * @throws Exception
	 */
	public void testDoEvaluateForQuarters() throws Exception{
		
		List list = (List) handler.doEvaluate("Spring", "Winter");
		
		String quarters[] = new String[]{"Spring","Summer","Autumn","Winter"};
		for(int i = 0, m = quarters.length; i < m; i++) {
			
			assertEquals(i, list.indexOf(quarters[i]));
		}
	}
	
	/**
	 * 对二元操作符　.. 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入两个字符串。
	 * @result
	 * 结果<br>
	 * 得到List对象。
	 * @throws Exception
	 */
	public void testDoEvaluateForMonth() throws Exception{
		
		List list = (List) handler.doEvaluate("January", "December");
		
		String months[] = new String[]{"January","February","March","April","May","June","July","August",
				"September","October","November","December"};
		for(int i = 0, m = months.length; i < m; i++) {
			
			assertEquals(i, list.indexOf(months[i]));
		}
	}
	
	/**
	 * 对二元操作符　.. 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入两个字符串。
	 * @result
	 * 结果<br>
	 * 得到List对象。
	 * @throws Exception
	 */
	public void testDoEvaluateForWeekDays() throws Exception{
		
		List list = (List) handler.doEvaluate("Sunday", "Saturday");
		
		String weekDays[] = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		for(int i = 0, m = weekDays.length; i < m; i++) {
			
			assertEquals(i, list.indexOf(weekDays[i]));
		}
	}
	
}
