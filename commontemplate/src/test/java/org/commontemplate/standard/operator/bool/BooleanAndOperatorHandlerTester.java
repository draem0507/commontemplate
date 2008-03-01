package org.commontemplate.standard.operator.bool;

import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
import org.commontemplate.standard.operator.LazyOperandMock;

public class BooleanAndOperatorHandlerTester extends BinaryOperatorHandlerTester {

	public void setUp() throws Exception {
		super.binaryOperatorHandler = new BooleanAndOperatorHandler();
	}

	public void testNull() throws Exception {
		assertEvaluation(null, new LazyOperandMock(null), Boolean.FALSE);
	}

	public void testObject() throws Exception {
		assertEvaluation(new Integer(1), new LazyOperandMock("a"), Boolean.TRUE);
	}

	public void testNullObject() throws Exception {
		assertEvaluation(null, new LazyOperandMock("a"), Boolean.FALSE);
	}

	public void testObjectNull() throws Exception {
		assertEvaluation(new Integer(1), new LazyOperandMock(null), Boolean.FALSE);
	}

	public void testTrue() throws Exception {
		assertEvaluation(Boolean.TRUE, new LazyOperandMock(Boolean.TRUE), Boolean.TRUE);
	}

	public void testFalse() throws Exception {
		assertEvaluation(Boolean.FALSE, new LazyOperandMock(Boolean.FALSE), Boolean.FALSE);
	}

	public void testTrueFalse() throws Exception {
		assertEvaluation(Boolean.TRUE, new LazyOperandMock(Boolean.FALSE), Boolean.FALSE);
	}

	public void testFalseTrue() throws Exception {
		assertEvaluation(Boolean.FALSE, new LazyOperandMock(Boolean.TRUE), Boolean.FALSE);
	}

}
