package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
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
		super(Object[].class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		Object[] list = (Object[]) leftOperand;
		List sub = new ArrayList();
		if (rightOperand instanceof IntegerSequence) {
			IntegerSequence indexs = (IntegerSequence) rightOperand;
			if (indexs.isAscending()) { // 升序序列
				for (int i = indexs.getMin(); i < list.length
						&& i <= indexs.getMax(); i++) {
					if (i >= 0)
						sub.add(list[i]);
				}
			} else { // 降序序列
				for (int i = indexs.getMax(); i >= 0
						&& i >= indexs.getMin(); i--) {
					if (i < list.length)
						sub.add(list[i]);
				}
			}
		} else { // 散列索引
			List indexs = (List) rightOperand;
			for (int i = 0, n = indexs.size(); i < n; i++) {
				int index = ((Integer) indexs.get(i)).intValue();
				if (index >= 0 && index < list.length)
					sub.add(list[index]);
			}
		}
		return sub.toArray();
	}

}