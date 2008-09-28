package org.commontemplate.standard.scope;

import org.commontemplate.config.ScopeHandler;
import org.commontemplate.core.Context;

public class GlobalScopeHandler implements ScopeHandler {

	public Object getScopeVariable(Context context, int level) {
		return context.getGlobalContext();
	}

}
