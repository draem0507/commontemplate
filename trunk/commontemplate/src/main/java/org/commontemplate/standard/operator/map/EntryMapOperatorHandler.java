package org.commontemplate.standard.operator.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class EntryMapOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public EntryMapOperatorHandler() {
		super(Entry.class, Entry.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Map literalMap = new HashMap();
		Entry leftEntry = (Entry)leftOperand;
		literalMap.put(leftEntry.getKey(), leftEntry.getValue());
		Entry literalEntry = (Entry)rightOperand;
		literalMap.put(literalEntry.getKey(), literalEntry.getValue());
		return literalMap;
	}

}
