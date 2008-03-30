package org.commontemplate;

import java.util.Date;

import junit.framework.TestCase;

public class VersionTester extends TestCase {

	public void testVersion() {
		super.assertTrue(Version.compareTo("0.5.9") > 0);
	}

	public void testVersionInfo() {
		super.assertTrue(Version.getReleaseDate() instanceof Date);
		super.assertEquals("http://www.commontemplate.org/", Version.getVendor());
		super.assertNotNull(Version.getVersionNumber());
	}

	public void testRightVersions() {
		super.assertTrue(Version.compare("0.6.5", "0.6.1") > 0);
		super.assertTrue(Version.compare("0.6", "0.6.1") < 0);
		super.assertTrue(Version.compare("0.5.9", "0.6.1") < 0);
		super.assertTrue(Version.compare("0.5.9", "0.5.9") == 0);
	}

	public void testErrorVersions() {
		try {
			Version.compare("ver", "0.6.1");
			fail("传入错误版本号，应抛出异常！");
		} catch (IllegalArgumentException e) {
			// right
		}
		try {
			Version.compare("0.6.1", "ver");
			fail("传入错误版本号，应抛出异常！");
		} catch (IllegalArgumentException e) {
			// right
		}
	}

	public void testNullVersions() {
		try {
			Version.compare(null, "0.6.1");
			fail("传入空版本号，应抛出异常！");
		} catch (NullPointerException e) {
			// right
		}
		try {
			Version.compare("0.6.1", null);
			fail("传入空版本号，应抛出异常！");
		} catch (NullPointerException e) {
			// right
		}
	}

}
