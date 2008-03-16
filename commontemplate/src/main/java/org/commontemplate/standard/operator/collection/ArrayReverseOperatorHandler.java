package org.commontemplate.standard.operator.collection;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

/**
 * 数组反转一元操作符: "-"<br/>
 * 如: ${- array}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class ArrayReverseOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ArrayReverseOperatorHandler() {
		super(Object[].class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		Object[] ar = (Object[])operand;
		int n = ar.length;
		if (n <= 1) {
			return ar;
		}
		Object[] re = new Object[n];
		for (int i = 0; i < n; i ++) {
			re[i] = ar[n - 1 - i];
		}
		return re;
	}

}