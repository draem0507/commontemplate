package org.commontemplate.util;

import java.net.MalformedURLException;

import junit.framework.TestCase;

public class UrlCleanerTester extends TestCase {

	public void testNullPath() throws Exception {
		try {
			UrlUtils.cleanUrl(null);
			fail("传入空值，应该抛出异常!");
		} catch (MalformedURLException e) {
			// right
		}
	}

	public void testEmptyPath() throws Exception {
		assertEquals("", UrlUtils.cleanUrl(""));
	}

	public void testParentPath() throws Exception {
		assertEquals("/aa/cc/dd", UrlUtils.cleanUrl("/aa/bb/../cc/dd"));
	}

	public void testRootPath() throws Exception {
		assertEquals("/cc/dd", UrlUtils.cleanUrl("/aa/../cc/dd"));
	}

	public void testCurrentPath() throws Exception {
		assertEquals("/aa/bb/cc/dd", UrlUtils.cleanUrl("/aa/bb/./cc/dd"));
	}

	public void testOverPath() throws Exception {
		try {
			UrlUtils.cleanUrl("/aa/../../cc/dd");
			fail("路径越界，应该抛出异常!");
		} catch (MalformedURLException e) {
			// right
		}
	}

	public void testDomainParentPath() throws Exception {
		assertEquals("http://localhost:8080/aa/cc/dd", UrlUtils.cleanUrl("http://localhost:8080/aa/bb/../cc/dd"));
	}

	public void testDomainRootPath() throws Exception {
		assertEquals("http://localhost:8080/cc/dd", UrlUtils.cleanUrl("http://localhost:8080/aa/../cc/dd"));
	}

	public void testDomainCurrentPath() throws Exception {
		assertEquals("http://localhost:8080/aa/bb/cc/dd", UrlUtils.cleanUrl("http://localhost:8080/aa/bb/./cc/dd"));
	}

	public void testDomainOverPath() throws Exception {
		try {
			UrlUtils.cleanUrl("http://localhost:8080/aa/../../cc/dd");
			fail("路径越界，应该抛出异常!");
		} catch (MalformedURLException e) {
			// right
		}
	}

	public void testFileName() throws Exception {
		assertEquals("aa", UrlUtils.getFileName("aa"));
		assertEquals("aa.txt", UrlUtils.getFileName("aa.txt"));
		assertEquals("aa", UrlUtils.getFileName("/aa"));
		assertEquals("aa.txt", UrlUtils.getFileName("/aa.txt"));
		assertEquals("bb.txt", UrlUtils.getFileName("/aa/bb.txt"));
	}

	public void testSimpleName() throws Exception {
		assertEquals("aa", UrlUtils.getSimpleName("aa"));
		assertEquals("aa", UrlUtils.getSimpleName("aa.txt"));
		assertEquals("aa", UrlUtils.getSimpleName("/aa"));
		assertEquals("aa", UrlUtils.getSimpleName("/aa.txt"));
		assertEquals("bb", UrlUtils.getSimpleName("/aa/bb.txt"));
	}

}
