package org.commontemplate.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class RuntimeExceptionSupport extends RuntimeException {

	private static final long serialVersionUID = 4410422013855597828L;

	private String messageKey;

	private Object[] messageArgs = new Object[0];

	public RuntimeExceptionSupport(String messageKey) {
		super();
		this.messageKey = messageKey;
	}

	public RuntimeExceptionSupport(String messageKey, Throwable cause) {
		super(cause);
		this.messageKey = messageKey;
	}

	public RuntimeExceptionSupport(String messageKey, Object[] messageArgs, Throwable cause) {
		super(cause);
		this.messageKey = messageKey;
		// 保护性拷贝
		this.messageArgs = new Object[messageArgs.length];
		System.arraycopy(messageArgs, 0, this.messageArgs, 0, messageArgs.length);
	}

	/* (non-javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		return ExceptionMessageSource.getMessage(messageKey, messageArgs);
	}

	public String getMessageKey() {
		return messageKey;
	}

	public List getMessageArgs() {
		return Collections.unmodifiableList(Arrays.asList(messageArgs));
	}

}
