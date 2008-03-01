package org.commontemplate.standard.operator;

/**
 * Handler未处理异常
 * 
 * @author liangfei0201@163.com
 *
 */
public class UnhandleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnhandleException() {
		super();
	}

	public UnhandleException(String message) {
		super(message);
	}

	public UnhandleException(Throwable cause) {
		super(cause);
	}

	public UnhandleException(String message, Throwable cause) {
		super(message, cause);
	}

}
