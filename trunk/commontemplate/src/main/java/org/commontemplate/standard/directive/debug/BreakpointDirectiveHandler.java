package org.commontemplate.standard.directive.debug;

import org.commontemplate.config.LineDirectiveHandler;
import org.commontemplate.core.Context;

public class BreakpointDirectiveHandler implements LineDirectiveHandler {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		context.setStep(true);
	}

}
