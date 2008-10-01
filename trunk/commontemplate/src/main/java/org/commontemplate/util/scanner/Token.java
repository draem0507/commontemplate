package org.commontemplate.util.scanner;

import org.commontemplate.util.Assert;
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
		Assert.assertTrue(message != null && message.length() > 0);
		Assert.assertNotNull(beginPosition);
		this.message = message;
		this.beginPosition = beginPosition;
		this.type = type;

		int endRow = beginPosition.getLine() + countLines(message);
		int endIndex = beginPosition.getIndex() + message.length();
		if (message.endsWith("\n")) { // 如果最后一个字符为换行，则当前块不包含该行.
			int last = message.length() < 2 ? -1 : message.lastIndexOf('\n', message.length() - 2);
			int endColumn;
			if (last == -1)
				endColumn = beginPosition.getColumn() + message.length();
			else
				endColumn = message.length() - last - 1;
			endPosition = new Position(endIndex - 1, endRow - 1, endColumn - 1);
			nextBeginPosition = new Position(endIndex, endRow, 0);
		} else {
			int last = message.lastIndexOf('\n');
			int endColumn;
			if (last == -1)
				endColumn = beginPosition.getColumn() + message.length();
			else
				endColumn = message.length() - last - 1;
			endPosition = new Position(endIndex - 1, endRow, endColumn - 1);
			nextBeginPosition = new Position(endIndex, endRow, endColumn);
		}
		this.location = new Location(beginPosition, endPosition);
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

	private final Position endPosition;

	/**
	 * 获取片断的结束位置
	 *
	 * @return 结束位置
	 */
	public Position getEndPosition() {
		return endPosition;
	}

	private final Position nextBeginPosition;

	/**
	 * 获取下一片断的起始位置
	 *
	 * @return 下一片断的起始位置
	 */
	public Position getNextBeginPosition() {
		return nextBeginPosition;
	}

	private final Location location;

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
		if (start < 0 || start > message.length())
			throw new java.lang.ArrayIndexOutOfBoundsException(start);
		if (end < 0 || end > message.length())
			throw new java.lang.ArrayIndexOutOfBoundsException(end);
		Assert.assertTrue(start <= end, "Token.rang.error", new Object[]{new Integer(start), new Integer(end)});
		if (start == 0)
			return new Token(message.substring(start, end), beginPosition);
		else
			return new Token(message.substring(start, end), new Token(message.substring(0, start), beginPosition).getNextBeginPosition());
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
