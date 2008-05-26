package org.commontemplate.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public final class I18nMessages {

	private I18nMessages() {}

	private static ResourceBundle resourceBundle = ResourceBundle
			.getBundle(I18nMessages.class.getPackage().getName().replace('.', '/') + "/messages",
					Locale.getDefault(),
					I18nMessages.class.getClassLoader());

	public static synchronized void setResourceBundle(ResourceBundle rb) {
		resourceBundle = rb;
	}

	private static ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	private static Locale getLocale() {
		return Locale.getDefault();
	}

	public static String getMessage(String key) {
		if (key == null)
			return "";
		ResourceBundle resourceBundle = getResourceBundle();
		if (resourceBundle == null)
			return key;
		String msg;
		try {
			msg = resourceBundle.getString(key);
		} catch (java.util.MissingResourceException e) {
			msg = null;
		}
		if (msg == null)
			return key;
		return msg;
	}

	public static String getMessage(String key, Object[] args) {
		return formatMessage(getMessage(key), args);
	}

	public static String getMessage(String key, String defaultValue) {
		if (key == null)
			return defaultValue;
		ResourceBundle resourceBundle = getResourceBundle();
		if (resourceBundle == null)
			return defaultValue;
		String msg;
		try {
			msg = resourceBundle.getString(key);
		} catch (java.util.MissingResourceException e) {
			msg = null;
		}
		if (msg == null)
			return defaultValue;
		return msg;
	}

	public static String getMessage(String key, Object[] args, String defaultValue) {
		return formatMessage(getMessage(key, defaultValue), args);
	}

	private static String formatMessage(String msg, Object[] args) {
		if (msg != null && msg.length() > 0 && args != null && args.length > 0) {
			return new MessageFormat(msg, getLocale()).format(args);
		}
		return msg;
	}

}
