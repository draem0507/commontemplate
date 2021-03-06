package org.commontemplate.standard.operator.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 日期年月日比较操符符: "~="<br/>
 * 如: $if{"2000-03-12 22:30".toDate ~= "2000-03-12 12:11".toDate}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class DateIgnoreTimeEqualsOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public DateIgnoreTimeEqualsOperatorHandler() {
		super(Date.class, Date.class, true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		if (leftOperand == null && rightOperand == null)
			return Boolean.TRUE;
		if (leftOperand == null || rightOperand == null)
			return Boolean.FALSE;

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
