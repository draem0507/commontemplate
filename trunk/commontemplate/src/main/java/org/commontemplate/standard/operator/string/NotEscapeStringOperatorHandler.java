package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;
import org.commontemplate.util.StringConvertUtils;

public class NotEscapeStringOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NotEscapeStringOperatorHandler() {
		super(String.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		return StringConvertUtils.revertLiteral((String)operand); // 将被引擎转义后的字符串转回来
	}

}