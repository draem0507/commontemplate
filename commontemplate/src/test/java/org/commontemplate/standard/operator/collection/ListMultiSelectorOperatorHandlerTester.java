package org.commontemplate.standard.operator.collection;

import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;

public class ListMultiSelectorOperatorHandlerTester extends BinaryOperatorHandlerTester {

	public void setUp() throws Exception {
		super.binaryOperatorHandler = new ListMultiSelectorOperatorHandler();
	}

	public void testNull() throws Exception {
		//assertEvaluation(null, null, null);
	}

}
