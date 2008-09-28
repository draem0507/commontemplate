package org.commontemplate.util.log;

import org.apache.log4j.LogManager;

public class Log4jLoggerProvider implements LoggerProvider {

	public Logger getLogger(String key) {
		return new Log4jLogger(LogManager.getLogger(key));
	}

}
