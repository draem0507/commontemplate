package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.standard.operator.sequence.IntegerSequence;

/**
 * 从列表中提取子列表处理器
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
			return list.subList(seq.getBegin(), seq.getEnd() + 1);
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