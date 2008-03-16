package org.commontemplate.standard.operator.collection;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class OrderByPropertiesOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public OrderByPropertiesOperatorHandler() {
		super(Collection.class, List.class);
	}
	
	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Collection leftCollection = (Collection)leftOperand;
		List properties = (List)rightOperand;
		TreeSet sort = new TreeSet(new PropertiesComparator(properties)) ;
		sort.addAll(leftCollection);
		return sort;
	}

}