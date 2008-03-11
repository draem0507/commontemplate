package org.commontemplate.standard.operator.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.standard.property.number.DateField;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * DateFieldAddOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class DateFieldAddOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	
	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为Date 对象, DateField 对象。
	 * @result
	 * 结果<br>
	 * 返回 Date 日期增加 DateField 后新的日期。
	 * @throws Exception
	 */
	public void testDoEvaluateForYear() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		Calendar calendar = Calendar.getInstance();
		
		Date leftOperand = new Date();
		calendar.setTime(leftOperand);
		calendar.set(Calendar.YEAR, 2008);
		DateField rightOperand = new DateField(DateField.YEAR, 1); 
		
		Date resultDate = (Date) handler.doEvaluate(calendar.getTime(), rightOperand);		
		calendar.setTime(resultDate);
		
		assertEquals(2009, calendar.get(Calendar.YEAR));
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为Date 对象, DateField 对象。
	 * @result
	 * 结果<br>
	 * 返回 Date 日期增加 DateField 后新的日期。
	 * @throws Exception
	 */
	public void testDoEvaluateForMonth() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		Calendar calendar = Calendar.getInstance();
		
		Date leftOperand = new Date();
		calendar.setTime(leftOperand);
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		DateField rightOperand = new DateField(DateField.MONTH, 1); 
		
		Date resultDate = (Date) handler.doEvaluate(calendar.getTime(), rightOperand);
		calendar.setTime(resultDate);
		
		assertEquals(2009, calendar.get(Calendar.YEAR));
		assertEquals(Calendar.JANUARY, calendar.get(Calendar.MONTH));
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为Date 对象, DateField 对象。
	 * @result
	 * 结果<br>
	 * 返回 Date 日期增加 DateField 后新的日期。
	 * @throws Exception
	 */
	public void testDoEvaluateForDay() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		Calendar calendar = Calendar.getInstance();
		
		Date leftOperand = new Date();
		calendar.setTime(leftOperand);
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 29);
		DateField rightOperand = new DateField(DateField.DAY, 2); 
		
		Date resultDate = (Date) handler.doEvaluate(calendar.getTime(), rightOperand);
		calendar.setTime(resultDate);
		
		assertEquals(2008, calendar.get(Calendar.YEAR));
		assertEquals(31, calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为Date 对象, DateField 对象。
	 * @result
	 * 结果<br>
	 * 返回 Date 日期增加 DateField 后新的日期。
	 * @throws Exception
	 */
	public void testDoEvaluateForDay2() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		Calendar calendar = Calendar.getInstance();
		
		Date leftOperand = new Date();
		calendar.setTime(leftOperand);
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 29);
		DateField rightOperand = new DateField(DateField.DAY, 3); 
		
		Date resultDate = (Date) handler.doEvaluate(calendar.getTime(), rightOperand);
		calendar.setTime(resultDate);
		
		assertEquals(2009, calendar.get(Calendar.YEAR));
		assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为Date 对象, DateField 对象。
	 * @result
	 * 结果<br>
	 * 返回 Date 日期增加 DateField 后新的日期。
	 * @throws Exception
	 */
	public void testDoEvaluateForWeek() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		Calendar calendar = Calendar.getInstance();
		
		Date leftOperand = new Date();
		calendar.setTime(leftOperand);
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.MONTH, Calendar.MARCH);
		calendar.set(Calendar.DAY_OF_MONTH, 8);
		DateField rightOperand = new DateField(DateField.WEEK, 1); 
		
		Date resultDate = (Date) handler.doEvaluate(calendar.getTime(), rightOperand);
		calendar.setTime(resultDate);
		
		assertEquals(2008, calendar.get(Calendar.YEAR));
		assertEquals(15, calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为Date 对象, DateField 对象。
	 * @result
	 * 结果<br>
	 * 返回 Date 日期增加 DateField 后新的日期。
	 * @throws Exception
	 */
	public void testDoEvaluateForHour() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		Calendar calendar = Calendar.getInstance();
		
		Date leftOperand = new Date();
		calendar.setTime(leftOperand);
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.MONTH, Calendar.MARCH);
		calendar.set(Calendar.DAY_OF_MONTH, 8);
		calendar.set(Calendar.HOUR_OF_DAY, 10);
		DateField rightOperand = new DateField(DateField.HOUR, 3); 
		
		Date resultDate = (Date) handler.doEvaluate(calendar.getTime(), rightOperand);
		calendar.setTime(resultDate);

		assertEquals(13, calendar.get(Calendar.HOUR_OF_DAY));
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为Date 对象, DateField 对象。
	 * @result
	 * 结果<br>
	 * 返回 Date 日期增加 DateField 后新的日期。
	 * @throws Exception
	 */
	public void testDoEvaluateForMinute() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		Calendar calendar = Calendar.getInstance();
		
		Date leftOperand = new Date();
		calendar.setTime(leftOperand);
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.MONTH, Calendar.MARCH);
		calendar.set(Calendar.DAY_OF_MONTH, 8);
		calendar.set(Calendar.HOUR_OF_DAY, 10);
		calendar.set(Calendar.MINUTE, 10);
		DateField rightOperand = new DateField(DateField.MINUTE, 6); 
		
		Date resultDate = (Date) handler.doEvaluate(calendar.getTime(), rightOperand);
		calendar.setTime(resultDate);

		assertEquals(16, calendar.get(Calendar.MINUTE));
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为Date 对象, DateField 对象。
	 * @result
	 * 结果<br>
	 * 返回 Date 日期增加 DateField 后新的日期。
	 * @throws Exception
	 */
	public void testDoEvaluateForSecond() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		Calendar calendar = Calendar.getInstance();
		
		Date leftOperand = new Date();
		calendar.setTime(leftOperand);
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.MONTH, Calendar.MARCH);
		calendar.set(Calendar.DAY_OF_MONTH, 8);
		calendar.set(Calendar.HOUR_OF_DAY, 10);
		calendar.set(Calendar.MINUTE, 10);
		calendar.set(Calendar.SECOND, 10);
		DateField rightOperand = new DateField(DateField.SECOND, 6); 
		
		Date resultDate = (Date) handler.doEvaluate(calendar.getTime(), rightOperand);
		calendar.setTime(resultDate);

		assertEquals(16, calendar.get(Calendar.SECOND));
	}
	
	/**
	 * 对2元操作符 + 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为Date 对象, DateField 对象。
	 * @result
	 * 结果<br>
	 * 返回 Date 日期增加 DateField 后新的日期。
	 * @throws Exception
	 */
	public void testDoEvaluateForMillonSecond() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("+");
		Calendar calendar = Calendar.getInstance();
		
		Date leftOperand = new Date();
		calendar.setTime(leftOperand);
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.MONTH, Calendar.MARCH);
		calendar.set(Calendar.DAY_OF_MONTH, 8);
		calendar.set(Calendar.HOUR_OF_DAY, 10);
		calendar.set(Calendar.MINUTE, 10);
		calendar.set(Calendar.SECOND, 10);
		calendar.set(Calendar.MILLISECOND, 10);
		DateField rightOperand = new DateField(DateField.MILLISECOND, 6); 
		
		Date resultDate = (Date) handler.doEvaluate(calendar.getTime(), rightOperand);
		calendar.setTime(resultDate);

		assertEquals(16, calendar.get(Calendar.MILLISECOND));
	}
}
