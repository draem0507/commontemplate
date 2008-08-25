package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.standard.operator.sequence.IntegerSequence;

/**
 * 子列表操作符: "[]"<br/> 如: ${users[0..3]} ${users[2,5,7]}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class ListSubOperatorHandler extends IndexedBinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ListSubOperatorHandler() {
		super(List.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		List list = (List) leftOperand;
		List sub = new ArrayList();
		if (rightOperand instanceof IntegerSequence) {
			IntegerSequence indexs = (IntegerSequence) rightOperand;
			if (indexs.isAsc()) { // 升序序列
				for (int i = indexs.getMin(); i < list.size()
						&& i <= indexs.getMax(); i++) {
					if (i >= 0)
						sub.add(list.get(i));
				}
			} else { // 降序序列
				for (int i = indexs.getMax(); i >= 0
						&& i >= indexs.getMin(); i--) {
					if (i < list.size())
						sub.add(list.get(i));
				}
			}
		} else { // 散列索引
			List indexs = (List) rightOperand;
			for (int i = 0, n = indexs.size(); i < n; i++) {
				int index = ((Integer) indexs.get(i)).intValue();
				if (index >= 0 && index < list.size())
					sub.add(list.get(index));
			}
		}
		return sub;
	}

}