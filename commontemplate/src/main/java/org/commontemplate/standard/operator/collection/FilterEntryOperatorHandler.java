package org.commontemplate.standard.operator.collection;

import org.commontemplate.config.LazyOperand;
import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class FilterEntryOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;
	
	public FilterEntryOperatorHandler() {
		super(Object.class, Object.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return new Filter(String.valueOf(leftOperand), ((LazyOperand)rightOperand).getExpression());
	}
}