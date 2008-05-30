package org.commontemplate.standard.property.number;

import junit.framework.TestCase;

/**
 * 数字转中文测试类
 *
 * @author YanRong
 *
 */
public class NumberToChinesePropertyHandlerTester extends TestCase {

	private NumberToChinesePropertyHandler numberToChinesePropertyHandler;

	protected void setUp() throws Exception {
		super.setUp();
		numberToChinesePropertyHandler = new NumberToChinesePropertyHandler();
	}

	public void testNumber() throws Exception {
		super.assertEquals("零", numberToChinesePropertyHandler.doProperty(new Integer(0)));
		super.assertEquals("负一", numberToChinesePropertyHandler.doProperty(new Integer(-1)));
		super.assertEquals("一", numberToChinesePropertyHandler.doProperty(new Integer(1)));
		super.assertEquals("负五", numberToChinesePropertyHandler.doProperty(new Integer(-5)));
		super.assertEquals("五", numberToChinesePropertyHandler.doProperty(new Integer(5)));
		super.assertEquals("负九", numberToChinesePropertyHandler.doProperty(new Integer(-9)));
		super.assertEquals("九", numberToChinesePropertyHandler.doProperty(new Integer(9)));
		super.assertEquals("一十", numberToChinesePropertyHandler.doProperty(new Integer(10)));
		super.assertEquals("二十", numberToChinesePropertyHandler.doProperty(new Integer(20)));
		super.assertEquals("一百", numberToChinesePropertyHandler.doProperty(new Integer(100)));
		super.assertEquals("负二百", numberToChinesePropertyHandler.doProperty(new Integer(-200)));
		super.assertEquals("二百", numberToChinesePropertyHandler.doProperty(new Integer(200)));
		super.assertEquals("二百一十", numberToChinesePropertyHandler.doProperty(new Integer(210)));
		super.assertEquals("二百九十五", numberToChinesePropertyHandler.doProperty(new Integer(295)));
		super.assertEquals("二亿三千一百万零一百", numberToChinesePropertyHandler.doProperty(new Integer(231000100)));
		super.assertEquals("二亿三千万零一百", numberToChinesePropertyHandler.doProperty(new Integer(230000100)));
		super.assertEquals("二亿三千万零一十", numberToChinesePropertyHandler.doProperty(new Integer(230000010)));
		super.assertEquals("二亿三千万", numberToChinesePropertyHandler.doProperty(new Integer(230000000)));
		super.assertEquals("二百零三", numberToChinesePropertyHandler.doProperty(new Integer(203)));
		super.assertEquals("二千零三", numberToChinesePropertyHandler.doProperty(new Integer(2003)));
		super.assertEquals("二万零三十", numberToChinesePropertyHandler.doProperty(new Integer(20030)));
		super.assertEquals("二百万零三十", numberToChinesePropertyHandler.doProperty(new Integer(2000030)));
		super.assertEquals("八百零二万零三", numberToChinesePropertyHandler.doProperty(new Integer(8020003)));
		super.assertEquals("八千零二万零三", numberToChinesePropertyHandler.doProperty(new Integer(80020003)));
		super.assertEquals("负八千零二万零三", numberToChinesePropertyHandler.doProperty(new Integer(-80020003)));

	}

}
