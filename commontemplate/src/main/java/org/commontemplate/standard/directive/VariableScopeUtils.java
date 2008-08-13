package org.commontemplate.standard.directive;

import org.commontemplate.core.Context;
import org.commontemplate.core.LocalContext;
import org.commontemplate.standard.operator.string.NamePair;

public class VariableScopeUtils {

	private VariableScopeUtils(){}

	public static void putVariable(Context context, boolean baseSuper, NamePair pair, Object value) {
		LocalContext current;
		if (baseSuper)
			current = context.getParentLocalContext();
		else
			current = context.getCurrentLocalContext();
		String scope = pair.getLeftName();
		String var = pair.getRightName();
		if ("global".equals(scope)) {
			context.getGlobalContext().putVariable(var, value);
		} else if ("root".equals(scope)) {
			context.getRootLocalContext().putVariable(var, value);
		} else if ("super".equals(scope)) {
			current.getParentLocalContext().putVariable(var, value);
		} else if ("local".equals(scope) || "this".equals(scope)) {
			current.putVariable(var, value);
		} else {
			LocalContext localContext = context.findLocalContext(scope);
			if (localContext != null) {
				localContext.putVariable(var, value);
			} else {
				current.putVariable(var, value);
			}
		}
	}

}
