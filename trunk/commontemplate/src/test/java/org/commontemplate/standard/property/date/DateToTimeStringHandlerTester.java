package org.commontemplate.standard.property.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.standard.property.PropertyHandler;

import junit.framework.TestCase;
/**
 * DateToTimeStringHandler的测试。
 * @author Yan Rong
 *
 */
public class DateToTimeStringHandlerTester extends TestCase {

	/**
	 * 测试DateToTimeStringHandler的方法。
	 * @condition
	 * 条件<br>
	 * 参数为一个日期类型。
	 * @result
	 * 结果<br>
	 * 应该得到日期的字符串。
	 * @throws Exception
	 */
	public void testHandleProperty() throws Exception{
		
		PropertyHandler propertyHandler = new DateToTimeStringHandler();
		Calendar calendar = Calendar.getInstance();		
		Date dt = new Date();
		calendar.setTime(dt);
		
		calendar.set(Calendar.HOUR_OF_DAY, 16);
		calendar.set(Calendar.MINUTE, 12);
		calendar.set(Calendar.SECOND, 30);
		
		assertEquals("16:12:30", propertyHandler.getProperty(calendar.getTime()));
		
	}
}
