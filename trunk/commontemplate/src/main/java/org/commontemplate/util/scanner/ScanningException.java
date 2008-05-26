package org.commontemplate.util.scanner;

import org.commontemplate.util.I18nRuntimeException;
import org.commontemplate.util.Position;

/**
 * 状态机扫描异常
 *
 * @author liangfei0201@163.com
 *
 */
public class ScanningException extends I18nRuntimeException {

	private static final long serialVersionUID = 1L;

	public ScanningException(int state, char ch, Position position) {
		super();
		this.position = position;
		this.state = state;
		this.ch = ch;
	}

	public ScanningException(int state, char ch, Position position, String message) {
		super(message, new Object[]{new Integer(state), String.valueOf(ch), position});
		this.position = position;
		this.state = state;
		this.ch = ch;
	}

	public ScanningException(int state, char ch, Position position, String message, Object[] args) {
		super(message, args);
		this.position = position;
		this.state = state;
		this.ch = ch;
	}

	public ScanningException(int state, char ch, Position position, Throwable cause) {
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
