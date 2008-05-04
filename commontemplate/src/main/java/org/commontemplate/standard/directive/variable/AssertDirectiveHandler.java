package org.commontemplate.standard.directive.variable;

import org.commontemplate.config.LineDirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.util.TypeUtils;

public class AssertDirectiveHandler implements LineDirectiveHandler {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param)
			throws Exception {
		if (! TypeUtils.isTrue(param))
			throw new java.lang.AssertionError();
	}

}
