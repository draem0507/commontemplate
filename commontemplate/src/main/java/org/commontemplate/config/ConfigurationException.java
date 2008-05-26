package org.commontemplate.config;

import org.commontemplate.util.I18nRuntimeException;

/**
 * 配置异常
 *
 * @see org.commontemplate.config.Configuration
 * @author liangfei0201@163.com
 *
 */
public class ConfigurationException extends I18nRuntimeException {

	private static final long serialVersionUID = 1L;

	public ConfigurationException() {
		super();
	}

	public ConfigurationException(String messageKey, Object[] messageArgs,
			Throwable cause) {
		super(messageKey, messageArgs, cause);
	}

	public ConfigurationException(String messageKey, Object[] messageArgs) {
		super(messageKey, messageArgs);
	}

	public ConfigurationException(String messageKey) {
		super(messageKey);
	}

	public ConfigurationException(Throwable cause) {
		super(cause);
	}

	public ConfigurationException(String messageKey, Throwable cause) {
		super(messageKey, cause);
	}

}
