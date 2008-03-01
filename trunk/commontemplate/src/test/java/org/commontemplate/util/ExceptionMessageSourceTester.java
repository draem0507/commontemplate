package org.commontemplate.util;

import junit.framework.TestCase;

public class ExceptionMessageSourceTester extends TestCase {

	public void testGetMessage() {
		super.assertEquals("test message", ExceptionMessageSource.getMessage("test"));
	}
	
	public void testChangeBaseName() {
		super.assertEquals("test message", ExceptionMessageSource.getMessage("test"));
	}

}
