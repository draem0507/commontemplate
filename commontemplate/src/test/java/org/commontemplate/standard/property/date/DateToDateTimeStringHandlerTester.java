package org.commontemplate.standard.property.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.standard.property.PropertyHandler;

import junit.framework.TestCase;
/**
 * DateToDateTimeStringHandler的测试。
 * @author Yan Rong
 *
 */
public class DateToDateTimeStringHandlerTester extends TestCase {

	/**
	 * 测试DateToDateTimeStringHandler的方法。
	 * @condition
	 * 条件<br>
	 * 参数为一个日期类型。
	 * @result
	 * 结果<br>
	 * 应该得到日期的字符串。
	 * @throws Exception
	 */
	public void testHandleProperty() throws Exception{
		
		PropertyHandler propertyHandler = new DateToDateTimeStringHandler();
		Calendar calendar = Calendar.getInstance();		
		Date dt = new Date();
		calendar.setTime(dt);
		
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.MONTH, Calendar.AUGUST);
		calendar.set(Calendar.DAY_OF_MONTH, 8);
		calendar.set(Calendar.HOUR_OF_DAY, 20);
		calendar.set(Calendar.MINUTE, 10);
		calendar.set(Calendar.SECOND, 15);
		
		assertEquals("2008-08-08 20:10:15", propertyHandler.getProperty(calendar.getTime()));
	}
}
