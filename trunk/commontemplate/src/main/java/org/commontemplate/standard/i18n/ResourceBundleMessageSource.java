package org.commontemplate.standard.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * 国际化信息源实现
 *
 * @author liangfei0201@163.com
 *
 */
public class ResourceBundleMessageSource implements MessageSource {

	private ResourceBundleProvider resourceBundleProvider;

	public void setResourceBundleProvider(
			ResourceBundleProvider resourceBundleProvider) {
		this.resourceBundleProvider = resourceBundleProvider;
	}

	private ResourceBundle getResourceBundle(Locale locale) {
		return resourceBundleProvider == null ? null : resourceBundleProvider.getResourceBundle(locale);
	}

	public String getMessage(Locale locale, String key) throws NoSuchMessageException {
		if (key == null)
			throw new NoSuchMessageException(
				"ResourceBundleMessageSource.message.key.required");
		if (locale == null)
			locale = Locale.getDefault();
		ResourceBundle resourceBundle = getResourceBundle(locale);
		if (resourceBundle == null)
			throw new NoSuchMessageException(
					"ResourceBundleMessageSource.resource.bundle.required");
		String msg;
		try {
			msg = resourceBundle.getString(key);
		} catch (java.util.MissingResourceException e) {
			msg = null;
		}
		if (msg == null)
			throw new NoSuchMessageException(key, resourceBundle.getLocale());
		return msg;
	}

	public String getMessage(Locale locale, String key, Object[] args)
			throws NoSuchMessageException {
		return formatMessage(locale, getMessage(locale, key), args);
	}

	public String getMessage(Locale locale, String key, String defaultValue) {
		if (key == null)
			return defaultValue;
		if (locale == null)
			locale = Locale.getDefault();
		ResourceBundle resourceBundle = getResourceBundle(locale);
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

	public String getMessage(Locale locale, String key, Object[] args, String defaultValue) {
		return formatMessage(locale, getMessage(locale, key, defaultValue), args);
	}

	private String formatMessage(Locale locale, String msg, Object[] args) {
		if (msg != null && msg.length() > 0 && args != null && args.length > 0) {
			return new MessageFormat(msg, locale).format(args);
		}
		return msg;
	}

}
