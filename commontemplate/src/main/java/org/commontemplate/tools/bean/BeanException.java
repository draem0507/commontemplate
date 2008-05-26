package org.commontemplate.tools.bean;

import org.commontemplate.util.I18nRuntimeException;

public class BeanException extends I18nRuntimeException {

	private static final long serialVersionUID = 1L;

	public BeanException() {
		super();
	}

	public BeanException(String messageKey, Throwable cause) {
		super(messageKey, cause);
	}

	public BeanException(String messageKey, Object[] messageArgs) {
		super(messageKey, messageArgs);
	}

	public BeanException(String messageKey, Object[] messageArgs,
			Throwable cause) {
		super(messageKey, messageArgs, cause);
	}

	public BeanException(String messageKey) {
		super(messageKey);
	}

	public BeanException(Throwable cause) {
		super(cause);
	}

}
