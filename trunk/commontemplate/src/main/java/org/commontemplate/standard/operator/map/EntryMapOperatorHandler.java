package org.commontemplate.standard.operator.map;

import java.util.Map.Entry;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.MapEntry;

public class EntryMapOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public EntryMapOperatorHandler() {
		super(MapEntry.class, MapEntry.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
	    LiteralMap literalMap = new LiteralMap();
		Entry leftEntry = (Entry)leftOperand;
		literalMap.put(leftEntry.getKey(), leftEntry.getValue());
		Entry literalEntry = (Entry)rightOperand;
		literalMap.put(literalEntry.getKey(), literalEntry.getValue());
		return literalMap;
	}

}
