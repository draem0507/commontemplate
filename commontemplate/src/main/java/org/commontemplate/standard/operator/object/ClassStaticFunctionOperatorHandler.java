package org.commontemplate.standard.operator.object;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;
import org.commontemplate.util.ArgumentUtils;
import org.commontemplate.util.ClassUtils;
import org.commontemplate.util.Function;

public class ClassStaticFunctionOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ClassStaticFunctionOperatorHandler() {
		super(Function.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		Function function = (Function)operand;
		String name = function.getName();
		int i = name.lastIndexOf('.');
		if (i >= 0 && i < name.length()) {
			String className = name.substring(0, i);
			String functionName = name.substring(i + 1);
			Class clazz = ClassUtils.forName(className);
			return ClassUtils.invokeStaticMethod(clazz, functionName, ArgumentUtils.getArgumentArray(function.getArgument()));
		}
		return ClassUtils.forName(function.getName());
	}

}