package org.commontemplate.standard.operator.number;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;

public class NumberDivideOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new NumberDivideOperatorHandler();
	}

	public void testByteDivide() throws Exception {
		assertEvaluation(new Byte((byte)6), new Byte((byte)2), new Integer(3));
	}

	public void testShortDivide() throws Exception {
		assertEvaluation(new Short((short)6), new Short((short)2), new Integer(3));
	}

	public void testIntegerDivide() throws Exception {
		assertEvaluation(new Integer(6), new Integer(2), new Integer(3));
	}

	public void testIntegerLessDivide() throws Exception {
		assertEvaluation(new Integer(2), new Integer(3), new Integer(0));
	}

	public void testLongDivide() throws Exception {
		assertEvaluation(new Long(6L), new Long(2L), new Long(3L));
	}

	public void testFloatDivide() throws Exception {
		assertEvaluation(new Float(6.0F), new Float(2.0F), new Float(3.0F));
	}

	public void testDoubleDivide() throws Exception {
		assertEvaluation(new Double(6.0D), new Double(2.0D), new Double(3.0D));
	}

}
