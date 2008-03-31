package org.commontemplate.standard.property.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.standard.property.PropertyHandler;

import junit.framework.TestCase;

/**
 * DateCenturyHandler的测试。
 * @author Yan Rong
 *
 */
public class DateCenturyHandlerTester extends TestCase {

	/**
	 * 测试DateCenturyHandler的方法。
	 * @condition
	 * 条件<br>
	 * 参数为一个日期类型。
	 * @result
	 * 结果<br>
	 * 应该得到日期的年份的前两位数字。其实就是计算是第几个世纪。
	 * @throws Exception
	 */
	public void testHandleProperty() throws Exception{

		PropertyHandler propertyHandler = new DateCenturyHandler();
		Calendar calendar = Calendar.getInstance();
		Date dt = new Date();
		calendar.setTime(dt);

		calendar.set(Calendar.YEAR, 2008);
		assertEquals(new Integer(20), propertyHandler.getProperty(dt));

		calendar.set(Calendar.YEAR, 1979);
		assertEquals(new Integer(19), propertyHandler.getProperty(calendar.getTime()));

		calendar.set(Calendar.YEAR, 2000);
		assertEquals(new Integer(20), propertyHandler.getProperty(calendar.getTime()));

		calendar.set(Calendar.YEAR, 1900);
		assertEquals(new Integer(19), propertyHandler.getProperty(calendar.getTime()));

		calendar.set(Calendar.YEAR, 800);
		assertEquals(new Integer(8), propertyHandler.getProperty(calendar.getTime()));

		calendar.set(Calendar.YEAR, 60);
		assertEquals(new Integer(0), propertyHandler.getProperty(calendar.getTime()));
	}
}
