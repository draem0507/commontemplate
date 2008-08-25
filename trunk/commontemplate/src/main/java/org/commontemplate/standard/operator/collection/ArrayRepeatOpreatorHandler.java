package org.commontemplate.standard.operator.collection;

import java.lang.reflect.Array;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class ArrayRepeatOpreatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ArrayRepeatOpreatorHandler() {
		super(new Class[]{boolean[].class, char[].class, byte[].class,
				short[].class, int[].class, long[].class,
				float[].class, double[].class, Object[].class},
				new Class[]{Integer.class}, false, false, true); // 左右参数可交换
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Object src;
		int repeat;
		if (rightOperand instanceof Integer) {
			src = leftOperand;
			repeat = ((Integer)rightOperand).intValue();
		} else {
			src = rightOperand;
			repeat = ((Integer)leftOperand).intValue();
		}
		int len = Array.getLength(src);
		if (len == 0 || repeat <= 0)
			return Array.newInstance(src.getClass().getComponentType(), 0);
		Object dest = Array.newInstance(src.getClass().getComponentType(), len * repeat);
		for (int i = 0; i < repeat; i ++)
			System.arraycopy(src, 0, dest, i * len, len);
		return dest;
	}

}