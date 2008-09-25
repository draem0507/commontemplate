package org.commontemplate.util.log;

public class SimpleLoggerProvider implements LoggerProvider {

	public Logger getLogger(String key) {
		return new SimpleLogger(key);
	}

}
