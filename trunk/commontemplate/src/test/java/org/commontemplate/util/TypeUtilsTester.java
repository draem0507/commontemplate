package org.commontemplate.util;

import junit.framework.TestCase;

/**
 * 类型测试
 * 
 * @author Yan Rong (yananay@126.com)
 *
 */
public class TypeUtilsTester extends TestCase {
	
	public void testRightNumber() {
		assertTrue(TypeUtils.isNumber("1"));
		assertTrue(TypeUtils.isNumber("1L"));
		assertTrue(TypeUtils.isNumber("1.2"));
		assertTrue(TypeUtils.isNumber("1.2F"));
		assertTrue(TypeUtils.isNumber("1.2D"));
	}
	
	public void testNotNumber() {
		assertFalse(TypeUtils.isNumber("a"));
		assertFalse(TypeUtils.isNumber("a1"));
		assertFalse(TypeUtils.isNumber("a1.2"));
		assertFalse(TypeUtils.isNumber("a1.2F"));
		assertFalse(TypeUtils.isNumber("a1.2D"));
		assertFalse(TypeUtils.isNumber("_1.2F"));
		assertFalse(TypeUtils.isNumber("+1.2D"));
	}

}
