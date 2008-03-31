package org.commontemplate.standard.property.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.standard.property.PropertyHandler;

import junit.framework.TestCase;
/**
 * DateMonthHandler的测试。
 * @author Yan Rong
 *
 */
public class DateMonthHandlerTester extends TestCase {

	/**
	 * 测试DateMonthHandler的方法。
	 * @condition
	 * 条件<br>
	 * 参数为一个日期类型。
	 * @result
	 * 结果<br>
	 * 应该得到日期的月份。
	 * @throws Exception
	 */
	public void testHandleProperty() throws Exception{

		PropertyHandler propertyHandler = new DateMonthHandler();

		Calendar calendar = Calendar.getInstance();
		Date dt = new Date();
		calendar.setTime(dt);

		calendar.set(Calendar.MONTH, 11);

		assertEquals(new Integer(11), propertyHandler.getProperty(calendar.getTime()));
	}
}
