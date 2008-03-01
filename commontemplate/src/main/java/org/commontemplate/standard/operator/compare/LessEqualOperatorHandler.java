package org.commontemplate.standard.operator.compare;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * "小于或等于"操作符处理器
 * 
 * @see java.lang.Comparable
 * @author liangfei0201@163.com
 *
 */
public class LessEqualOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public LessEqualOperatorHandler() {
		super(Comparable.class, Comparable.class);
	}
	
	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf(((Comparable)leftOperand).compareTo(rightOperand) <= 0);
	}

}
