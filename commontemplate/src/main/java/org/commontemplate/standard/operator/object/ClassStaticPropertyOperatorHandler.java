package org.commontemplate.standard.operator.object;


public class ClassStaticPropertyOperatorHandler extends ClassStaticOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ClassStaticPropertyOperatorHandler() {
		super(String.class);
	}

	protected String getOperandName(Object operand) {
		return (String)operand;
	}

}