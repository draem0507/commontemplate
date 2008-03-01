package org.commontemplate.util;

public class AssertionException extends IllegalStateException {

	private static final long serialVersionUID = 1L;

	public AssertionException() {
		super();
	}
	
	public AssertionException(String message) {
		super(message);
	}

}
