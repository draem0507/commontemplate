package org.commontemplate.standard.operator.object;

public abstract class NewInstanceOperatorHandlerSupport extends ClassNamedOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NewInstanceOperatorHandlerSupport(Class operandClass) {
		super(operandClass);
	}

	protected Object convertResult(Class clazz) throws Exception {
		return clazz.newInstance();
	}

}