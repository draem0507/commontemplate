package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * 从数组中提取子数组操作符: "[]"<br/>
 * 如: ${arr[0..3]} ${arr[1,3,4]}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class ArraySubOperatorHandler extends IndexedBinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ArraySubOperatorHandler() {
		super(Object[].class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		Object[] list = (Object[])leftOperand;
		List indexs = (List)rightOperand;
		List sub = new ArrayList(indexs.size());
		for (int i = 0, n = indexs.size(); i < n ; i ++) {
			int index = ((Integer)indexs.get(i)).intValue();
			if (index < list.length - 1)
				sub.add(list[index]);
		}
		return sub.toArray();
	}

}