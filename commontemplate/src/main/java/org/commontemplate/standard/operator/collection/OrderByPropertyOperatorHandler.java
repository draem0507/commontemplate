package org.commontemplate.standard.operator.collection;

import java.util.Collection;
import java.util.TreeSet;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 集合属性排序操作符: "orderby"<br/>
 * 如: ${users orderby "+name"}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class OrderByPropertyOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public OrderByPropertyOperatorHandler() {
		super(Collection.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Collection leftCollection = (Collection)leftOperand;
		String property = (String)rightOperand;
		TreeSet sort = new TreeSet(new PropertyComparator(property)) ;
		sort.addAll(leftCollection);
		return sort;
	}

}