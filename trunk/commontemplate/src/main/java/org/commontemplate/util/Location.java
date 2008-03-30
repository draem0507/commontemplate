package org.commontemplate.util;

import java.io.Serializable;

/**
 * 位置区域
 * (不变量, 线程安全)
 *
 * @see org.commontemplate.util.Position
 * @author liangfei0201@163.com
 *
 */
public final class Location implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Location ZERO = new Location(Position.ZERO, Position.ZERO);

	private final Position begin;

	private final Position end;

	/**
	 * 通过起始行列创建区域
	 *
	 * @param beginOffset 起始位置偏移量
	 * @param beginRow 起始位置行
	 * @param beginColumn 起始位置列
	 * @param endOffset 结束位置偏移量
	 * @param endRow 结束位置行
	 * @param endColumn 结束位置列
	 */
	public Location(int beginOffset, int beginRow, int beginColumn,
			int endOffset, int endRow, int endColumn) {
		this(new Position(beginOffset, beginRow, beginColumn),
				new Position(endOffset, endRow, endColumn));
	}

	/**
	 * 通过起始位置创建区域
	 *
	 * @param begin 起始位置
	 * @param end 结束位置
	 */
	public Location(Position begin, Position end) { // FIXME 验证不变式，同行Position，offset差值应等于column之差.
		this.begin = begin;
		this.end = end;
	}

	/**
	 * 获取起始位置
	 * @return 起始位置
	 */
	public Position getBegin() {
		return begin;
	}

	/**
	 * 获取结束位置
	 * @return 结束位置
	 */
	public Position getEnd() {
		return end;
	}

	/**
	 * 整体偏移
	 * @param r 偏移量
	 * @return 偏移后的区域
	 */
	public Location offset(Location r) {
		return new Location(begin.offset(r.getBegin()), end.offset(r.getEnd()));
	}

	/**
	 * 开始位置偏移
	 * @param p 偏移量
	 * @return 偏移后的区域
	 */
	public Location offsetBegin(Position p) {
		return new Location(begin.offset(p), end);
	}

	/**
	 * 结束位置偏移
	 * @param p 偏移量
	 * @return 偏移后的区域
	 */
	public Location offsetEnd(Position p) {
		return new Location(begin, end.offset(p));
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((begin == null) ? 0 : begin.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Location other = (Location) obj;
		if (begin == null) {
			if (other.begin != null)
				return false;
		} else if (!begin.equals(other.begin))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		return true;
	}

	public String toString() {
		return begin.toString() + "-" + end.toString();
	}

}
