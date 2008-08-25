package org.commontemplate.standard.operator.collection;

import java.lang.reflect.Array;

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
		super(new Class[]{boolean[].class, char[].class, byte[].class,
				short[].class, int[].class, long[].class,
				float[].class, double[].class, Object[].class});
	}

	public Object doEvaluate(Object operand) throws Exception {
		Object array = operand;
		int len = Array.getLength(array);
		if (len <= 1)
			return array;
		Object dest = Array.newInstance(array.getClass().getComponentType(), len);
		for (int i = 0; i < len; i ++)
			Array.set(dest, i, Array.get(array, len - 1 - i));
		return dest;
	}

}