package org.commontemplate.standard.filter;

import org.commontemplate.config.InvalidTemplateNameException;
import org.commontemplate.standard.filter.TemplateNameRelativer;

import junit.framework.TestCase;

public class PathRelativerTester extends TestCase {
	
	private TemplateNameRelativer pathRelativer;
	
	public void setUp() {
		pathRelativer = new TemplateNameRelativer();
	}

	public void testNullPath() {
		try {
			pathRelativer.clean(null);
			fail("传入空值，应该抛出异常!");
		} catch (NullPointerException e) {
			// right
		}
	}
	
	public void testEmptyPath() {
		assertEquals("", pathRelativer.clean(""));
	}
	
	public void testOverPath() {
		try {
			pathRelativer.clean("/aa/../../cc/dd");
			fail("路径越界，应该抛出异常!");
		} catch (InvalidTemplateNameException e) {
			// right
		}
	}

	public void testParentPath() {
		assertEquals("/aa/cc/dd", pathRelativer.clean("/aa/bb/../cc/dd"));
	}

	public void testRootPath() {
		assertEquals("/cc/dd", pathRelativer.clean("/aa/../cc/dd"));
	}
	
	public void testCurrentPath() {
		assertEquals("/aa/bb/cc/dd", pathRelativer.clean("/aa/bb/./cc/dd"));
	}
	
}
