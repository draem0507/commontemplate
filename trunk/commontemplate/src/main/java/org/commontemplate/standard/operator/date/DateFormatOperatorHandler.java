package org.commontemplate.standard.operator.date;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 日期格式化
 * 
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
