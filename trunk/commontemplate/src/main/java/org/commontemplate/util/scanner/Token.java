package org.commontemplate.util.scanner;

import org.commontemplate.util.Location;
import org.commontemplate.util.Offset;
import org.commontemplate.util.Position;


/**
 * 输出片断信息
 * (不变量-线程安全)
 * @author liangfei0201@163.com
 *
 */
public final class Token {
	
	public static final int UNKOWN_TYPE = -1;
	
	private final String message;
	
	private final Offset offset;
	
	private final Position beginPosition;
	
	private final int type;
	
	public Token(String message, Offset offset) {
		this(message, offset, UNKOWN_TYPE);
	}
	
	public Token(String message, Offset offset, int type) {
		this.message = message;
		this.offset = offset;
		this.beginPosition = offset.getPosition();
		this.type = type;
	}

	public Token(String message, long offset, Position beginPosition) {
		this(message, new Offset(offset, beginPosition), UNKOWN_TYPE);
	}
	
	public Token(String message, long offset, Position beginPosition, int type) {
		this(message, new Offset(offset, beginPosition), type);
	}

	/**
	 * 获取片断的内容信息
	 * 
	 * @return 内容信息
	 */
	public String getMessage() {
		return message;
	}

	public Offset getOffset() {
		return offset;
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
	
	private Position endPosition;// 缓存endPosition

	/**
	 * 获取片断的结束位置
	 * 
	 * @return 结束位置
	 */
	public Position getEndPosition() {
		if (endPosition == null) {
			int lines = countLines(message);
			int last = message.lastIndexOf('\n');
			int endColumn = 0;
			if (last == -1) 
				endColumn = beginPosition.getColumn() + message.length();
			else 
				endColumn = message.length() - last - 1;
			int endRow = beginPosition.getRow() + lines;
			endPosition = new Position(endRow, endColumn);
		}
		return endPosition;
	}
	
	private Location location; // 缓存location

	/**
	 * 获取片断的位置区域
	 * 
	 * @return 位置区域
	 */
	public Location getLocation() {
		if (location == null) {
			location = new Location(getBeginPosition(), getEndPosition());
		}
		return location;
	}
	
	public Token addToken(Token otherToken) {
		return new Token(message + otherToken.message, offset);
	}

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
		return new Token(sub, offset.getLength() + start, new Position(row, col));
	}
	
	private int countLines(String str) {
		int count = 0;
		for (int i = 0, n = str.length(); i < n; i ++) 
			if (str.charAt(i) == '\n') 
				count ++;
		return count;
	}
	
	private String toString; // 缓存toString

	public String toString() {
		if (toString == null) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(message);
			buffer.append(getLocation().toString());
			toString = buffer.toString();
		}
		return toString;
	}

}
