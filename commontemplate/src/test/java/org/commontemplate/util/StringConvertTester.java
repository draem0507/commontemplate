package org.commontemplate.util;

import junit.framework.TestCase;

public class StringConvertTester extends TestCase {

	public void testConvertEscape() {
		super.assertEquals("\n", StringConvertUtils.convertLiteral("\\n"));
		super.assertEquals("\t", StringConvertUtils.convertLiteral("\\t"));
		super.assertEquals("\f", StringConvertUtils.convertLiteral("\\f"));
		super.assertEquals("\r", StringConvertUtils.convertLiteral("\\r"));
		super.assertEquals("\\", StringConvertUtils.convertLiteral("\\\\"));
		super.assertEquals("\u1234", StringConvertUtils.convertLiteral("\\u1234"));

		super.assertEquals("aaa\n", StringConvertUtils.convertLiteral("aaa\\n"));
		super.assertEquals("aaa\t", StringConvertUtils.convertLiteral("aaa\\t"));
		super.assertEquals("aaa\f", StringConvertUtils.convertLiteral("aaa\\f"));
		super.assertEquals("aaa\r", StringConvertUtils.convertLiteral("aaa\\r"));
		super.assertEquals("aaa\\", StringConvertUtils.convertLiteral("aaa\\\\"));
		super.assertEquals("aaa\u1234", StringConvertUtils.convertLiteral("aaa\\u1234"));

		super.assertEquals("\nbbb", StringConvertUtils.convertLiteral("\\nbbb"));
		super.assertEquals("\tbbb", StringConvertUtils.convertLiteral("\\tbbb"));
		super.assertEquals("\fbbb", StringConvertUtils.convertLiteral("\\fbbb"));
		super.assertEquals("\rbbb", StringConvertUtils.convertLiteral("\\rbbb"));
		super.assertEquals("\\bbb", StringConvertUtils.convertLiteral("\\\\bbb"));
		super.assertEquals("\u1234bbb", StringConvertUtils.convertLiteral("\\u1234bbb"));

		super.assertEquals("aaa\nbbb", StringConvertUtils.convertLiteral("aaa\\nbbb"));
		super.assertEquals("aaa\tbbb", StringConvertUtils.convertLiteral("aaa\\tbbb"));
		super.assertEquals("aaa\fbbb", StringConvertUtils.convertLiteral("aaa\\fbbb"));
		super.assertEquals("aaa\rbbb", StringConvertUtils.convertLiteral("aaa\\rbbb"));
		super.assertEquals("aaa\\bbb", StringConvertUtils.convertLiteral("aaa\\\\bbb"));
		super.assertEquals("aaa\u1234bbb", StringConvertUtils.convertLiteral("aaa\\u1234bbb"));
	}

	public void testConvertPath() {
		super.assertEquals("C:\native\room\u5678\\user\\java\\3333\file.txt",
				StringConvertUtils.convertLiteral("C:\\native\\room\\u5678\\user\\java\\3333\\file.txt"));
	}

	public void testRevertPath() {
		super.assertEquals("C:\\native\\room\\u5678\\user\\java\\3333\\file.txt",
				StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("C:\\native\\room\\u5678\\user\\java\\3333\\file.txt")));
	}

}
