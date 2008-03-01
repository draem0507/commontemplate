package org.commontemplate.tools.bean;

public class BeanException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BeanException() {
		super();
	}

	public BeanException(String message, Throwable cause) {
		super(message, cause);
	}

	public BeanException(String message) {
		super(message);
	}

	public BeanException(Throwable cause) {
		super(cause);
	}

}
