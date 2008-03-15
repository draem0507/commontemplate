package org.commontemplate.standard.operator.compare;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 值相等比较操作符: "=="<br/>
 * 如: $if{obj1 == obj2} <br/>
 * 注: 这里需处理char与string的比较. 如: $if{ch == "A"} (假设ch是char类型变量)<br/>
 *
 * @see java.lang.Object#equals(Object)
 * @author liangfei0201@163.com
 *
 */
public class EqualsOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public EqualsOperatorHandler() {
		super(Object.class, Object.class, true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		if (leftOperand == null && rightOperand == null)
			return Boolean.TRUE;
		if (leftOperand instanceof Character && rightOperand instanceof String) {
			String str = (String)rightOperand;
			return Boolean.valueOf(str.length() == 1 && ((Character)leftOperand).charValue() == str.charAt(0));
		}
		if (leftOperand instanceof String && rightOperand instanceof Character) {
			String str = (String)leftOperand;
			return Boolean.valueOf(str.length() == 1 && ((Character)rightOperand).charValue() == str.charAt(0));
		}
		return Boolean.valueOf(leftOperand != null && leftOperand.equals(rightOperand));
	}

}