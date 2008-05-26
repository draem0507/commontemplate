package org.commontemplate.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class I18nException extends Exception {

	private static final long serialVersionUID = 4410422013855597828L;

	private String messageKey;

	private Object[] messageArgs = new Object[0];

	public I18nException() {
		super();
	}

	public I18nException(Throwable cause) {
		super(cause);
	}

	public I18nException(String messageKey) {
		super();
		this.messageKey = messageKey;
	}

	public I18nException(String messageKey, Throwable cause) {
		super(cause);
		this.messageKey = messageKey;
	}

	public I18nException(String messageKey, Object[] messageArgs) {
		super();
		this.messageKey = messageKey;
		// 保护性拷贝
		this.messageArgs = new Object[messageArgs.length];
		System.arraycopy(messageArgs, 0, this.messageArgs, 0, messageArgs.length);
	}

	public I18nException(String messageKey, Object[] messageArgs, Throwable cause) {
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
		return I18nMessages.getMessage(messageKey, messageArgs);
	}

	public String getMessageKey() {
		return messageKey;
	}

	public List getMessageArgs() {
		return Collections.unmodifiableList(Arrays.asList(messageArgs));
	}

}
