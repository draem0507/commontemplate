package org.commontemplate.util.scanner;

import org.commontemplate.util.scanner.PatternTypeResolver;

import junit.framework.TestCase;

public class GeneralTypeResolverTester extends TestCase {
	
	public void testEscapeType() {
		String[] types = {"+|-|||&"};
		PatternTypeResolver patternTypeResolver = new PatternTypeResolver(types);
		assertEquals(0, patternTypeResolver.resolveType('+'));
		assertEquals(0, patternTypeResolver.resolveType('-'));
		assertEquals(0, patternTypeResolver.resolveType('|'));
		assertEquals(0, patternTypeResolver.resolveType('&'));
	}

	public void testExpressionType() {
		String[] types = {" |\t|\n|\r", "_|a-z|A-Z", "0-9", ".", "\"|\'", "\\", "(|)|[|]"};
		PatternTypeResolver patternTypeResolver = new PatternTypeResolver(types);
		assertEquals(0, patternTypeResolver.resolveType(' '));
		assertEquals(0, patternTypeResolver.resolveType('\t'));
		assertEquals(0, patternTypeResolver.resolveType('\n'));
		assertEquals(0, patternTypeResolver.resolveType('\r'));
		
		assertEquals(1, patternTypeResolver.resolveType('_'));
		for (char ch = 'a'; ch <= 'z'; ch = (char)(ch + 1))
			assertEquals(1, patternTypeResolver.resolveType(ch));
		for (char ch = 'A'; ch <= 'Z'; ch = (char)(ch + 1))
			assertEquals(1, patternTypeResolver.resolveType(ch));
		
		for (char ch = '0'; ch <= '9'; ch = (char)(ch + 1))
			assertEquals(2, patternTypeResolver.resolveType(ch));

		assertEquals(3, patternTypeResolver.resolveType('.'));
		
		assertEquals(4, patternTypeResolver.resolveType('\"'));
		assertEquals(4, patternTypeResolver.resolveType('\''));
		
		assertEquals(5, patternTypeResolver.resolveType('\\'));
		
		assertEquals(6, patternTypeResolver.resolveType('('));
		assertEquals(6, patternTypeResolver.resolveType(')'));
		assertEquals(6, patternTypeResolver.resolveType('['));
		assertEquals(6, patternTypeResolver.resolveType(']'));
		
		assertEquals(7, patternTypeResolver.resolveType('+'));
		assertEquals(7, patternTypeResolver.resolveType('-'));
		assertEquals(7, patternTypeResolver.resolveType('*'));
		assertEquals(7, patternTypeResolver.resolveType('/'));
		assertEquals(7, patternTypeResolver.resolveType('|'));
		assertEquals(7, patternTypeResolver.resolveType('&'));
	}
	
	public void testDirectiveType() {
		String[] types = {
			" |\t|\r", "\\", "$", "0-9|_|a-z|A-Z", "{", "}", "!", "*", "#", "\n", "\"|\'|`"
		};
		PatternTypeResolver patternTypeResolver = new PatternTypeResolver(types);
		assertEquals(0, patternTypeResolver.resolveType(' '));
		assertEquals(0, patternTypeResolver.resolveType('\t'));
		assertEquals(0, patternTypeResolver.resolveType('\r'));
		
		assertEquals(1, patternTypeResolver.resolveType('\\'));
		assertEquals(2, patternTypeResolver.resolveType('$'));
		
		assertEquals(3, patternTypeResolver.resolveType('_'));
		for (char ch = 'a'; ch <= 'z'; ch = (char)(ch + 1))
			assertEquals(3, patternTypeResolver.resolveType(ch));
		for (char ch = 'A'; ch <= 'Z'; ch = (char)(ch + 1))
			assertEquals(3, patternTypeResolver.resolveType(ch));
		for (char ch = '0'; ch <= '9'; ch = (char)(ch + 1))
			assertEquals(3, patternTypeResolver.resolveType(ch));

		assertEquals(4, patternTypeResolver.resolveType('{'));
		assertEquals(5, patternTypeResolver.resolveType('}'));
		assertEquals(6, patternTypeResolver.resolveType('!'));
		assertEquals(7, patternTypeResolver.resolveType('*'));
		assertEquals(8, patternTypeResolver.resolveType('#'));
		assertEquals(9, patternTypeResolver.resolveType('\n'));
		
		assertEquals(10, patternTypeResolver.resolveType('\"'));
		assertEquals(10, patternTypeResolver.resolveType('\''));
		assertEquals(10, patternTypeResolver.resolveType('`'));
		
		assertEquals(11, patternTypeResolver.resolveType('+'));
		assertEquals(11, patternTypeResolver.resolveType('-'));
		assertEquals(11, patternTypeResolver.resolveType('/'));
		assertEquals(11, patternTypeResolver.resolveType('|'));
		assertEquals(11, patternTypeResolver.resolveType('&'));
	}

}
