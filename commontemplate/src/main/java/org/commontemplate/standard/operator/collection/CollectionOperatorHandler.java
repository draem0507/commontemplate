package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.Collection;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.standard.operator.sequence.Sequence;

/**
 * 字面列表操作符: ","<br/>
 * 如: ${"aa","bb","cc"}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class CollectionOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public CollectionOperatorHandler() {
		super(Object.class, Object.class, true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		if (leftOperand instanceof Collection
				&& ! (leftOperand instanceof Sequence)) { // 序列除外
			Collection literalList = (Collection)leftOperand;
			try {
				literalList.add(rightOperand);
				return literalList;
			} catch (UnsupportedOperationException e) { // 当集合不支持add方法时，即unmodified
				Collection newList = new ArrayList();
				newList.addAll(literalList);
				newList.add(rightOperand);
				return newList;
			}
		}

		Collection literalList = new ArrayList();
		literalList.add(leftOperand);
		literalList.add(rightOperand);
		return literalList;
	}

}
