package org.commontemplate.standard.operator.context;

import org.commontemplate.core.LocalContext;
import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class LocalContextGetterOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public LocalContextGetterOperatorHandler() {
		super(LocalContext.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return ((LocalContext)leftOperand).lookupVariable((String)rightOperand);
	}

}