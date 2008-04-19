package org.commontemplate.standard.property.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.standard.property.PropertyHandler;

import junit.framework.TestCase;
/**
 * WeekOfMonthHandler的测试。
 * @author Yan Rong
 *
 */
public class WeekOfMonthHandlerTester extends TestCase {

	/**
	 * 测试WeekOfMonthHandler的方法。
	 * @condition
	 * 条件<br>
	 * 参数为一个日期类型。
	 * @result
	 * 结果<br>
	 * 应该得到日期是所属月份的第几个星期。
	 * @throws Exception
	 */
	public void testHandleProperty() throws Exception{

		PropertyHandler propertyHandler = new WeekOfMonthHandler();
		Calendar calendar = Calendar.getInstance();
		Date dt = new Date();
		calendar.setTime(dt);

		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.MONTH, Calendar.MARCH);
		calendar.set(Calendar.DAY_OF_MONTH, 14);

		assertEquals(new Integer(3), propertyHandler.getProperty(calendar.getTime()));
	}
}
