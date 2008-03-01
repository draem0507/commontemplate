package org.commontemplate.config;

import junit.framework.TestCase;

public class SyntaxTester extends TestCase {
	
	public void testRightSyntax() {
		new Syntax('$', '{', '}', '#', '*', '!', "end");
		new Syntax('@', '{', '}', '#', '*', '!', "end1");
	}
	
	public void testRepeatSyntax() {
		try {
			new Syntax('$', '$', '}', '#', '*', '!', "end");
			fail("传入相同的char时，应抛出异常!");
		} catch (IllegalStateException e) {
			// right
		}
	}
	
	public void testLetterSyntax() {
		try {
			new Syntax('a', '{', '}', '#', '*', '!', "end");
			fail("传入字母时，应抛出异常!");
		} catch (IllegalArgumentException e) {
			// right
		}
	}
	
	public void testNumberSyntax() {
		try {
			new Syntax('1', '{', '}', '#', '*', '!', "end");
			fail("传入数字时，应抛出异常!");
		} catch (IllegalArgumentException e) {
			// right
		}
	}
	
	public void testEmptyEndNameSyntax() {
		try {
			new Syntax('$', '{', '}', '#', '*', '!', "");
			fail("传入空结束指令名时，应抛出异常!");
		} catch (IllegalStateException e) {
			// right
		}
	}

}