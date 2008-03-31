package org.commontemplate.standard.operator.date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.commontemplate.config.BinaryOperatorHandler;

import junit.framework.TestCase;
/**
 * DateFormatOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class DateFormatOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	public void setUp() {

		handler = new DateFormatOperatorHandler();			
	}
	
	/**
	 * 对2元操作符 # 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为Date 对象, 格式化格式。
	 * @result
	 * 结果<br>
	 * 按照格式化的格式返回日期的字符串。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.MONTH, Calendar.MARCH);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		calendar.set(Calendar.HOUR_OF_DAY, 10);
		calendar.set(Calendar.MINUTE, 35);
		calendar.set(Calendar.SECOND, 59);
		
		String format = (String) handler.doEvaluate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
		assertEquals("2008-03-31 10:35:59", format);
	}
	
}
