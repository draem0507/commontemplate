package org.commontemplate.standard.i18n;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 国际化信息源热加载实现
 *
 * @author YangRong
 *
 */
public class ReloadableMessageSource extends MessageSourceSupport {

	private static final long serialVersionUID = 1L;
	
	private String basename;
	
	private ReloadableResourceProvider reloadResourceProvider;
	
	private Map extInfo;

	public String getMessage(Locale locale, String key) throws NoSuchMessageException {
		if (key == null)
			throw new NoSuchMessageException(
				"ResourceBundleMessageSource.message.key.required");
		if(locale == null) {
			locale = Locale.getDefault();
		}

		try {
			if(extInfo == null) {
				extInfo = new HashMap();
			}
			String msg = (String) reloadResourceProvider.getObject(basename, locale, key, extInfo);
			if (msg == null)
				throw new NoSuchMessageException(key, locale);
			return msg;
		} catch (java.util.MissingResourceException e) {
			throw new NoSuchMessageException(key, locale);
		}
	}

	public void setReloadResourceProvider(
			ReloadableResourceProvider reloadResourceProvider) {
		this.reloadResourceProvider = reloadResourceProvider;
	}

	public String getBasename() {
		return basename;
	}

	public void setBasename(String basename) {
		this.basename = basename;
	}

	public Map getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(Map extInfo) {
		this.extInfo = extInfo;
	}

}
