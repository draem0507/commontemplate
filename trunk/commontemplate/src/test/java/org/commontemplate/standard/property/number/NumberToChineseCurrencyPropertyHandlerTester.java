package org.commontemplate.standard.property.number;

import junit.framework.TestCase;

public class NumberToChineseCurrencyPropertyHandlerTester extends TestCase {

	private NumberToChineseCurrencyPropertyHandler numberToChineseCurrencyPropertyHandler;

	protected void setUp() throws Exception {
		super.setUp();
		numberToChineseCurrencyPropertyHandler = new NumberToChineseCurrencyPropertyHandler();
	}

	public void testCurrency() throws Exception {
		super.assertEquals("零圆整", numberToChineseCurrencyPropertyHandler.getProperty(new Integer(0)));
		super.assertEquals("壹仟零壹圆整", numberToChineseCurrencyPropertyHandler.getProperty(new Integer(1001)));
		super.assertEquals("壹佰圆捌角玖分", numberToChineseCurrencyPropertyHandler.getProperty(new Float(100.89)));
		super.assertEquals("壹仟零贰圆捌角玖分", numberToChineseCurrencyPropertyHandler.getProperty(new Float(1002.89)));
	}

}
