package org.commontemplate.util;

import java.net.MalformedURLException;

import junit.framework.TestCase;

public class UrlCleanerTester extends TestCase {

	public void testNullPath() throws Exception {
		try {
			UrlCleaner.clean(null);
			fail("传入空值，应该抛出异常!");
		} catch (MalformedURLException e) {
			// right
		}
	}

	public void testEmptyPath() throws Exception {
		assertEquals("", UrlCleaner.clean(""));
	}

	public void testParentPath() throws Exception {
		assertEquals("/aa/cc/dd", UrlCleaner.clean("/aa/bb/../cc/dd"));
	}

	public void testRootPath() throws Exception {
		assertEquals("/cc/dd", UrlCleaner.clean("/aa/../cc/dd"));
	}

	public void testCurrentPath() throws Exception {
		assertEquals("/aa/bb/cc/dd", UrlCleaner.clean("/aa/bb/./cc/dd"));
	}

	public void testOverPath() throws Exception {
		try {
			UrlCleaner.clean("/aa/../../cc/dd");
			fail("路径越界，应该抛出异常!");
		} catch (MalformedURLException e) {
			// right
		}
	}

	public void testDomainParentPath() throws Exception {
		assertEquals("http://localhost:8080/aa/cc/dd", UrlCleaner.clean("http://localhost:8080/aa/bb/../cc/dd"));
	}

	public void testDomainRootPath() throws Exception {
		assertEquals("http://localhost:8080/cc/dd", UrlCleaner.clean("http://localhost:8080/aa/../cc/dd"));
	}

	public void testDomainCurrentPath() throws Exception {
		assertEquals("http://localhost:8080/aa/bb/cc/dd", UrlCleaner.clean("http://localhost:8080/aa/bb/./cc/dd"));
	}

	public void testDomainOverPath() throws Exception {
		try {
			UrlCleaner.clean("http://localhost:8080/aa/../../cc/dd");
			fail("路径越界，应该抛出异常!");
		} catch (MalformedURLException e) {
			// right
		}
	}

	public void testFileName() throws Exception {
		assertEquals("aa", UrlCleaner.getFileName("aa"));
		assertEquals("aa.txt", UrlCleaner.getFileName("aa.txt"));
		assertEquals("aa", UrlCleaner.getFileName("/aa"));
		assertEquals("aa.txt", UrlCleaner.getFileName("/aa.txt"));
		assertEquals("bb.txt", UrlCleaner.getFileName("/aa/bb.txt"));
	}

	public void testSimpleName() throws Exception {
		assertEquals("aa", UrlCleaner.getSimpleName("aa"));
		assertEquals("aa", UrlCleaner.getSimpleName("aa.txt"));
		assertEquals("aa", UrlCleaner.getSimpleName("/aa"));
		assertEquals("aa", UrlCleaner.getSimpleName("/aa.txt"));
		assertEquals("bb", UrlCleaner.getSimpleName("/aa/bb.txt"));
	}

}
