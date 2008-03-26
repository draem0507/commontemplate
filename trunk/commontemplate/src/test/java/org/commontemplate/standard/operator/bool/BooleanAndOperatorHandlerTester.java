package org.commontemplate.standard.operator.bool;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
import org.commontemplate.standard.operator.LazyOperandMock;

public class BooleanAndOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new BooleanAndOperatorHandler();
	}

	public void testNull() throws Exception {
		assertEvaluation(null, new LazyOperandMock(null), null);
	}

	public void testObject() throws Exception {
		assertEvaluation(new Integer(1), new LazyOperandMock("a"), "a");
	}

	public void testNullObject() throws Exception {
		assertEvaluation(null, new LazyOperandMock("a"), null);
	}

	public void testObjectNull() throws Exception {
		assertEvaluation(new Integer(1), new LazyOperandMock(null), null);
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
