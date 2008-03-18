package org.commontemplate.engine;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.commontemplate.core.MessageSource;
import org.commontemplate.core.NoSuchMessageException;

/**
 * 国际化信息源实现
 * 
 * @author liangfei0201@163.com
 *
 */
final class MessageSourceImpl implements MessageSource {
	
	private final ResourceBundle resourceBundle;
	
	private final Locale locale;
	
	MessageSourceImpl(ResourceBundle resourceBundle, Locale locale) {
		this.resourceBundle = resourceBundle;
		this.locale = locale;
	}

	public String getMessage(String key) throws NoSuchMessageException {
		if (resourceBundle == null) 
			throw new NoSuchMessageException("未找到信息源ResourceBundle! 请检查是否配置了ResourceBundleFactory或MessageBasename!");
		String msg = resourceBundle.getString(key);
		if (msg == null)
			throw new NoSuchMessageException(key, locale);
		return msg;
	}

	public String getMessage(String key, Object[] args) throws NoSuchMessageException {
		return formatMessage(getMessage(key), args);
	}

	public String getMessage(String key, String defaultValue) {
		if (resourceBundle == null)
			return defaultValue;
		String msg = resourceBundle.getString(key);
		if (msg == null)
			return defaultValue;
		return msg;
	}

	public String getMessage(String key, Object[] args, String defaultValue) {
		return formatMessage(getMessage(key, defaultValue), args);
	}
	
	private final String formatMessage(String msg, Object[] args) {
		if (msg != null && msg.length() > 0
				&& args != null && args.length > 0) {
			return new MessageFormat(msg, locale).format(args);
		}
		return msg;
	}
	
}