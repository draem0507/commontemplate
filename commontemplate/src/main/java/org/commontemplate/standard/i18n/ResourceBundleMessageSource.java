package org.commontemplate.standard.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 国际化信息源ResourceBundle适配实现
 *
 * @author liangfei0201@163.com
 *
 */
public class ResourceBundleMessageSource extends MessageSourceSupport {

	private static final long serialVersionUID = 1L;

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
		try {
			String msg = resourceBundle.getString(key);
			if (msg == null)
				throw new NoSuchMessageException(key, resourceBundle.getLocale());
			return msg;
		} catch (java.util.MissingResourceException e) {
			throw new NoSuchMessageException(key, resourceBundle.getLocale());
		}
	}

}
