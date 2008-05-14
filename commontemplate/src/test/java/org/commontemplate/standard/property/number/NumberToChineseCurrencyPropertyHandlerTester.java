package org.commontemplate.standard.property.number;

import junit.framework.TestCase;

/**
 * 数字转中文货币测试类
 *
 * @author YanRong
 *
 */
public class NumberToChineseCurrencyPropertyHandlerTester extends TestCase {

	private NumberToChineseCurrencyPropertyHandler numberToChineseCurrencyPropertyHandler;

	protected void setUp() throws Exception {
		super.setUp();
		numberToChineseCurrencyPropertyHandler = new NumberToChineseCurrencyPropertyHandler();
	}

	public void testCurrency() throws Exception {
		super.assertEquals("零圆整", numberToChineseCurrencyPropertyHandler.getProperty(new Integer(0)));
		super.assertEquals("壹仟零壹圆整", numberToChineseCurrencyPropertyHandler.getProperty(new Integer(1001)));
		super.assertEquals("负壹仟零壹圆整", numberToChineseCurrencyPropertyHandler.getProperty(new Integer(-1001)));
		super.assertEquals("壹佰圆捌角玖分", numberToChineseCurrencyPropertyHandler.getProperty(new Float(100.89)));
		super.assertEquals("壹仟零贰圆捌角玖分", numberToChineseCurrencyPropertyHandler.getProperty(new Float(1002.89)));
		super.assertEquals("捌佰零贰万零叁圆整", numberToChineseCurrencyPropertyHandler.getProperty(new Integer(8020003)));
		super.assertEquals("贰亿叁仟万圆整", numberToChineseCurrencyPropertyHandler.getProperty(new Integer(230000000)));
		super.assertEquals("负贰亿叁仟万圆整", numberToChineseCurrencyPropertyHandler.getProperty(new Integer(-230000000)));
	}

}
