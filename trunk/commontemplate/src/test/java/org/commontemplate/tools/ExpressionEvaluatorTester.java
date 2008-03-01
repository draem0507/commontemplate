package org.commontemplate.tools;

import junit.framework.TestCase;

public class ExpressionEvaluatorTester extends TestCase {
	
	public void testInteger() {
		super.assertEquals(new Integer(3), new ExpressionEvaluator("a+b").put("a", 1).put("b", 2).evaluate());
	}

}
