package org.commontemplate.standard.operator.object;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.ClassUtils;

public class ClassStaticPropertyOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ClassStaticPropertyOperatorHandler() {
		super(Class.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		return ClassUtils.getStaticProperty((Class)leftOperand, (String)rightOperand);
	}

}
