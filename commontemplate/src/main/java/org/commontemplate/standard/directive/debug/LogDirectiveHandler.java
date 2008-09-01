package org.commontemplate.standard.directive.debug;

import java.util.Map.Entry;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.standard.log.Logger;
import org.commontemplate.util.Assert;

public class LogDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	private Logger logger;

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		if (param instanceof String) {
			logger.debug((String)param);
		} else if (param instanceof Entry) {
			Entry entry = (Entry)param;
			String level = (String)entry.getKey();
			String msg = (String)entry.getValue();
			if ("debug".equals(level)) {
				logger.debug(msg);
			} else if ("info".equals(level)) {
				logger.info(msg);
			} else if ("warn".equals(level)) {
				logger.warn(msg);
			} else if ("error".equals(level)) {
				logger.error(msg);
			} else {
				Assert.fail("LogDirectiveHandler.log.level.error", new Object[]{level});
			}
		} else {
			Assert.fail("LogDirectiveHandler.parameter.error", new Object[]{param});
		}
	}

	public boolean isExpressionRequired() {
		return true;
	}

}
