package org.commontemplate.util;

import java.io.Serializable;

/**
 * 偏移量
 * (不变类，线程安全)
 * 
 * @see org.commontemplate.util.Position
 * @author liangfei0201@163.com
 *
 */
public final class Offset implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Offset ZERO = new Offset(0, Position.ZERO);
	
	private final long length;
	
	private final Position position;

	public Offset(long length, Position position) {
		super();
		this.length = length;
		this.position = position;
	}

	/**
	 * 获取偏移长度
	 * 
	 * @return 偏移长度
	 */
	public long getLength() {
		return length;
	}

	/**
	 * 获取偏移位置
	 * 
	 * @return 位置
	 */
	public Position getPosition() {
		return position;
	}
	
	public boolean equals(Object o) {
		if (o == null) 
			return false;
		if (! (o instanceof Offset))
			return false;
		Offset off = (Offset)o;
		return this.length == off.length 
			&& this.position.equals(off.position);
	}
	
	public int hashCode() {
		return 37 * (new Long(length).hashCode() + position.hashCode());
	}
	
	public String toString() {
		return length + "/" + position.toString();
	}
}
