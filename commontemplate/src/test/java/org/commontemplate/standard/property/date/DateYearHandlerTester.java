package org.commontemplate.standard.property.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.standard.property.PropertyHandler;

import junit.framework.TestCase;
/**
 * DateYearHandler的测试。
 * @author Yan Rong
 *
 */
public class DateYearHandlerTester extends TestCase {

	/**
	 * 测试DateYearHandler的方法。
	 * @condition
	 * 条件<br>
	 * 参数为一个日期类型。
	 * @result
	 * 结果<br>
	 * 应该得到日期的年份。
	 * @throws Exception
	 */
	public void testHandleProperty() throws Exception{

		PropertyHandler propertyHandler = new DateYearHandler();
		Calendar calendar = Calendar.getInstance();
		Date dt = new Date();
		calendar.setTime(dt);

		calendar.set(Calendar.YEAR, 2007);

		assertEquals(new Integer(2007), propertyHandler.doProperty(calendar.getTime()));
	}
}
