package org.commontemplate.standard.property.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.standard.property.PropertyHandler;

import junit.framework.TestCase;
/**
 * DateLeapHandler的测试。
 * @author Yan Rong
 *
 */
public class DateLeapHandlerTester extends TestCase {

	/**
	 * 测试DateCenturyHandler的方法。
	 * @condition
	 * 条件<br>
	 * 参数为一个日期类型。
	 * @result
	 * 结果<br>
	 * 应该得到是否是闰年。
	 * @throws Exception
	 */
	public void testHandleProperty() throws Exception{
		
		PropertyHandler propertyHandler = new DateLeapHandler();
		Calendar calendar = Calendar.getInstance();		
		Date dt = new Date();
		calendar.setTime(dt);
		
		calendar.set(Calendar.YEAR, 100);
		assertEquals(Boolean.valueOf(false), 
				propertyHandler.doProperty(calendar.getTime()));
		
		calendar.set(Calendar.YEAR, 1000);
		assertEquals(Boolean.valueOf(false), 
				propertyHandler.doProperty(calendar.getTime()));
		
		calendar.set(Calendar.YEAR, 2000);
		assertEquals(Boolean.valueOf(true), 
				propertyHandler.doProperty(calendar.getTime()));
		
		calendar.set(Calendar.YEAR, 2008);
		assertEquals(Boolean.valueOf(true), 
				propertyHandler.doProperty(calendar.getTime()));
	}
}
