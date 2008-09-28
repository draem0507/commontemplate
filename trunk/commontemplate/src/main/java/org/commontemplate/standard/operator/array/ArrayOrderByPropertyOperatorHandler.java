package org.commontemplate.standard.operator.array;

import java.util.Arrays;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.standard.operator.collection.PropertyComparator;

public class ArrayOrderByPropertyOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ArrayOrderByPropertyOperatorHandler() {
		super(Object[].class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Arrays.sort((Object[])leftOperand, new PropertyComparator((String)rightOperand));
		return leftOperand;
	}

}