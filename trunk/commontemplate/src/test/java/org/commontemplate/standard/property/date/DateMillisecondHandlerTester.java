package org.commontemplate.standard.property.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.standard.property.PropertyHandler;

import junit.framework.TestCase;
/**
 * DateMillisecondHandler的测试。
 * @author Yan Rong
 *
 */
public class DateMillisecondHandlerTester extends TestCase {

	/**
	 * 测试DateCenturyHandler的方法。
	 * @condition
	 * 条件<br>
	 * 参数为一个日期类型。
	 * @result
	 * 结果<br>
	 * 应该得到日期的毫秒数。
	 * @throws Exception
	 */
	public void testHandleProperty() throws Exception{

		PropertyHandler propertyHandler = new DateMillisecondHandler();
		Calendar calendar = Calendar.getInstance();
		Date dt = new Date();
		calendar.setTime(dt);

		calendar.set(Calendar.MILLISECOND, 100);

		assertEquals(new Integer(100), propertyHandler.getProperty(calendar.getTime()));
	}
}
