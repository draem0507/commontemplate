package org.commontemplate.standard.operator.map;

import org.commontemplate.util.TypeUtils;

public class MapNotContainOperatorHandler extends MapContainOperatorHandler {

	private static final long serialVersionUID = 1L;

	public MapNotContainOperatorHandler() {
		super();
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf(! TypeUtils.isTrue(super.doEvaluate(leftOperand, rightOperand)));
	}

}