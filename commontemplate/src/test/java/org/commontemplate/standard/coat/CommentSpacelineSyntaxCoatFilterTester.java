package org.commontemplate.standard.coat;

import junit.framework.TestCase;

public class CommentSpacelineSyntaxCoatFilterTester extends TestCase {

	private CommentSpacelineSyntaxCoatFilter syntaxCoatFilter;

	protected void setUp() throws Exception {
		super.setUp();
		syntaxCoatFilter = new CommentSpacelineSyntaxCoatFilter();
		syntaxCoatFilter.setBegin("/*");
		syntaxCoatFilter.setEnd("*/");
	}

	public void testFilter() throws Exception {
		super.assertEquals("  \t ", syntaxCoatFilter.filter("  \t /*", false, false));
		super.assertEquals("  \t ", syntaxCoatFilter.filter("*/  \t ", false, false));
		super.assertEquals("\n", syntaxCoatFilter.filter("\n  \t /*", false, false));
		super.assertEquals("", syntaxCoatFilter.filter("*/  \t \n", false, false));
		super.assertEquals("aa\n", syntaxCoatFilter.filter("aa\n  \t /*", false, false));
		super.assertEquals("bb", syntaxCoatFilter.filter("*/  \t \nbb", false, false));
	}

}
