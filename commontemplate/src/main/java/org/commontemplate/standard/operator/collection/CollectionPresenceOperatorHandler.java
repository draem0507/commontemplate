package org.commontemplate.standard.operator.collection;

import java.util.Collection;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

public class CollectionPresenceOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public CollectionPresenceOperatorHandler() {
		super(Collection.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		Collection c = (Collection)operand;
		return Boolean.valueOf(c != null && c.size() > 0);
	}

}