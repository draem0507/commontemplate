package org.commontemplate.util;

import java.io.Serializable;

/**
 * 位置坐标
 * (不变类，线程安全)
 * 
 * @see org.commontemplate.util.Location
 * @see org.commontemplate.util.Offset
 * @author liangfei0201@163.com
 *
 */
public final class Position implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final Position ZERO = new Position(0, 0);

	private final int row;
	
	private final int column;
	
	public Position(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	/**
	 * 获取位置行
	 * 
	 * @return 位置行
	 */
	public int getRow() {
		return row;
	}

	/**
	 * 获取位置列
	 * 
	 * @return 位置列
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * 偏移指定行列数
	 * 
	 * @param pos 行列数
	 * @return 偏移结果
	 */
	public Position offset(Position pos) {
		return new Position(this.row + pos.row, this.column + pos.column);
	}
	
	/**
	 * 偏移指定行数
	 * 
	 * @param row 行数
	 * @return 偏移结果
	 */
	public Position offsetRow(int row) {
		return new Position(this.row + row, this.column);
	}
	
	
	/**
	 * 偏移指定列数
	 * 
	 * @param column 列数
	 * @return 偏移结果
	 */
	public Position offsetColumn(int column) {
		return new Position(this.row, this.column + column);
	}

	public boolean equals(Object o) {
		if (o == null) 
			return false;
		if (! (o instanceof Position))
			return false;
		Position pos = (Position)o;
		return this.row == pos.row && this.column == pos.column;
	}
	
	public int hashCode() {
		return 37 * (row + column);
	}

	public String toString() {
		return "(" + row + "," + column + ")";
	}
	
}
