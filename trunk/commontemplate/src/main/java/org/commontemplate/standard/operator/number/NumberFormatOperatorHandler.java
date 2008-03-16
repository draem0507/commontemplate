package org.commontemplate.standard.operator.number;

import java.text.DecimalFormat;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 数字格式化操作符: "#"<br/>
 * 如: ${12 # "###,##0.00"} ${num1 # "###,##0.00"}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class NumberFormatOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NumberFormatOperatorHandler() {
		super(Number.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return new DecimalFormat((String)rightOperand).format((Number)leftOperand);
	}

}
