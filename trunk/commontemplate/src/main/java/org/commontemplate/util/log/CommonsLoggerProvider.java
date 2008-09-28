package org.commontemplate.util.log;

import org.apache.commons.logging.LogFactory;

public class CommonsLoggerProvider implements LoggerProvider {

	public Logger getLogger(String key) {
		return new CommonsLogger(LogFactory.getLog(key));
	}

}
