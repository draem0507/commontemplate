package org.commontemplate.standard.operator.array;

import org.commontemplate.util.TypeUtils;

public class ArrayNotContainOperatorHandler extends ArrayContainOperatorHandler {

	private static final long serialVersionUID = 1L;

	public ArrayNotContainOperatorHandler() {
		super();
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf(! TypeUtils.isTrue(super.doEvaluate(leftOperand, rightOperand)));
	}

}
