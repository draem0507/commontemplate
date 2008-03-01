package org.commontemplate.standard.operator.compare;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * "等于"操作符处理器
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