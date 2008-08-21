package org.commontemplate.standard.operator.object;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.ClassUtils;

public class DotInstanceStringOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public DotInstanceStringOperatorHandler() {
		super(NewPackage.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		NewPackage pkg = (NewPackage)leftOperand;
		String str = (String)rightOperand;
		String name = pkg.getNewPackage().getName() + "." + str;
		try {
			return ClassUtils.forName(name).newInstance();
		} catch (ClassNotFoundException e) {
			return new NewPackage(Package.getPackage(name));
		} catch (InstantiationException e) {
			return new NewPackage(Package.getPackage(name));
		} catch (IllegalAccessException e) {
			return new NewPackage(Package.getPackage(name));
		}
	}

}
