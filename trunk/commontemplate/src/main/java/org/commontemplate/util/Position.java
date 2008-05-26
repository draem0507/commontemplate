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

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + offset;
		result = prime * result + row;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Position other = (Position) obj;
		if (column != other.column)
			return false;
		if (offset != other.offset)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	public String toString() {
		return "(pos:" + offset + " row:" + (row + 1) + " col:" + column + ")";
	}

}
