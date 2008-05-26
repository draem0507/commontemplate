package org.commontemplate.util;

import java.io.FileNotFoundException;

/**
 * 异常类工厂, 通过国际化信息创建基础异常
 *
 * @author liangfei0201@163.com
 *
 */
public class I18nExceptionFactory {

	public static IllegalArgumentException createIllegalArgumentException(String messgaeKey) {
		return new IllegalArgumentException(I18nMessages.getMessage(messgaeKey));
	}

	public static IllegalArgumentException createIllegalArgumentException(String messgaeKey, Object[] messgaeArgs) {
		return new IllegalArgumentException(I18nMessages.getMessage(messgaeKey, messgaeArgs));
	}

	public static IllegalStateException createIllegalStateException(String messgaeKey) {
		return new IllegalStateException(I18nMessages.getMessage(messgaeKey));
	}

	public static IllegalStateException createIllegalStateException(String messgaeKey, Object[] messgaeArgs) {
		return new IllegalStateException(I18nMessages.getMessage(messgaeKey, messgaeArgs));
	}

	public static NullPointerException createNullPointerException(String messgaeKey) {
		return new NullPointerException(I18nMessages.getMessage(messgaeKey));
	}

	public static NullPointerException createNullPointerException(String messgaeKey, Object[] messgaeArgs) {
		return new NullPointerException(I18nMessages.getMessage(messgaeKey, messgaeArgs));
	}

	public static FileNotFoundException createFileNotFoundException(String messgaeKey) {
		return new FileNotFoundException(I18nMessages.getMessage(messgaeKey));
	}

	public static FileNotFoundException createFileNotFoundException(String messgaeKey, Object[] messgaeArgs) {
		return new FileNotFoundException(I18nMessages.getMessage(messgaeKey, messgaeArgs));
	}

	public static NoSuchMethodException createNoSuchMethodException(String messgaeKey) {
		return new NoSuchMethodException(I18nMessages.getMessage(messgaeKey));
	}

	public static NoSuchMethodException createNoSuchMethodException(String messgaeKey, Object[] messgaeArgs) {
		return new NoSuchMethodException(I18nMessages.getMessage(messgaeKey, messgaeArgs));
	}

	public static UnsupportedOperationException createUnsupportedOperationException(String messgaeKey) {
		return new UnsupportedOperationException(I18nMessages.getMessage(messgaeKey));
	}

	public static UnsupportedOperationException createUnsupportedOperationException(String messgaeKey, Object[] messgaeArgs) {
		return new UnsupportedOperationException(I18nMessages.getMessage(messgaeKey, messgaeArgs));
	}

}
