package org.commontemplate.standard.operator.array;

import java.lang.reflect.Array;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class ArrayContainOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ArrayContainOperatorHandler() {
		super(new Class[]{Object.class}, new Class[]{boolean[].class, char[].class, byte[].class,
			short[].class, int[].class, long[].class,
			float[].class, double[].class, Object[].class}, true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		if (leftOperand == null || rightOperand == null)
			return Boolean.FALSE;
		for (int i = 0, n = Array.getLength(rightOperand); i < n; i ++) {
			Object o = Array.get(rightOperand, i);
			if (leftOperand.equals(o))
				return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}