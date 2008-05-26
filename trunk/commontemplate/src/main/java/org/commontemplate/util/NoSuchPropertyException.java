package org.commontemplate.util;


/**
 * 未找到属性
 *
 * @author liangfei0201@163.com
 *
 */
public class NoSuchPropertyException extends I18nRuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSuchPropertyException() {
		super();
	}

	public NoSuchPropertyException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchPropertyException(String message, Object[] args, Throwable cause) {
		super(message, args, cause);
	}

	public NoSuchPropertyException(String message) {
		super(message);
	}

	public NoSuchPropertyException(String message, Object[] args) {
		super(message, args);
	}

	public NoSuchPropertyException(Throwable cause) {
		super(cause);
	}

}
