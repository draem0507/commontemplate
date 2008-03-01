package org.commontemplate.standard.operator.collection;

import java.util.Collection;
import java.util.Iterator;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class ContainOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ContainOperatorHandler() {
		super(Object.class, Collection.class, true);
	}
	
	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		if (leftOperand == null || rightOperand == null)
			return Boolean.FALSE;
		Collection c = (Collection)rightOperand;
		for (Iterator iterator = c.iterator(); iterator.hasNext();) {
			Object o = iterator.next();
			if (leftOperand.equals(o))
				return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}