package org.commontemplate.standard.property.number;

import org.commontemplate.util.Assert;

/**
 * Number类型的扩展，代表日期差值的数字.
 * 如：3y表示3年, 3M表示3个月
 * 
 * @author liangfei0201@163.com
 *
 */
public final class DateField implements Comparable {

	private static final long serialVersionUID = 1L;
	
	public static final int MILLISECOND  = 1;

	public static final int SECOND = MILLISECOND * 1000;
	
	public static final int MINUTE = SECOND * 60;
	
	public static final int HOUR = MINUTE * 60;
	
	public static final int DAY = HOUR * 24;

	public static final int WEEK = DAY * 7;
	
	// 以下值为近似值
	public static final int MONTH = DAY * 30;
	
	public static final int YEAR = MONTH * 12;

	public DateField(int type, int value) {
		Assert.assertTrue(type == MILLISECOND || type == SECOND 
				|| type == MINUTE || type == HOUR || type == DAY 
				|| type == WEEK || type == MONTH || type == YEAR
				, "非法的DateField类型!");
		
		this.type = type;
		this.value = value;
	}

	private int type;
	
	/**
	 * 返回代表的类型
	 * 
	 * @return 类型
	 */
	public int getType() {
		return type;
	}
	
	private int value;
	
	/**
	 * 返回代表的数值
	 * 
	 * @return 数值
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * 返回该数字表示的近似时间值
	 * 
	 * @return 近似时间值
	 */
	public long getTime() {
		return type * value;
	}
	
	public int compareTo(Object o) {
		DateField d = (DateField)o;
		return type * value - d.type * d.value;
	}
	
	public boolean equals(Object o) {
		if (o == null) 
			return false;
		if (! (o instanceof DateField))
			return false;
		DateField n = (DateField)o;
		return this.type == n.type && this.value == n.value;
	}
	
	public int hashCode() {
		return 37 * (type  + value);
	}

}
