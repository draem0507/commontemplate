package org.commontemplate.standard.operator.compare;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 值不相等比较操作符: "!=" <br/>
 * 如: $if{num1 != num2}<br/>
 *
 * @see java.lang.Object#equals(Object)
 * @author liangfei0201@163.com
 *
 */
public class NotEqualsOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NotEqualsOperatorHandler() {
		super(Object.class, Object.class, true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		if (leftOperand == null && rightOperand == null)
			return Boolean.FALSE;
		if (leftOperand instanceof Character && rightOperand instanceof String) {
			String str = (String)rightOperand;
			return Boolean.valueOf(! (str.length() == 1 && ((Character)leftOperand).charValue() == str.charAt(0)));
		}
		if (leftOperand instanceof String && rightOperand instanceof Character) {
			String str = (String)leftOperand;
			return Boolean.valueOf(! (str.length() == 1 && ((Character)rightOperand).charValue() == str.charAt(0)));
		}
		return Boolean.valueOf(! (leftOperand != null && leftOperand.equals(rightOperand)));
	}

}