package org.commontemplate.standard.directive.debug;

import org.commontemplate.core.IgnoreException;

public class StopException extends IgnoreException {

	private static final long serialVersionUID = 1L;

	public StopException() {
		super();
	}

	public StopException(String msg) {
		super(msg);
	}

}
