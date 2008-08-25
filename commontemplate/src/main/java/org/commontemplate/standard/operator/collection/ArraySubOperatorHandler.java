package org.commontemplate.standard.operator.collection;

import java.lang.reflect.Array;
import java.util.List;

import org.commontemplate.standard.operator.sequence.IntegerSequence;

/**
 * 从数组中提取子数组操作符: "[]"<br/> 如: ${arr[0..3]} ${arr[1,3,4]}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class ArraySubOperatorHandler extends
		IndexedBinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ArraySubOperatorHandler() {
		super(new Class[]{boolean[].class, char[].class, byte[].class,
				short[].class, int[].class, long[].class,
				float[].class, double[].class, Object[].class});
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		Object array = leftOperand;
		int len = Array.getLength(array);
		if (rightOperand instanceof IntegerSequence) { // 注意：需处理无穷大序列，如：array[2..*]
			IntegerSequence indexs = (IntegerSequence) rightOperand;
			int min = indexs.getMin() > 0 ? indexs.getMin() : 0;
			int max = indexs.getMax() < len - 1 ? indexs.getMax() : len - 1;
			int n = max - min + 1;
			if (n <= 0)
				return Array.newInstance(array.getClass().getComponentType(), 0);
			Object sub = Array.newInstance(array.getClass().getComponentType(), n);
			for (int i = 0; i < n; i++) {
				int index = indexs.isAsc() ? min + i : max - i;
				Array.set(sub, i,
						Array.get(array, index));
			}
			return sub;
		} else { // 散列索引
			List indexs = (List) rightOperand;
			int n = indexs.size();
			if (n <= 0)
				return Array.newInstance(array.getClass().getComponentType(), 0);
			Object sub = Array.newInstance(array.getClass().getComponentType(), n);
			for (int i = 0; i < n; i++) {
				int index = ((Integer) indexs.get(i)).intValue();
				if (index >= 0 && index < len)
					Array.set(sub, i, Array.get(array, index));
			}
			return sub;
		}
	}

}