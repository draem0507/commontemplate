package org.commontemplate.util.log;

public class NoneLoggerProvider implements LoggerProvider {

	public Logger getLogger(String key) {
		return NoneLogger.getInstance();
	}

}
