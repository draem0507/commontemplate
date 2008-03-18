package org.commontemplate.standard.operator.collection;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;

public class ListMultiSelectorOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new ListMultiSelectorOperatorHandler();
	}

	public void testNull() throws Exception {
		//assertEvaluation(null, null, null);
	}

}
