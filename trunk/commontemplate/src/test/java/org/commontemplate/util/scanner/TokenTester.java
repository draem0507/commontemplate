package org.commontemplate.util.scanner;

import org.commontemplate.util.Location;
import org.commontemplate.util.Position;
import org.commontemplate.util.scanner.Token;

import junit.framework.TestCase;

public class TokenTester extends TestCase {
	
	public void testSub() {
		Token source = new Token("&&!", 1, new Position(10, 10));
		super.assertEquals(new Location(10, 10, 10, 13), source.getLocation());
		Token sub = source.subToken(0, 2);
		super.assertEquals("&&", sub.getMessage());
		super.assertEquals(new Location(10, 10, 10, 12), sub.getLocation());
		Token sub2 = source.subToken(2, 3);
		super.assertEquals("!", sub2.getMessage());
		super.assertEquals(new Location(10, 12, 10, 13), sub2.getLocation());
	}
	
	public void testLineSub() {
		Token source = new Token("abc\ndef\ngh", 1, new Position(10, 10));
		super.assertEquals(new Location(10, 10, 12, 2), source.getLocation());
		Token sub = source.subToken(0, 2);
		super.assertEquals("ab", sub.getMessage());
		super.assertEquals(new Location(10, 10, 10, 12), sub.getLocation());
		Token sub2 = source.subToken(0, 5);
		super.assertEquals("abc\nd", sub2.getMessage());
		super.assertEquals(new Location(10, 10, 11, 1), sub2.getLocation());
		Token sub3 = source.subToken(5, 9);
		super.assertEquals("ef\ng", sub3.getMessage());
		super.assertEquals(new Location(11, 1, 12, 1), sub3.getLocation());
	}

}