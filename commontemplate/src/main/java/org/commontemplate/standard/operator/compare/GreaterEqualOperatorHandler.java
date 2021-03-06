package org.commontemplate.standard.operator.compare;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 大于或等于比较操作符: ">="<br/>
 * 如: $if{num1 >= num2}<br/>
 *
 * @see java.lang.Comparable
 * @author liangfei0201@163.com
 *
 */
public class GreaterEqualOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public GreaterEqualOperatorHandler() {
		super(Comparable.class, Comparable.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf(((Comparable)leftOperand).compareTo(rightOperand) >= 0);
	}

}
