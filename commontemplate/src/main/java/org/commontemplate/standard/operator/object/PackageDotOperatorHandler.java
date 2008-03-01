package org.commontemplate.standard.operator.object;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class PackageDotOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public PackageDotOperatorHandler() {
		super(Package.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		Package superPackage = (Package)leftOperand;
		String subName = (String)rightOperand;
		String name = superPackage.getName() + "." + subName;
		try {
			return Class.forName(name);
		} catch (ClassNotFoundException e) {
			return Package.getPackage(name);
		}
	}

}
