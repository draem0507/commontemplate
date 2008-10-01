package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 集合属性排序操作符: "orderby"<br/>
 * 如: ${users orderby "+name"}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class CollectionOrderByPropertyOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public CollectionOrderByPropertyOperatorHandler() {
		super(Collection.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Collection leftCollection = (Collection)leftOperand;
		String property = (String)rightOperand;
		List list = new ArrayList(leftCollection.size());
		list.addAll(leftCollection);
		Collections.sort(list, new PropertyComparator(property));
		return list;
	}

}