package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.StringUtils;

public class StringRemoveOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringRemoveOperatorHandler() {
		super(String.class, String.class, true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		if (leftOperand == null)
			return null;
		if (rightOperand == null)
			return leftOperand;
		return StringUtils.remove((String)leftOperand, (String)rightOperand);
	}

}