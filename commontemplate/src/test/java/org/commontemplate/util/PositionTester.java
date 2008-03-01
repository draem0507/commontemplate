package org.commontemplate.util;

import junit.framework.TestCase;

/**
 * Position 测试
 * 
 * @author Yan Rong (yananay@126.com)
 *
 */
public class PositionTester extends TestCase {

	/**
	 * 测试 offset 方法。<br>
	 * @condition
	 * 条件<br>
	 * 一个原始的Position对象，坐标是 0, 0<br>
	 * 一个用于offset的Position对象，坐标是 2, 3<br>
	 * @result
	 * 结果<br>
	 * 进行offset操作后，原始的Postion不会改变，<br>
	 * 会生成一个新的Postion对象，坐标发生变化。
	 * @author YanRong
	 */
	public void testOffset() {
		
		Position pos = new Position(0, 0);		
		Position offsetPos = new Position(2, 3);
		
		Position newPos = pos.offset(offsetPos);
		
		assertEquals(0, pos.getRow());
		assertEquals(0, pos.getColumn());
		
		assertEquals(2, newPos.getRow());
		assertEquals(3, newPos.getColumn());
	}
	
	/**
	 * 测试 offsetRow 方法。<br>
	 * @condition
	 * 条件<br>
	 * 一个原始的Position对象，坐标是 0, 0<br>
	 * @result
	 * 结果<br>
	 * 进行offset操作后，原始的Postion不会改变，<br>
	 * 会生成一个新的Postion对象，坐标发生变化。
	 * @author YanRong
	 */
	public void testOffsetRow() {
		
		Position pos = new Position(0, 0);
		Position newPos = pos.offsetRow(2);
		
		assertEquals(0, pos.getRow());
		assertEquals(2, newPos.getRow());
	}
	
	/**
	 * 测试 offsetColumn 方法。<br>
	 * @condition
	 * 条件<br>
	 * 一个原始的Position对象，坐标是 0, 0<br>
	 * @result
	 * 结果<br>
	 * 进行offset操作后，原始的Postion不会改变，<br>
	 * 会生成一个新的Postion对象，坐标发生变化。
	 * @author YanRong
	 */
	public void testOffsetColumn() {
		
		Position pos = new Position(0, 0);
		Position newPos = pos.offsetColumn(2);
		
		assertEquals(0, pos.getRow());
		assertEquals(2, newPos.getColumn());
	}
	
	/**
	 * 测试 Equals 方法。<br>
	 * @condition
	 * 条件<br>
	 * 两个Position对象<br>
	 * @result
	 * 结果<br>
	 * 只有行和列完全相同，两个对象才认为相同。
	 * @author YanRong
	 */
	public void testEquals() {
		
		Position targetPos = new Position(0, 0);
		Position expectPos = new Position(0, 1);
		
		assertNotSame(targetPos, expectPos);
		
		targetPos = new Position(0, 0);
		expectPos = new Position(1, 0);
		
		assertNotSame(targetPos, expectPos);
		
		targetPos = new Position(10, 9);
		expectPos = new Position(10, 9);
		
		assertEquals(targetPos, expectPos);
	}
	
}
