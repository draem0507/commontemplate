package org.commontemplate.standard.operator.collection;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 数组按索引号取值处理器
 * 
 * @author liangfei0201@163.com
 *
 */
public class ArrayGetterOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ArrayGetterOperatorHandler() {
		super(Object[].class, Integer.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Object[] arr = (Object[])leftOperand;
		Integer index = (Integer)rightOperand;
		if (index.intValue() < 0) // 小于0表示倒数
			index = new Integer(arr.length - index.intValue());
		if (index.intValue() < 0 || index.intValue() >= arr.length) // 忽略越界
			return null;
		return arr[index.intValue()];
	}

}