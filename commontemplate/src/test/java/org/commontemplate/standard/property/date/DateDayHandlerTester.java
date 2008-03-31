package org.commontemplate.standard.property.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.standard.property.PropertyHandler;

import junit.framework.TestCase;
/**
 * DateDayHandler的测试。
 * @author Yan Rong
 *
 */
public class DateDayHandlerTester extends TestCase {

	/**
	 * 测试DateDayHandler的方法。
	 * @condition
	 * 条件<br>
	 * 参数为一个日期类型。
	 * @result
	 * 结果<br>
	 * 应该得到日期的月份。
	 * @throws Exception
	 */
	public void testHandleProperty() throws Exception{
		
		PropertyHandler propertyHandler = new DateDayHandler();
		Calendar calendar = Calendar.getInstance();		
		Date dt = new Date();
		calendar.setTime(dt);
		
		for(int i = 1; i <= 12; i++) {
			
			calendar.set(Calendar.DAY_OF_MONTH, i);
			assertEquals(Integer.valueOf(i), propertyHandler.getProperty(calendar.getTime()));
		}
		
	}
}
