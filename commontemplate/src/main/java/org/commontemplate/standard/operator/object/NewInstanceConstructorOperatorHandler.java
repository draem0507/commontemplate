package org.commontemplate.standard.operator.object;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;
import org.commontemplate.util.ClassUtils;
import org.commontemplate.util.Function;

public class NewInstanceConstructorOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NewInstanceConstructorOperatorHandler() {
		super(Function.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		Function function = (Function)operand;
		String name = function.getName();
		Object args = function.getArgument();
		Class cls = ClassUtils.forName(name);
		return ObjectHandlerUtils.invokeConstructor(cls, args);
	}

}