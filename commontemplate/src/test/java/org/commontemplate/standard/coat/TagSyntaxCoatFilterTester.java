package org.commontemplate.standard.coat;

import junit.framework.TestCase;

public class TagSyntaxCoatFilterTester extends TestCase {

	private TagSyntaxCoatFilter syntaxCoatFilter;

	protected void setUp() throws Exception {
		super.setUp();
		syntaxCoatFilter = new TagSyntaxCoatFilter();
	}

	public void testFilter() throws Exception {
		super.assertEquals("$if{user != null && user.pay > 8000}$msg{user.name}$end{if}",
				syntaxCoatFilter.filter("<ct:if param=\"user != null && user.pay > 8000\"><ct:msg param=\"user.name\"/></ct:if>"));
		super.assertEquals("aaa$if{user != null && user.pay > 8000}bbb$msg{user.name}ccc$end{if}ddd",
				syntaxCoatFilter.filter("aaa<ct:if param=\"user != null && user.pay > 8000\">bbb<ct:msg param=\"user.name\"/>ccc</ct:if>ddd"));
	}

}
