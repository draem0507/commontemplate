package org.commontemplate.standard.operator.compare;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 字符串忽略大小写比较操作符: "~="<br/>
 * 如: $if{"aa" ~= "AA"}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class ApproximateEqualsOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ApproximateEqualsOperatorHandler() {
		super(Object.class, Object.class ,true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		if (leftOperand == null && rightOperand == null)
			return Boolean.TRUE;
		if (leftOperand == null || rightOperand == null)
			return Boolean.FALSE;
		return Boolean.valueOf(leftOperand.toString().equalsIgnoreCase(rightOperand.toString()));
	}

}