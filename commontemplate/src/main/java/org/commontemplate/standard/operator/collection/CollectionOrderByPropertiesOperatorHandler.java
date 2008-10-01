package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 集合多属性排序操作符: "orderby"<br/>
 * 如: ${users orderby ("+name", "-registerDate")}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class CollectionOrderByPropertiesOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public CollectionOrderByPropertiesOperatorHandler() {
		super(Collection.class, List.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Collection leftCollection = (Collection)leftOperand;
		List properties = (List)rightOperand;
		List list = new ArrayList(leftCollection.size());
		list.addAll(leftCollection);
		Collections.sort(list, new PropertiesComparator(properties));
		return list;
	}

}