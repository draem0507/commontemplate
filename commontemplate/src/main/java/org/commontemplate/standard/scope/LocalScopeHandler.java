package org.commontemplate.standard.scope;

import java.io.Serializable;

import org.commontemplate.config.ScopeHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.LocalContext;

public class LocalScopeHandler implements ScopeHandler, Serializable {

	private static final long serialVersionUID = 1L;

	public Object getScopeVariable(Context context, int level) {
		if (level == 0)
			return context.getCurrentLocalContext();
		LocalContext root = context.getRootLocalContext();
		LocalContext local = context.getCurrentLocalContext();
		for (int i = 0; i < level; i ++) {
			local = local.getParentLocalContext();
			if (local == root)
				return root;
			if (local == null)
				return context.getGlobalContext();
		}
		return local;
	}

}
