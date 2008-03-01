package org.commontemplate.standard.operator.object;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

public class ClassPackageOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ClassPackageOperatorHandler() {
		super(String.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		String name = (String)operand;
		try {
			return Class.forName(name);
		} catch (ClassNotFoundException e) {
			return Package.getPackage(name);
		}
	}

}