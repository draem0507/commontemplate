package org.commontemplate.standard.operator.collection;

import java.lang.reflect.Array;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 数组按索引号取值操作符: "[]"<br/>
 * 如: ${array[3]}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class ArrayGetterOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ArrayGetterOperatorHandler() {
		super(new Class[]{boolean[].class, char[].class, byte[].class,
				short[].class, int[].class, long[].class,
				float[].class, double[].class, Object[].class},
				new Class[]{Integer.class});
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Object array = leftOperand;
		int index = ((Integer)rightOperand).intValue();
		int len = Array.getLength(array);
		if (index < 0) // 小于0表示倒数
			index = len - 1 + index;
		if (index < 0 || index >= len) // 忽略越界
			return null;
		return Array.get(array, index);
	}

}