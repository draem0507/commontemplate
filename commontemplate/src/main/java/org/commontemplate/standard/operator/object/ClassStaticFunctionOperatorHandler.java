package org.commontemplate.standard.operator.object;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.ClassUtils;
import org.commontemplate.util.Function;

public class ClassStaticFunctionOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ClassStaticFunctionOperatorHandler() {
		super(Class.class, Function.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		Class clazz = (Class)leftOperand;
		Function func = (Function)rightOperand;
		return ClassUtils.getStaticFunction(clazz, func.getName(), func.getArguments().toArray());
	}

}
