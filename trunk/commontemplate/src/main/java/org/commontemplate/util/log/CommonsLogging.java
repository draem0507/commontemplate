package org.commontemplate.util.log;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 适配CommonsLogging，依赖于commons-logging.jar
 * <br/>
 * 有关CommonsLogging详细信息请参阅：<a target="_blank" href="http://www.apache.org/">http://www.apache.org/</a>
 *
 * @author liangfei0201@163.com
 *
 */
public class CommonsLogging implements Logger, Serializable {

	private static final long serialVersionUID = 1L;

	private final Log log;

	public CommonsLogging() {
		 log = LogFactory.getLog(CommonsLogging.class);
	}

	public CommonsLogging(String logKey) {
		this.log = LogFactory.getLog(logKey);
	}

	public CommonsLogging(Class logKeyClass) {
		this.log = LogFactory.getLog(logKeyClass);
	}

	public CommonsLogging(Log log) {
		this.log = log;
	}

	public void debug(String msg) {
		log.debug(msg);
	}

	public void debug(String msg, Throwable e) {
		log.debug(msg, e);
	}

	public void info(String msg) {
		log.info(msg);
	}

	public void info(String msg, Throwable e) {
		log.info(msg, e);
	}

	public void warn(String msg) {
		log.warn(msg);
	}

	public void warn(String msg, Throwable e) {
		log.warn(msg, e);
	}

	public void error(String msg) {
		log.error(msg);
	}

	public void error(String msg, Throwable e) {
		log.error(msg, e);
	}

	public boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}

	public boolean isInfoEnabled() {
		return log.isInfoEnabled();
	}

	public boolean isWarnEnabled() {
		return log.isWarnEnabled();
	}

	public boolean isErrorEnabled() {
		return log.isErrorEnabled();
	}

	public boolean isFatalEnabled() {
		return log.isFatalEnabled();
	}

}
