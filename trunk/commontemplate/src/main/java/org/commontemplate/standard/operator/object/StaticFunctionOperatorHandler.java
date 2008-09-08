package org.commontemplate.standard.operator.object;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.ArgumentUtils;
import org.commontemplate.util.ClassUtils;
import org.commontemplate.util.Function;

public class StaticFunctionOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StaticFunctionOperatorHandler() {
		super(Class.class, Function.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		Class clazz = (Class)leftOperand;
		Function function = (Function)rightOperand;
		String name = function.getName();
		Object[] args = ArgumentUtils.getArgumentArray(function.getArgument());
		return ClassUtils.invokeStaticMethod(clazz, name, args);
	}


}
