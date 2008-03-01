package org.commontemplate.standard.operator.compare;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 数值比较，相等返回0, 大于返回1, 小于返回-1
 * 
 * @author liangfei0201@163.com
 *
 */
public class CompareOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public CompareOperatorHandler() {
		super(Comparable.class, Comparable.class);
	}
	
	private static final Integer EQUAL = new Integer(0);
	
	private static final Integer GREATER = new Integer(1);
	
	private static final Integer LESS = new Integer(-1);
	
	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		int value = ((Comparable)leftOperand).compareTo(rightOperand);
		if (value == 0) 
			return EQUAL;
		return value > 0 ? GREATER : LESS;
	}

}