package org.commontemplate.standard.operator.object;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.standard.operator.UnhandleException;

public class InstanceofOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public InstanceofOperatorHandler() {
		super(Object.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		try {
			Class cls = Class.forName((String)rightOperand);
			return Boolean.valueOf(leftOperand.getClass().isAssignableFrom(cls));
		} catch (ClassNotFoundException e) {
			throw new UnhandleException(e);
		}
	}

}