package org.commontemplate.standard.property.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.standard.property.PropertyHandler;

import junit.framework.TestCase;
/**
 * DateHourHandler的测试。
 * @author Yan Rong
 *
 */
public class DateHourHandlerTester extends TestCase {

	/**
	 * 测试DateCenturyHandler的方法。
	 * @condition
	 * 条件<br>
	 * 参数为一个日期类型。
	 * @result
	 * 结果<br>
	 * 应该得到日期的小时数。
	 * @throws Exception
	 */
	public void testHandleProperty() throws Exception{

		PropertyHandler propertyHandler = new DateHourHandler();
		Calendar calendar = Calendar.getInstance();
		Date dt = new Date();
		calendar.setTime(dt);

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		assertEquals(new Integer(23), propertyHandler.doProperty(calendar.getTime()));

		calendar.set(Calendar.HOUR_OF_DAY, 1);
		assertEquals(new Integer(1), propertyHandler.doProperty(calendar.getTime()));
	}
}
