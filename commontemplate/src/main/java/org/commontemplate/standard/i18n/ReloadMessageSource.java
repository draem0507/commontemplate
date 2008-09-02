package org.commontemplate.standard.i18n;

import java.util.Locale;

/**
 * 国际化信息源热加载实现
 *
 * @author YangRong
 *
 */
public class ReloadMessageSource extends MessageSourceSupport {

	private static final long serialVersionUID = 1L;

	private ReloadResourceProvider reloadResourceProvider;

	public String getMessage(Locale locale, String key) throws NoSuchMessageException {
		if (key == null)
			throw new NoSuchMessageException(
				"ResourceBundleMessageSource.message.key.required");
		if(locale == null) {
			locale = Locale.getDefault();
		}

		reloadResourceProvider.setResourceLocale(locale);

		try {
			String msg = reloadResourceProvider.getString(key);
			if (msg == null)
				throw new NoSuchMessageException(key, locale);
			return msg;
		} catch (java.util.MissingResourceException e) {
			throw new NoSuchMessageException(key, locale);
		}
	}

	public void setReloadResourceProvider(
			ReloadResourceProvider reloadResourceProvider) {
		this.reloadResourceProvider = reloadResourceProvider;
	}

}
