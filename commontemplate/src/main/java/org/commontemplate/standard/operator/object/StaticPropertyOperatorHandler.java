package org.commontemplate.standard.operator.object;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.BeanUtils;

public class StaticPropertyOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StaticPropertyOperatorHandler() {
		super(Class.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		Class clazz = (Class)leftOperand;
		String property = (String)rightOperand;
		return BeanUtils.getStaticProperty(clazz, property);
	}


}
