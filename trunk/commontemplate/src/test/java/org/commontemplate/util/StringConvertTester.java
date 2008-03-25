package org.commontemplate.util;

import junit.framework.TestCase;

public class StringConvertTester extends TestCase {

	public void testConvert() {
		super.assertEquals("C:\native\room\u5678\\user\\java\\3333\file.txt", StringConvertUtils.convertLiteral("C:\\native\\room\\u5678\\user\\java\\3333\\file.txt"));
	}

}
