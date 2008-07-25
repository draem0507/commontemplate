package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.StringUtils;

public class StringIgnoreCaseStartsWithOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringIgnoreCaseStartsWithOperatorHandler() {
		super(String.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return Boolean.valueOf(StringUtils.startsWithIgnoreCase((String)leftOperand, (String)rightOperand));
	}

}