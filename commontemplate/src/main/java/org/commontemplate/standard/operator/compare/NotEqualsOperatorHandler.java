package org.commontemplate.standard.operator.compare;

import org.commontemplate.util.TypeUtils;

/**
 * 值不相等比较操作符: "!=" <br/>
 * 如: $if{num1 != num2}<br/>
 *
 * @see java.lang.Object#equals(Object)
 * @author liangfei0201@163.com
 *
 */
public class NotEqualsOperatorHandler extends EqualsOperatorHandler {

	private static final long serialVersionUID = 1L;

	public NotEqualsOperatorHandler() {
		super();
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf(! TypeUtils.isTrue(super.doEvaluate(leftOperand, rightOperand)));
	}

}