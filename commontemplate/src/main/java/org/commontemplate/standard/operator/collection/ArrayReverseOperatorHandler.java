package org.commontemplate.standard.operator.collection;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

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