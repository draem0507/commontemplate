package org.commontemplate.util;

import junit.framework.TestCase;

/**
 * Offset 测试
 * 
 * @author Yan Rong (yananay@126.com)
 *
 */
public class OffsetTester extends TestCase {
	
	/**
	 * 测试Equals 方法。
	 * @condition
	 * 条件<br>
	 * 两个Offset对象。
	 * @result
	 * 结果<br>
	 * 只有长度和Postion对象完全相同，两个对象才相同。
	 * @author YanRong
	 */
	public void testEquals() {
		
		Offset targetOffset = new Offset(0, new Position(1, 2));
		Offset expectOffset = new Offset(1, new Position(1, 2));
		
		assertNotSame(targetOffset, expectOffset);
		
		targetOffset = new Offset(0, new Position(1, 2));
		expectOffset = new Offset(0, new Position(1, 3));
		
		assertNotSame(targetOffset, expectOffset);
		
		targetOffset = new Offset(0, new Position(1, 2));
		expectOffset = new Offset(0, new Position(2, 2));
		
		assertNotSame(targetOffset, expectOffset);
		
		targetOffset = new Offset(2, new Position(1, 2));
		expectOffset = new Offset(2, new Position(1, 2));
		
		assertEquals(targetOffset, expectOffset);
	}
	
}
