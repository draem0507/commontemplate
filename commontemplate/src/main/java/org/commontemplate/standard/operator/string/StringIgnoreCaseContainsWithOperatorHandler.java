package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.StringUtils;

public class StringIgnoreCaseContainsWithOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringIgnoreCaseContainsWithOperatorHandler() {
		super(String.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf(StringUtils.containsIgnoreCase((String)leftOperand, (String)rightOperand));
	}

}