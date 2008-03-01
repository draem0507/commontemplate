package org.commontemplate.util.scanner;

import org.commontemplate.util.scanner.MatchingTypeResolver;

import junit.framework.TestCase;

public class GeneralTypeResolverTester extends TestCase {
	
	public void testEscapeType() {
		String[] types = {"+|-|||&"};
		MatchingTypeResolver patternTypeResolver = new MatchingTypeResolver(types);
		assertEquals(0, patternTypeResolver.getType('+'));
		assertEquals(0, patternTypeResolver.getType('-'));
		assertEquals(0, patternTypeResolver.getType('|'));
		assertEquals(0, patternTypeResolver.getType('&'));
	}

	public void testExpressionType() {
		String[] types = {" |\t|\n|\r", "_|a-z|A-Z", "0-9", ".", "\"|\'", "\\", "(|)|[|]"};
		MatchingTypeResolver patternTypeResolver = new MatchingTypeResolver(types);
		assertEquals(0, patternTypeResolver.getType(' '));
		assertEquals(0, patternTypeResolver.getType('\t'));
		assertEquals(0, patternTypeResolver.getType('\n'));
		assertEquals(0, patternTypeResolver.getType('\r'));
		
		assertEquals(1, patternTypeResolver.getType('_'));
		for (char ch = 'a'; ch <= 'z'; ch = (char)(ch + 1))
			assertEquals(1, patternTypeResolver.getType(ch));
		for (char ch = 'A'; ch <= 'Z'; ch = (char)(ch + 1))
			assertEquals(1, patternTypeResolver.getType(ch));
		
		for (char ch = '0'; ch <= '9'; ch = (char)(ch + 1))
			assertEquals(2, patternTypeResolver.getType(ch));

		assertEquals(3, patternTypeResolver.getType('.'));
		
		assertEquals(4, patternTypeResolver.getType('\"'));
		assertEquals(4, patternTypeResolver.getType('\''));
		
		assertEquals(5, patternTypeResolver.getType('\\'));
		
		assertEquals(6, patternTypeResolver.getType('('));
		assertEquals(6, patternTypeResolver.getType(')'));
		assertEquals(6, patternTypeResolver.getType('['));
		assertEquals(6, patternTypeResolver.getType(']'));
		
		assertEquals(7, patternTypeResolver.getType('+'));
		assertEquals(7, patternTypeResolver.getType('-'));
		assertEquals(7, patternTypeResolver.getType('*'));
		assertEquals(7, patternTypeResolver.getType('/'));
		assertEquals(7, patternTypeResolver.getType('|'));
		assertEquals(7, patternTypeResolver.getType('&'));
	}
	
	public void testDirectiveType() {
		String[] types = {
			" |\t|\r", "\\", "$", "0-9|_|a-z|A-Z", "{", "}", "!", "*", "#", "\n", "\"|\'|`"
		};
		MatchingTypeResolver patternTypeResolver = new MatchingTypeResolver(types);
		assertEquals(0, patternTypeResolver.getType(' '));
		assertEquals(0, patternTypeResolver.getType('\t'));
		assertEquals(0, patternTypeResolver.getType('\r'));
		
		assertEquals(1, patternTypeResolver.getType('\\'));
		assertEquals(2, patternTypeResolver.getType('$'));
		
		assertEquals(3, patternTypeResolver.getType('_'));
		for (char ch = 'a'; ch <= 'z'; ch = (char)(ch + 1))
			assertEquals(3, patternTypeResolver.getType(ch));
		for (char ch = 'A'; ch <= 'Z'; ch = (char)(ch + 1))
			assertEquals(3, patternTypeResolver.getType(ch));
		for (char ch = '0'; ch <= '9'; ch = (char)(ch + 1))
			assertEquals(3, patternTypeResolver.getType(ch));

		assertEquals(4, patternTypeResolver.getType('{'));
		assertEquals(5, patternTypeResolver.getType('}'));
		assertEquals(6, patternTypeResolver.getType('!'));
		assertEquals(7, patternTypeResolver.getType('*'));
		assertEquals(8, patternTypeResolver.getType('#'));
		assertEquals(9, patternTypeResolver.getType('\n'));
		
		assertEquals(10, patternTypeResolver.getType('\"'));
		assertEquals(10, patternTypeResolver.getType('\''));
		assertEquals(10, patternTypeResolver.getType('`'));
		
		assertEquals(11, patternTypeResolver.getType('+'));
		assertEquals(11, patternTypeResolver.getType('-'));
		assertEquals(11, patternTypeResolver.getType('/'));
		assertEquals(11, patternTypeResolver.getType('|'));
		assertEquals(11, patternTypeResolver.getType('&'));
	}

}
