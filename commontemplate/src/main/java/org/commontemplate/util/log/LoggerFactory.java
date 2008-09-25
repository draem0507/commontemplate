package org.commontemplate.util.log;

/**
 * 日志输出器工厂
 *
 * @author liangfei0201@163.com
 *
 */
public class LoggerFactory {

	private LoggerFactory() {}

	static {
		try {
			Class.forName("org.apache.commons.logging.LogFactory", true, Thread.currentThread().getContextClassLoader());
			setLoggerProvider(new CommonsLoggingProvider());
		} catch (ClassNotFoundException e) {
			setLoggerProvider(new SimpleLoggerProvider());
		}
	}

	private static LoggerProvider loggerProvider;

	/**
	 * 设置日志输出器供给器
	 *
	 * @param loggerProvider 日志输出器供给器
	 */
	public static synchronized void setLoggerProvider(LoggerProvider loggerProvider) {
		if (loggerProvider != null)
			LoggerFactory.loggerProvider = loggerProvider;
	}

	/**
	 * 获取日志输出器
	 *
	 * @param key 分类键
	 * @return 日志输出器, 后验条件: 不返回null.
	 */
	public static Logger getLogger(Class key) {
		return getLogger(key == null ? null : key.getName());
	}

	/**
	 * 获取日志输出器
	 *
	 * @param key 分类键
	 * @return 日志输出器, 后验条件: 不返回null.
	 */
	public static Logger getLogger(String key) {
		return loggerProvider.getLogger(key);
	}

}
