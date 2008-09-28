package org.commontemplate.standard.operator.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class MapOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public MapOperatorHandler() {
		super(Map.class, Entry.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Map literalMap = (Map)leftOperand;
		Entry literalEntry = (Entry)rightOperand;
		try {
			literalMap.put(literalEntry.getKey(), literalEntry.getValue());
			return literalMap;
		} catch (UnsupportedOperationException e) { // 当Map不支持put方法时，即unmodified
			Map newMap = new HashMap();
			newMap.putAll(literalMap);
			newMap.put(literalEntry.getKey(), literalEntry.getValue());
			return newMap;
		}
	}

}
