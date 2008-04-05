package org.commontemplate.util;

import junit.framework.TestCase;

public class LocationTester extends TestCase {

	public void testLocation() {
		Location location = new Location(1,2,3,4,5,6);
		super.assertEquals(location.getBegin().getOffset(), 1);
		super.assertEquals(location.getBegin().getRow(), 2);
		super.assertEquals(location.getBegin().getColumn(), 3);
		super.assertEquals(location.getEnd().getOffset(), 4);
		super.assertEquals(location.getEnd().getRow(), 5);
		super.assertEquals(location.getEnd().getColumn(), 6);
	}

}