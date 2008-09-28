package org.commontemplate.standard.operator.map;

import java.util.Iterator;
import java.util.Map;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class MapContainOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public MapContainOperatorHandler() {
		super(Object.class, Map.class, true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		if (leftOperand == null || rightOperand == null)
			return Boolean.FALSE;
		Map map = (Map)rightOperand;
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			Object obj = iterator.next();
			if (leftOperand.equals(obj))
				return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}