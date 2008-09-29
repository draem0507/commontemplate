package org.commontemplate.standard.scope;

import org.commontemplate.core.Context;
import org.commontemplate.core.LocalContext;

public class ForScopeHandler extends ScopeHandlerSupport {

	private static final long serialVersionUID = 1L;

	private String variableName = "for";

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		if (variableName != null && variableName.length() > 0)
			this.variableName = variableName.trim();
	}

	public Object getScopeVariable(Context context, int level) {
		LocalContext local = context.getCurrentLocalContext();
		int count = 0;
		while (count <= level && local != null) {
			if (count == level) {
				return getLocalVariable(context, local, variableName);
			}
			if(local.getLocalContextName() != null
					&& local.getLocalContextName().equals(variableName))
				count ++;
			local = local.getParentLocalContext();
		}
		return null;
	}

}
