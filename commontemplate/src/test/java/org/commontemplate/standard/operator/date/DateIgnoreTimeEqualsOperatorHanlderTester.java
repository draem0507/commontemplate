package org.commontemplate.standard.operator.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
/**
 * DateIgnoreTimeEqualsOperatorHanlder 的测试。
 * @author YanRong
 *
 */
public class DateIgnoreTimeEqualsOperatorHanlderTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new DateIgnoreTimeEqualsOperatorHanlder();
	}
	
	/**
	 * 对2元操作符 ~= 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Date 对象。
	 * @result
	 * 结果<br>
	 * 返回两个 Date 的比较结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(new Date());
		calendar1.set(Calendar.YEAR, 2008);
		calendar1.set(Calendar.MONTH, Calendar.MARCH);
		calendar1.set(Calendar.DAY_OF_MONTH, 8);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(new Date());
		calendar2.set(Calendar.YEAR, 2008);
		calendar2.set(Calendar.MONTH, Calendar.MARCH);
		calendar2.set(Calendar.DAY_OF_MONTH, 8);
		
		assertEvaluation(calendar1.getTime(), calendar2.getTime(), Boolean.TRUE);
		
		calendar2.set(Calendar.DAY_OF_MONTH, 9);
		
		assertEvaluation(calendar1.getTime(), calendar2.getTime(), Boolean.FALSE);
		
	}
}
