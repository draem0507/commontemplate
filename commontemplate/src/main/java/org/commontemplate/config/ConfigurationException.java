package org.commontemplate.config;

/**
 * 配置异常
 * 
 * @see org.commontemplate.config.Configuration
 * @author liangfei0201@163.com
 * 
 */
public class ConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConfigurationException() {
		super();
	}

	public ConfigurationException(String message) {
		super(message);
	}

	public ConfigurationException(Throwable cause) {
		super(cause);
	}

	public ConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

}
