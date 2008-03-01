package org.commontemplate.engine.template;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.commontemplate.config.Syntax;
import org.commontemplate.engine.template.DirectiveTokenizer;
import org.commontemplate.util.scanner.ScanningException;
import org.commontemplate.util.scanner.Token;

import junit.framework.TestCase;

public class DirectiveTokenizerTester extends TestCase {

	DirectiveTokenizer directiveTokenizer;
	
	public void setUp() {
		directiveTokenizer = new DirectiveTokenizer(Syntax.DEFAULT);
	}
	
	private static void assertTokens(List tokens, String[] messages) {
		assertEquals(messages.length, tokens.size());
		for (int i = 0, n = messages.length; i < n; i ++) {
			assertEquals(messages[i], ((Token)tokens.get(i)).getMessage());
		}
	}
	
	public void testNullTemplate() throws IOException, ScanningException {
		try {
			directiveTokenizer.split(null);
			fail("传入null,应抛出NullPointerException");
		} catch (NullPointerException e) {
			// right
		}
	}
	
	public void testEmptyTemplate() throws IOException, ScanningException {
		List tokens = directiveTokenizer.split(new StringReader(""));
		assertEquals(0, tokens.size());
	}
	
	public void testRightTemplate() throws IOException, ScanningException {
		String template = "aaa $if{true} bbbb $else  ccc $end ddd ";
		List tokens = directiveTokenizer.split(new StringReader(template));
		String[] messages = new String[]{"aaa ", "$if{true}", " bbbb ", "$else", "  ccc ", "$end", " ddd "};
		assertTokens(tokens, messages);
	}
	
	public void testEscapeTemplate() throws IOException, ScanningException {
		String template = "aaa $if{true} bbbb \\$else  ccc $end ddd ";
		List tokens = directiveTokenizer.split(new StringReader(template));
		String[] messages = new String[]{"aaa ", "$if{true}", " bbbb \\$else  ccc ", "$end", " ddd "};
		assertTokens(tokens, messages);
	}
	
	public void testIllegalTemplate() {
		
	}
	
	/*public void testStringTemplate() {
		String template = "$set{template:\"$if{true}show$end\"}";
		
	}*/

	public void tearDown() {
		directiveTokenizer = null;
	}

}
