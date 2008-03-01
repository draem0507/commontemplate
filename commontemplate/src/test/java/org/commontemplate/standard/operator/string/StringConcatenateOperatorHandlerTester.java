package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;

public class StringConcatenateOperatorHandlerTester extends BinaryOperatorHandlerTester {

	public void setUp() throws Exception {
		super.binaryOperatorHandler = new StringConcatenateOperatorHandler();
	}

	public void testNull() throws Exception {
		assertEvaluation(null, null, "");
	}

	public void testEmpty() throws Exception {
		assertEvaluation("", "", "");
	}

	public void testNullAndEmpty() throws Exception {
		assertEvaluation(null, "", "");
		assertEvaluation("", null, "");
	}

	public void testString() throws Exception {
		assertEvaluation("aa", "bb", "aabb");
	}

	public void testNumber() throws Exception {
		assertEvaluation(new Integer(3), new Integer(5), "35");
		assertEvaluation(new Integer(3), null, "3");
		assertEvaluation(null, new Integer(5), "5");
	}

	public void testNumberAndString() throws Exception {
		assertEvaluation(new Integer(1), "bb", "1bb");
		assertEvaluation("aa", new Integer(1), "aa1");
	}

	public void testBoolean() throws Exception {
		assertEvaluation(Boolean.TRUE, Boolean.FALSE, "truefalse");
		assertEvaluation(Boolean.TRUE, null, "true");
		assertEvaluation(null, Boolean.FALSE, "false");
	}

}
