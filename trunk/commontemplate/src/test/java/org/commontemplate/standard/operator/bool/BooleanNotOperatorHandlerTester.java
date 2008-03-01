package org.commontemplate.standard.operator.bool;

import org.commontemplate.standard.operator.UnaryOperatorHandlerTester;

public class BooleanNotOperatorHandlerTester extends UnaryOperatorHandlerTester {

	public void setUp() throws Exception {
		super.unaryOperatorHandler = new BooleanNotOperatorHandler();
	}

	public void testNull() throws Exception {
		super.assertEvaluation(null, Boolean.TRUE);
	}

	public void testInteger() throws Exception {
		super.assertEvaluation(new Integer(2), Boolean.FALSE);
	}

	public void testZero() throws Exception {
		super.assertEvaluation(new Integer(0), Boolean.TRUE);
	}

	public void testString() throws Exception {
		super.assertEvaluation("a", Boolean.FALSE);
	}

	public void testEmptyString() throws Exception {
		super.assertEvaluation("", Boolean.TRUE);
	}

	public void testTrue() throws Exception {
		super.assertEvaluation(Boolean.TRUE, Boolean.FALSE);
	}

	public void testFlase() throws Exception {
		super.assertEvaluation(Boolean.FALSE, Boolean.TRUE);
	}

}
