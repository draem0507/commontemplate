package org.commontemplate.util;

import junit.framework.TestCase;

public class StringConvertTester extends TestCase {

	public void testConvertEscape() {
		super.assertEquals("\n", StringConvertUtils.convertLiteral("\\n"));
		super.assertEquals("\t", StringConvertUtils.convertLiteral("\\t"));
		super.assertEquals("\f", StringConvertUtils.convertLiteral("\\f"));
		super.assertEquals("\r", StringConvertUtils.convertLiteral("\\r"));
		super.assertEquals("\b", StringConvertUtils.convertLiteral("\\b"));
		super.assertEquals("\u1234", StringConvertUtils.convertLiteral("\\u1234"));
		super.assertEquals("\\users", StringConvertUtils.convertLiteral("\\users"));
		super.assertEquals("\\", StringConvertUtils.convertLiteral("\\\\"));
		super.assertEquals("\"", StringConvertUtils.convertLiteral("\\\""));
		super.assertEquals("\'", StringConvertUtils.convertLiteral("\\\'"));

		super.assertEquals("aaa\n", StringConvertUtils.convertLiteral("aaa\\n"));
		super.assertEquals("aaa\t", StringConvertUtils.convertLiteral("aaa\\t"));
		super.assertEquals("aaa\f", StringConvertUtils.convertLiteral("aaa\\f"));
		super.assertEquals("aaa\r", StringConvertUtils.convertLiteral("aaa\\r"));
		super.assertEquals("aaa\b", StringConvertUtils.convertLiteral("aaa\\b"));
		super.assertEquals("aaa\u1234", StringConvertUtils.convertLiteral("aaa\\u1234"));
		super.assertEquals("aaa\\users", StringConvertUtils.convertLiteral("aaa\\users"));
		super.assertEquals("aaa\\", StringConvertUtils.convertLiteral("aaa\\\\"));
		super.assertEquals("aaa\"", StringConvertUtils.convertLiteral("aaa\\\""));
		super.assertEquals("aaa\'", StringConvertUtils.convertLiteral("aaa\\\'"));

		super.assertEquals("\nccc", StringConvertUtils.convertLiteral("\\nccc"));
		super.assertEquals("\tccc", StringConvertUtils.convertLiteral("\\tccc"));
		super.assertEquals("\fccc", StringConvertUtils.convertLiteral("\\fccc"));
		super.assertEquals("\rccc", StringConvertUtils.convertLiteral("\\rccc"));
		super.assertEquals("\bccc", StringConvertUtils.convertLiteral("\\bccc"));
		super.assertEquals("\u1234ccc", StringConvertUtils.convertLiteral("\\u1234ccc"));
		super.assertEquals("\\usersccc", StringConvertUtils.convertLiteral("\\usersccc"));
		super.assertEquals("\\ccc", StringConvertUtils.convertLiteral("\\\\ccc"));
		super.assertEquals("\"ccc", StringConvertUtils.convertLiteral("\\\"ccc"));
		super.assertEquals("\'ccc", StringConvertUtils.convertLiteral("\\\'ccc"));

		super.assertEquals("aaa\nccc", StringConvertUtils.convertLiteral("aaa\\nccc"));
		super.assertEquals("aaa\tccc", StringConvertUtils.convertLiteral("aaa\\tccc"));
		super.assertEquals("aaa\fccc", StringConvertUtils.convertLiteral("aaa\\fccc"));
		super.assertEquals("aaa\rccc", StringConvertUtils.convertLiteral("aaa\\rccc"));
		super.assertEquals("aaa\bccc", StringConvertUtils.convertLiteral("aaa\\bccc"));
		super.assertEquals("aaa\u1234ccc", StringConvertUtils.convertLiteral("aaa\\u1234ccc"));
		super.assertEquals("aaa\\usersccc", StringConvertUtils.convertLiteral("aaa\\usersccc"));
		super.assertEquals("aaa\\ccc", StringConvertUtils.convertLiteral("aaa\\\\ccc"));
		super.assertEquals("aaa\"ccc", StringConvertUtils.convertLiteral("aaa\\\"ccc"));
		super.assertEquals("aaa\'ccc", StringConvertUtils.convertLiteral("aaa\\\'ccc"));

	}

	public void testRevertEscape() {
		super.assertEquals("\\n", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("\\n")));
		super.assertEquals("\\t", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("\\t")));
		super.assertEquals("\\f", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("\\f")));
		super.assertEquals("\\r", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("\\r")));
		super.assertEquals("\\b", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("\\b")));
		super.assertEquals("\\u1234", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("\\u1234")));
		super.assertEquals("\\users", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("\\users")));
		super.assertEquals("\\", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("\\\\")));
		super.assertEquals("\"", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("\\\"")));
		super.assertEquals("\'", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("\\\'")));

		super.assertEquals("aaa\\nccc", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("aaa\\nccc")));
		super.assertEquals("aaa\\tccc", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("aaa\\tccc")));
		super.assertEquals("aaa\\fccc", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("aaa\\fccc")));
		super.assertEquals("aaa\\rccc", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("aaa\\rccc")));
		super.assertEquals("aaa\\bccc", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("aaa\\bccc")));
		super.assertEquals("aaa\\u1234ccc", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("aaa\\u1234ccc")));
		super.assertEquals("aaa\\usersccc", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("aaa\\usersccc")));
		super.assertEquals("aaa\\ccc", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("aaa\\\\ccc")));
		super.assertEquals("aaa\"ccc", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("aaa\\\"ccc")));
		super.assertEquals("aaa\'ccc", StringConvertUtils.revertLiteral(StringConvertUtils.convertLiteral("aaa\\\'ccc")));

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
