package org.commontemplate.standard.operator.map;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.standard.operator.collection.PropertiesComparator;

public class MapOrderByPropertiesOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public MapOrderByPropertiesOperatorHandler() {
		super(Map.class, List.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Map leftMap = (Map)leftOperand;
		List properties = (List)rightOperand;
		TreeMap sort = new TreeMap(new PropertiesComparator(properties)) ;
		sort.putAll(leftMap);
		return sort;
	}

}