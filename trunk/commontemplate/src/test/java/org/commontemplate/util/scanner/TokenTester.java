package org.commontemplate.util.scanner;

import org.commontemplate.util.Location;
import org.commontemplate.util.Position;
import org.commontemplate.util.scanner.Token;

import junit.framework.TestCase;

public class TokenTester extends TestCase {

	public void testEndEnter() {
		Token source = new Token("aa\nbb\n", new Position(0, 0, 0));
		super.assertEquals(new Location(new Position(0, 0, 0), new Position(5, 1, 2)), source.getLocation());
		Token source2 = new Token("aa\nbb\n", new Position(11, 3, 4));
		super.assertEquals(new Location(new Position(11, 3, 4), new Position(16, 4, 2)), source2.getLocation());
	}

	public void testEndPosition() {
		Token source = new Token("aa\nbb\ncc", new Position(0, 0, 0));
		super.assertEquals(new Location(new Position(0, 0, 0), new Position(7, 2, 1)), source.getLocation());
		Token source2 = new Token("aa\nbb\ncc", new Position(11, 3, 4));
		super.assertEquals(new Location(new Position(11, 3, 4), new Position(18, 5, 1)), source2.getLocation());
	}

	public void testSub() {
		Token source = new Token("&&!", new Position(11, 10, 10));
		super.assertEquals(new Location(new Position(11, 10, 10), new Position(13, 10, 12)), source.getLocation());
		Token sub = source.subToken(0, 2);
		super.assertEquals("&&", sub.getMessage());
		super.assertEquals(new Location(new Position(11, 10, 10), new Position(12, 10, 11)), sub.getLocation());
		Token sub2 = source.subToken(2, 3);
		super.assertEquals("!", sub2.getMessage());
		super.assertEquals(new Location(new Position(13, 10, 12), new Position(13, 10, 12)), sub2.getLocation());
	}

	public void testLineSub() {
		Token source = new Token("abc\ndef\ngh", new Position(11, 10, 10));
		super.assertEquals(new Location(new Position(11, 10, 10), new Position(20, 12, 1)), source.getLocation());
		Token sub = source.subToken(0, 2);
		super.assertEquals("ab", sub.getMessage());
		super.assertEquals(new Location(new Position(11, 10, 10), new Position(12, 10, 11)), sub.getLocation());
		Token sub2 = source.subToken(0, 5);
		super.assertEquals("abc\nd", sub2.getMessage());
		super.assertEquals(new Location(new Position(11, 10, 10), new Position(15, 11, 0)), sub2.getLocation());
		Token sub3 = source.subToken(5, 9);
		super.assertEquals("ef\ng", sub3.getMessage());
		super.assertEquals(new Location(new Position(16, 11, 1), new Position(19, 12, 0)), sub3.getLocation());
	}

}
