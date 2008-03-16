package org.commontemplate.standard.operator.date;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 日期格式化操作符: "#"<br/>
 * 如: ${date # "yyyy-MM-dd HH:mm:ss"}<br/>
 *
 * @see java.text.DateFormat#format(Date)
 * @author liangfei0201@163.com
 *
 */
public class DateFormatOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public DateFormatOperatorHandler() {
		super(Date.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return new SimpleDateFormat((String)rightOperand).format((Date)leftOperand);
	}

}
