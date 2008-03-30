package org.commontemplate.util.scanner;

import org.commontemplate.util.Position;

/**
 * 状态机扫描异常
 *
 * @author liangfei0201@163.com
 *
 */
public class ScanningException extends Exception {

	private static final long serialVersionUID = 1L;

	public ScanningException(Position position, int state, char ch) {
		super();
		this.position = position;
		this.state = state;
		this.ch = ch;
	}

	public ScanningException(Position position, int state, char ch, String message) {
		super(message + "错误发生在字符:" + ch + ", 位置:" + position);
		this.position = position;
		this.state = state;
		this.ch = ch;
	}

	public ScanningException(Position position, int state, char ch, Throwable cause) {
		super(cause);
		this.position = position;
		this.state = state;
		this.ch = ch;
	}

	private final Position position;

	public Position getPosition() {
		return position;
	}

	private final int state;

	public final int getState() {
		return state;
	}

	private final char ch;

	public final char getChar() {
		return ch;
	}

}
