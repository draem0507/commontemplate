package org.commontemplate.util.scanner;

import org.commontemplate.util.Offset;
import org.commontemplate.util.Position;

/**
 * 状态机扫描异常
 * 
 * @author liangfei0201@163.com
 *
 */
public class ScanningException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private Offset offset;

	public final Offset getOffset() {
		return offset;
	}

	private final int state;
	
	public final int getState() {
		return state;
	}
	
	public ScanningException(int state, long offset, int row, int column) {
		super();
		this.state = state;
		this.offset = new Offset(offset, new Position(row, column));
	}

	public ScanningException(int state, long offset, int row, int column, String message) {
		super(message);
		this.state = state;
		this.offset = new Offset(offset, new Position(row, column));
	}
	
	public ScanningException(int state, long offset, int row, int column, Throwable cause) {
		super(cause);
		this.state = state;
		this.offset = new Offset(offset, new Position(row, column));
	}

}
