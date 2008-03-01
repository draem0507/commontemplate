package org.commontemplate.standard.operator.collection;

import org.commontemplate.config.LazyOperand;
import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

public class FilterOperatorHandler extends UnaryOperatorHandlerSupport {

	public FilterOperatorHandler() {
		super(LazyOperand.class);
	}

	private static final long serialVersionUID = 1L;

	public Object doEvaluate(Object operand) throws Exception {
		return new Filter(((LazyOperand)operand).getExpression());
	}

}