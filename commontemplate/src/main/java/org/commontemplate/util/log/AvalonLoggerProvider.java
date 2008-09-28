package org.commontemplate.util.log;

public class AvalonLoggerProvider implements LoggerProvider {

	public Logger getLogger(String key) {
		return new AvalonLogger(org.apache.log.Hierarchy.getDefaultHierarchy().getLoggerFor(key));
	}

}
