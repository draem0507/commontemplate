package org.commontemplate.standard.operator.object;

import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;

public class ObjectPropertyOperatorHandlerTester extends BinaryOperatorHandlerTester {

	public void setUp() throws Exception {
		super.binaryOperatorHandler = new ObjectPropertyOperatorHandler();
	}

	public void testNull() throws Exception {
		assertEvaluation(null, null, null);
	}

	public void testNullProperty() throws Exception {
		assertEvaluation("aa", null, null);
	}

	public void testNullBean() throws Exception {
		assertEvaluation(null, "aa", null);
	}

	public void testNullToString() throws Exception {
		assertEvaluation(null, "toString", "null");
	}

	public void testStringTrim() throws Exception {
		assertEvaluation("  aa  bb ", "trim", "aa  bb");
	}

	public void testNumberToString() throws Exception {
		assertEvaluation(new Integer(5), "toString", "5");
	}
}
