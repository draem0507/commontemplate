package org.commontemplate.util.log;

public class CommonsLoggingProvider implements LoggerProvider {

	public Logger getLogger(String key) {
		return new CommonsLogging(key);
	}

}
