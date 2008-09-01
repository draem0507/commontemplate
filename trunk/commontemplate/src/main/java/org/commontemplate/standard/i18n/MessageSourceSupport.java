package org.commontemplate.standard.i18n;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;

public abstract class MessageSourceSupport implements MessageSource, Serializable {

	public String getMessage(Locale locale, String key, Object[] args)
			throws NoSuchMessageException {
		return formatMessage(locale, getMessage(locale, key), args);
	}

	public String getMessage(Locale locale, String key, String defaultValue) {
		if (key == null)
			return defaultValue;
		try {
			String msg = getMessage(locale, key);
			if (msg == null)
				return defaultValue;
			return msg;
		} catch (NoSuchMessageException e) {
			return defaultValue;
		} catch (MissingResourceException e) {
			return defaultValue;
		}
	}

	public String getMessage(Locale locale, String key, Object[] args, String defaultValue) {
		return formatMessage(locale, getMessage(locale, key, defaultValue), args);
	}

	protected String formatMessage(Locale locale, String msg, Object[] args) {
		if (msg != null && msg.length() > 0 && args != null && args.length > 0) {
			return new MessageFormat(msg, locale).format(args);
		}
		return msg;
	}

}
