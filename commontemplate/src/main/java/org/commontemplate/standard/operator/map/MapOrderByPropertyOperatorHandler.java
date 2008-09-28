package org.commontemplate.standard.operator.map;

import java.util.Map;
import java.util.TreeMap;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.standard.operator.collection.PropertyComparator;

public class MapOrderByPropertyOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public MapOrderByPropertyOperatorHandler() {
		super(Map.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Map leftMap = (Map)leftOperand;
		String property = (String)rightOperand;
		TreeMap sort = new TreeMap(new PropertyComparator(property)) ;
		sort.putAll(leftMap);
		return sort;
	}

}