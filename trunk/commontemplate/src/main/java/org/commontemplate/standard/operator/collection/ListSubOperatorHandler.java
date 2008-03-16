package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.standard.operator.sequence.IntegerSequence;

/**
 * 子列表操作符: "[]"<br/>
 * 如: ${users[0..3]} ${users[2,5,7]}<br/>
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
		List list = (List)leftOperand;
		if (rightOperand instanceof IntegerSequence) {
			IntegerSequence seq = (IntegerSequence)rightOperand;
			int begin = seq.getBegin();
			if (begin < 0)
				begin = 0;
			int end = seq.getEnd() + 1;
			if (end > list.size())
				end = list.size();
			return list.subList(begin, end);
		}
		List indexs = (List)rightOperand;
		List sub = new ArrayList(indexs.size());
		for (int i = 0, n = indexs.size(); i < n ; i ++) {
			int index = ((Integer)indexs.get(i)).intValue();
			if (index < list.size() - 1)
				sub.add(list.get(index));
		}
		return sub;
	}

}