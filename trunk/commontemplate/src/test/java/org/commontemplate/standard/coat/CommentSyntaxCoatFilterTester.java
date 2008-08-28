package org.commontemplate.standard.coat;

import junit.framework.TestCase;

public class CommentSyntaxCoatFilterTester extends TestCase {

	private CommentSyntaxCoatFilter syntaxCoatFilter;

	protected void setUp() throws Exception {
		super.setUp();
		syntaxCoatFilter = new CommentSyntaxCoatFilter();
		syntaxCoatFilter.setBegin("/*");
		syntaxCoatFilter.setEnd("*/");
		syntaxCoatFilter.setClearSpaceline(true);
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
