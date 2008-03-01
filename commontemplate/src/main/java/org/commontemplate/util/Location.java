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
	
	public static final Location ZERO = new Location(0, 0, 0, 0);
	
	private final Position begin;
	
	private final Position end;
	
	/**
	 * 通过起始行列创建区域
	 * @param beginRow 起始行
	 * @param beginColumn 起始列
	 * @param endRow 结束行
	 * @param endColumn 结束列
	 */
	public Location(int beginRow, int beginColumn, int endRow, int endColumn) {
		this(new Position(beginRow, beginColumn), new Position(endRow, endColumn));
	}
	
	/**
	 * 通过起始位置创建区域
	 * @param begin 起始位置
	 * @param end 结束位置
	 */
	public Location(Position begin, Position end) {
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

	public boolean equals(Object o) {
		if (o == null) 
			return false;
		if (! (o instanceof Location))
			return false;
		Location r = (Location)o;
		return begin.equals(r.begin) 
			&& end.equals(r.end);
	}
	
	public int hashCode() {
		return 37 * (begin.hashCode() + end.hashCode());
	}
	
	public String toString() {
		return begin.toString() + "-" + end.toString();
	}
	
}
