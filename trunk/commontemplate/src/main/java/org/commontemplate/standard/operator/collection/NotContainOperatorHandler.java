package org.commontemplate.standard.operator.collection;

import java.util.Collection;
import java.util.Iterator;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class NotContainOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NotContainOperatorHandler() {
		super(Object.class, Collection.class, true);
	}
	
	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		if (leftOperand == null || rightOperand == null)
			return Boolean.TRUE;
		Collection c = (Collection)rightOperand;
		for (Iterator iterator = c.iterator(); iterator.hasNext();) {
			Object o = iterator.next();
			if (leftOperand.equals(o))
				return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

}