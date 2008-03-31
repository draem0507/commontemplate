package org.commontemplate.standard.property.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.standard.property.PropertyHandler;

import junit.framework.TestCase;
/**
 * DateMinuteHandler的测试。
 * @author Yan Rong
 *
 */
public class DateMinuteHandlerTester extends TestCase {

	/**
	 * 测试DateMinuteHandler的方法。
	 * @condition
	 * 条件<br>
	 * 参数为一个日期类型。
	 * @result
	 * 结果<br>
	 * 应该得到日期的分钟数。
	 * @throws Exception
	 */
	public void testHandleProperty() throws Exception{
		
		PropertyHandler propertyHandler = new DateMinuteHandler();
		Calendar calendar = Calendar.getInstance();		
		Date dt = new Date();
		calendar.setTime(dt);
		
		calendar.set(Calendar.MINUTE, 59);
		
		assertEquals(Integer.valueOf(59), propertyHandler.getProperty(calendar.getTime()));
		
	}
}
