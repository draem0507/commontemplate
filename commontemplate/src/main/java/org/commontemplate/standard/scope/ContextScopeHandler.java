package org.commontemplate.standard.scope;

import java.io.Serializable;

import org.commontemplate.config.ScopeHandler;
import org.commontemplate.core.Context;

public class ContextScopeHandler implements ScopeHandler, Serializable {

	private static final long serialVersionUID = 1L;

	public Object getScopeVariable(Context context, int level) {
		return context;
	}

}
