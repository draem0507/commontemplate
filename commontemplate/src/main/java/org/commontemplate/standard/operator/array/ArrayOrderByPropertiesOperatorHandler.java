package org.commontemplate.standard.operator.array;

import java.util.Arrays;
import java.util.List;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.standard.operator.collection.PropertiesComparator;

public class ArrayOrderByPropertiesOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ArrayOrderByPropertiesOperatorHandler() {
		super(Object[].class, List.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Arrays.sort((Object[])leftOperand, new PropertiesComparator((List)rightOperand));
		return leftOperand;
	}

}