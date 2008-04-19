package org.commontemplate.standard.property.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.standard.property.PropertyHandler;

import junit.framework.TestCase;
/**
 * DateToLongHandler的测试。
 * @author Yan Rong
 *
 */
public class DateToLongHandlerTester extends TestCase {

	/**
	 * 测试DateToLongHandler的方法。
	 * @condition
	 * 条件<br>
	 * 参数为一个日期类型。
	 * @result
	 * 结果<br>
	 * 应该得到日期的整型数值。
	 * @throws Exception
	 */
	public void testHandleProperty() throws Exception{

		PropertyHandler propertyHandler = new DateToLongHandler();
		Calendar calendar = Calendar.getInstance();
		Date dt = new Date();
		calendar.setTime(dt);

		assertEquals(new Long(calendar.getTime().getTime()),
				propertyHandler.getProperty(calendar.getTime()));
	}
}
