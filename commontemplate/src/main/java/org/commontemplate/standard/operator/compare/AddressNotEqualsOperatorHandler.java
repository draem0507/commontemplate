package org.commontemplate.standard.operator.compare;

import org.commontemplate.util.TypeUtils;

public class AddressNotEqualsOperatorHandler extends AddressEqualsOperatorHandler {

	private static final long serialVersionUID = 1L;

	public AddressNotEqualsOperatorHandler() {
		super();
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf(! TypeUtils.isTrue(super.doEvaluate(leftOperand, rightOperand)));
	}

}