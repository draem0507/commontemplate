package org.commontemplate.standard.operator;

import org.commontemplate.util.I18nRuntimeException;

/**
 * Handler未处理异常
 *
 * @author liangfei0201@163.com
 *
 */
public class UnhandleException extends I18nRuntimeException {

	private static final long serialVersionUID = -3920253969849559276L;

	public UnhandleException() {
		super();
	}

	public UnhandleException(String message) {
		super(message);
	}

	public UnhandleException(String message, Object[] args) {
		super(message, args);
	}

	public UnhandleException(Throwable cause) {
		super(cause);
	}

	public UnhandleException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnhandleException(String message, Object[] args, Throwable cause) {
		super(message, args, cause);
	}

}
