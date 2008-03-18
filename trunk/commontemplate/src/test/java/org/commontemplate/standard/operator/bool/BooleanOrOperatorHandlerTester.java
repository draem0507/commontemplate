package org.commontemplate.standard.operator.bool;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
import org.commontemplate.standard.operator.LazyOperandMock;

public class BooleanOrOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new BooleanOrOperatorHandler();
	}

	public void testNull() throws Exception {
		assertEvaluation(null, new LazyOperandMock(null), null);
	}

	public void testObject() throws Exception {
		assertEvaluation(new Integer(1), new LazyOperandMock("a"), new Integer(1));
	}

	public void testNullObject() throws Exception {
		assertEvaluation(null, new LazyOperandMock("a"), "a");
	}

	public void testObjectNull() throws Exception {
		assertEvaluation(new Integer(1), new LazyOperandMock(null), new Integer(1));
	}

	public void testTrue() throws Exception {
		assertEvaluation(Boolean.TRUE, new LazyOperandMock(Boolean.TRUE), Boolean.TRUE);
	}

	public void testFalse() throws Exception {
		assertEvaluation(Boolean.FALSE, new LazyOperandMock(Boolean.FALSE), Boolean.FALSE);
	}

	public void testTrueFalse() throws Exception {
		assertEvaluation(Boolean.TRUE, new LazyOperandMock(Boolean.FALSE), Boolean.TRUE);
	}

	public void testFalseTrue() throws Exception {
		assertEvaluation(Boolean.FALSE, new LazyOperandMock(Boolean.TRUE), Boolean.TRUE);
	}

}
