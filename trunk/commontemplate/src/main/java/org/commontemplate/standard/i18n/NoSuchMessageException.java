package org.commontemplate.standard.i18n;

import java.util.Locale;

import org.commontemplate.util.I18nRuntimeException;

public class NoSuchMessageException extends I18nRuntimeException {

	private static final long serialVersionUID = 1L;

	private final String code;

	private final Locale locale;

	public NoSuchMessageException(String code, Locale locale) {
		super("NoSuchMessageException.default.message", new Object[]{code, locale});
		this.code = code;
		this.locale = locale;
	}

	public NoSuchMessageException(String code) {
		this(code, Locale.getDefault());
	}

	public String getCode() {
		return code;
	}

	public Locale getLocale() {
		return locale;
	}

}