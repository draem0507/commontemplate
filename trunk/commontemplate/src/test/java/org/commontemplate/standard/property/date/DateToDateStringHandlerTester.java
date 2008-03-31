package org.commontemplate.standard.property.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.standard.property.PropertyHandler;

import junit.framework.TestCase;
/**
 * DateToDateStringHandler的测试。
 * @author Yan Rong
 *
 */
public class DateToDateStringHandlerTester extends TestCase {

	/**
	 * 测试DateToDateStringHandler的方法。
	 * @condition
	 * 条件<br>
	 * 参数为一个日期类型。
	 * @result
	 * 结果<br>
	 * 应该得到日期的字符串格式。
	 * @throws Exception
	 */
	public void testHandleProperty() throws Exception{
		
		PropertyHandler propertyHandler = new DateToDateStringHandler();
		Calendar calendar = Calendar.getInstance();		
		Date dt = new Date();
		calendar.setTime(dt);
		
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.MONTH, Calendar.AUGUST);
		calendar.set(Calendar.DAY_OF_MONTH, 8);
		
		assertEquals("2008-08-08", propertyHandler.getProperty(calendar.getTime()));
	}
}
