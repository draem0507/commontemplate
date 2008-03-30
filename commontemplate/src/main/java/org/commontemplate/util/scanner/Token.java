package org.commontemplate.util.scanner;

import org.commontemplate.util.Location;
import org.commontemplate.util.Position;

/**
 * 输出片断信息
 * (不变类-线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public final class Token {

	public static final int UNKOWN_TYPE = -1;

	private final String message;

	private final Position beginPosition;

	private final int type;

	/**
	 * 构造片断 (未知类型)
	 *
	 * @param message 片断内容
	 * @param beginPosition 起始位置, 根据片断内容自动计算结束位置
	 */
	public Token(String message, Position beginPosition) {
		this(message, beginPosition, UNKOWN_TYPE);
	}

	/**
	 * 构造片断
	 *
	 * @param message 片断内容
	 * @param beginPosition 起始位置, 根据片断内容自动计算结束位置
	 * @param type 片断类型
	 */
	public Token(String message, Position beginPosition, int type) {
		this.message = message;
		this.beginPosition = beginPosition;
		this.type = type;
		calculateToken();
	}

	// 计算结束位置
	private void calculateToken() {
		int lines = countLines(message);
		int last = message.lastIndexOf('\n');
		int endColumn = 0;
		if (last == -1)
			endColumn = beginPosition.getColumn() + message.length();
		else
			endColumn = message.length() - last - 1;
		int endRow = beginPosition.getRow() + lines;
		endPosition = new Position(beginPosition.getOffset() + message.length(), endRow, endColumn);
		location = new Location(beginPosition, endPosition);
	}

	// 统计行数
	private int countLines(String str) {
		int count = 0;
		for (int i = 0, n = str.length(); i < n; i ++)
			if (str.charAt(i) == '\n')
				count ++;
		return count;
	}

	/**
	 * 获取片断的内容信息
	 *
	 * @return 内容信息
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 片断的类型
	 * @return 类型
	 */
	public int getType() {
		return type;
	}

	/**
	 * 获取片断的起始位置
	 *
	 * @return 起始位置
	 */
	public Position getBeginPosition() {
		return beginPosition;
	}

	private Position endPosition;

	/**
	 * 获取片断的结束位置
	 *
	 * @return 结束位置
	 */
	public Position getEndPosition() {
		return endPosition;
	}

	private Location location;

	/**
	 * 获取片断的位置区域
	 *
	 * @return 位置区域
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * 将两个片断相加, 产生新的片断
	 *
	 * @param otherToken 待相加的片断
	 * @return 相加后的片断
	 */
	public Token addToken(Token otherToken) {
		return new Token(message + otherToken.message, getBeginPosition());
	}

	/**
	 * 截取子片断
	 *
	 * @param start 截取起始位置
	 * @return 子片断
	 */
	public Token subToken(int start) {
		return subToken(start, message.length());
	}

	/**
	 * 截取子片断
	 *
	 * @param start 截取起始位置
	 * @param end 截取结束位置
	 * @return 子片断
	 */
	public Token subToken(int start, int end) {
		if (start >= end)
			throw new java.lang.IllegalStateException(start + " >= " + end + ", 起始位置必需小于结束位置!");
		if (start < 0 || start > message.length() || end > message.length())
			throw new java.lang.ArrayIndexOutOfBoundsException(start);

		String sub = message.substring(start, end);
		int row = beginPosition.getRow();
		int col = beginPosition.getColumn();
		if (start > 0) {
			// 计算起始行
			row += countLines(message.substring(0, start));

			// 计算起始列
			int last = sub.lastIndexOf('\n');
			if (last == -1)
				col += start;
			else
				col = sub.length() - last - 1;
		}
		return new Token(sub, new Position(getBeginPosition().getOffset() + start, row, col));
	}

	public String toString() {
		return message + getLocation();
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((beginPosition == null) ? 0 : beginPosition.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + type;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Token other = (Token) obj;
		if (beginPosition == null) {
			if (other.beginPosition != null)
				return false;
		} else if (!beginPosition.equals(other.beginPosition))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
