package org.commontemplate.standard.property.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.standard.property.PropertyHandler;

import junit.framework.TestCase;
/**
 * DayOfYearHandler的测试。
 * @author Yan Rong
 *
 */
public class DayOfYearHandlerTester extends TestCase {

	/**
	 * 测试DayOfYearHandler的方法。
	 * @condition
	 * 条件<br>
	 * 参数为一个日期类型。
	 * @result
	 * 结果<br>
	 * 应该得到日期是一年中的第几天。
	 * @throws Exception
	 */
	public void testHandleProperty() throws Exception{

		PropertyHandler propertyHandler = new DayOfYearHandler();
		Calendar calendar = Calendar.getInstance();
		Date dt = new Date();
		calendar.setTime(dt);

		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.MONDAY, Calendar.FEBRUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 29);

		assertEquals(new Integer(60), propertyHandler.getProperty(calendar.getTime()));
	}
}
