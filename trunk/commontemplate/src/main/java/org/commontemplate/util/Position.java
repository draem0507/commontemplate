package org.commontemplate.util;

import java.io.Serializable;

/**
 * 位置坐标
 * (不变类，线程安全)
 *
 * @see org.commontemplate.util.Location
 * @author liangfei0201@163.com
 *
 */
public final class Position implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Position ZERO = new Position(0, 0, 0);

	private final int offset;

	private final int row;

	private final int column;

	public Position(int offset, int row, int column) {
		super();
		this.offset = offset;
		this.row = row;
		this.column = column;
	}

	/**
	 * 获取偏移量
	 *
	 * @return 偏移量
	 */
	public int getOffset() {
		return offset;
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
		return new Position(this.offset + pos.offset, this.row + pos.row, this.column + pos.column);
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o.getClass() != Position.class) // 因Position为不变类，采用等号比instanceof高效
			return false;
		Position pos = (Position)o;
		return this.offset == pos.offset && this.row == pos.row && this.column == pos.column;
	}

	public int hashCode() {
		return 37 * (offset + row + column);
	}

	public String toString() {
		return "(" + offset + ":" + row + "," + column + ")";
	}

}
