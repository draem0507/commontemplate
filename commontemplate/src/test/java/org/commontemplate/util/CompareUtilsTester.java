package org.commontemplate.util;

public class CompareUtilsTester extends junit.framework.TestCase {
	
	public void testMaxInteger() {
		assertTrue(CompareUtils.compareNumber(new Integer(5), new Integer(6)) < 0);
		assertTrue(CompareUtils.compareNumber(new Integer(5), new Integer(5)) == 0);
		assertTrue(CompareUtils.compareNumber(new Integer(6), new Integer(3)) > 0);
	}
	
	public void testMaxLong() {
		assertTrue(CompareUtils.compareNumber(new Long(5), new Long(6)) < 0);
		assertTrue(CompareUtils.compareNumber(new Long(5), new Long(5)) == 0);
		assertTrue(CompareUtils.compareNumber(new Long(6), new Long(3)) > 0);
	}
	
}
