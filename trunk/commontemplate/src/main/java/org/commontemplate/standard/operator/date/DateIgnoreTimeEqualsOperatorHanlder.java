package org.commontemplate.standard.operator.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class DateIgnoreTimeEqualsOperatorHanlder extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public DateIgnoreTimeEqualsOperatorHanlder() {
		super(Date.class, Date.class);
	}
	
	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Date leftDate = (Date)leftOperand;
		Date rightDate = (Date)rightOperand;
		
		// Calendar创建效率比较低，这里重用同一Calendar
		// 并且Calendar不是线程安全的，不能作为静态字段
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(leftDate);
		int ly = calendar.get(Calendar.YEAR);
		int lm = calendar.get(Calendar.MONTH);
		int ld = calendar.get(Calendar.DAY_OF_MONTH);
		
		calendar.setTime(rightDate);
		int ry = calendar.get(Calendar.YEAR);
		int rm = calendar.get(Calendar.MONTH);
		int rd = calendar.get(Calendar.DAY_OF_MONTH);
		
		return Boolean.valueOf(ly == ry && lm == rm && ld == rd);
	}

}
