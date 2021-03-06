package org.commontemplate.util.log;

/**
 * 日志输出器工厂
 *
 * @author liangfei0201@163.com
 *
 */
public class LoggerFactory {

	private LoggerFactory() {}

	static { // 查找常用的日志框架
		try {
			LoggerProvider loggerProvider = new CommonsLoggerProvider();
			Logger logger = loggerProvider.getLogger(LoggerFactory.class.getName());
			logger.info("using LoggerProvider:" + CommonsLoggerProvider.class.getName());
			setLoggerProvider(loggerProvider);
		} catch (Throwable e) {
			try {
				LoggerProvider loggerProvider = new Log4jLoggerProvider();
				Logger logger = loggerProvider.getLogger(LoggerFactory.class.getName());
				logger.info("using LoggerProvider:" + Log4jLoggerProvider.class.getName());
				setLoggerProvider(loggerProvider);
			} catch (Throwable e2) {
				try {
					LoggerProvider loggerProvider = new AvalonLoggerProvider();
					Logger logger = loggerProvider.getLogger(LoggerFactory.class.getName());
					logger.info("using LoggerProvider:" + AvalonLoggerProvider.class.getName());
					setLoggerProvider(loggerProvider);
				} catch (Throwable e3) {
					try {
						LoggerProvider loggerProvider = new Slf4jLoggerProvider();
						Logger logger = loggerProvider.getLogger(LoggerFactory.class.getName());
						logger.info("using LoggerProvider:" + Slf4jLoggerProvider.class.getName());
						setLoggerProvider(loggerProvider);
					} catch (Throwable e4) {
						try {
							LoggerProvider loggerProvider = new JdkLoggerProvider();
							Logger logger = loggerProvider.getLogger(LoggerFactory.class.getName());
							logger.info("using LoggerProvider:" + JdkLoggerProvider.class.getName());
							setLoggerProvider(loggerProvider);
						} catch (Throwable e5) {
							LoggerProvider loggerProvider = new SimpleLoggerProvider();
							Logger logger = loggerProvider.getLogger(LoggerFactory.class.getName());
							logger.info("using LoggerProvider:" + SimpleLoggerProvider.class.getName());
							setLoggerProvider(loggerProvider);
						}
					}
				}
			}
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
