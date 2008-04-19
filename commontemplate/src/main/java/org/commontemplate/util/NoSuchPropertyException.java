package org.commontemplate.util;

/**
 * 未找到属性
 *
 * @author liangfei0201@163.com
 *
 */
public class NoSuchPropertyException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoSuchPropertyException() {
		super();
	}

	public NoSuchPropertyException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchPropertyException(String message) {
		super(message);
	}

	public NoSuchPropertyException(Throwable cause) {
		super(cause);
	}

}
