package org.commontemplate.standard.operator.collection;

import java.util.Collection;
import java.util.Iterator;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 集合中包含项匹配操作符: "~"<br/>
 * 如: $if{"a" ~ {"a", "b", "c"}}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class CollectionContainOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public CollectionContainOperatorHandler() {
		super(Object.class, Collection.class, true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		if (leftOperand == null || rightOperand == null)
			return Boolean.FALSE;
		Collection coll = (Collection)rightOperand;
		for (Iterator iterator = coll.iterator(); iterator.hasNext();) {
			Object obj = iterator.next();
			if (leftOperand.equals(obj))
				return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}