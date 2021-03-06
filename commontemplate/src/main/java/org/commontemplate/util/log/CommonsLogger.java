package org.commontemplate.util.log;

import java.io.Serializable;

import org.apache.commons.logging.Log;

/**
 * 适配CommonsLogging，依赖于commons-logging.jar
 * <br/>
 * 有关CommonsLogging详细信息请参阅：<a target="_blank" href="http://www.apache.org/">http://www.apache.org/</a>
 *
 * @author liangfei0201@163.com
 *
 */
public class CommonsLogger implements Logger, Serializable {

	private static final long serialVersionUID = 1L;

	private final Log logger;

	public CommonsLogger(Log logger) {
		this.logger = logger;
	}

	public void debug(String msg) {
		logger.debug(msg);
	}

	public void debug(String msg, Throwable e) {
		logger.debug(msg, e);
	}

	public void info(String msg) {
		logger.info(msg);
	}

	public void info(String msg, Throwable e) {
		logger.info(msg, e);
	}

	public void warn(String msg) {
		logger.warn(msg);
	}

	public void warn(String msg, Throwable e) {
		logger.warn(msg, e);
	}

	public void error(String msg) {
		logger.error(msg);
	}

	public void error(String msg, Throwable e) {
		logger.error(msg, e);
	}

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	public boolean isFatalEnabled() {
		return logger.isFatalEnabled();
	}

}
