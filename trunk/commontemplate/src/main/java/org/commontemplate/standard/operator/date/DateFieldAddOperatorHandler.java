package org.commontemplate.standard.operator.date;

import java.util.Calendar;
import java.util.Date;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.standard.property.number.DateField;

/**
 * 日期指定字段的数值向前滚动操作符: "+"<br/>
 * 如: ${date + 1.year} ${date + 3.day}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class DateFieldAddOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public DateFieldAddOperatorHandler() {
		super(Date.class, DateField.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Date leftDate = (Date)leftOperand;
		DateField field = (DateField)rightOperand;
		if (field.getType() == DateField.MONTH) {
			Calendar c = Calendar.getInstance();
			c.setTime(leftDate);
			c.add(Calendar.MONTH, field.getValue());
			return c.getTime();
		} else if (field.getType() == DateField.YEAR) {
			Calendar c = Calendar.getInstance();
			c.setTime(leftDate);
			c.add(Calendar.YEAR, field.getValue());
			return c.getTime();
		}
		return new Date(leftDate.getTime() + field.getTime());
	}

}