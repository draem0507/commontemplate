package org.commontemplate.standard.directive.variable;

import java.util.Map.Entry;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;

public class LocalAssignDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		Entry model = (Entry)param;
		String var = model.getKey().toString();
		Object value = model.getValue();
		if (context.isVariableContained(var)) {
			context.setVariable(var, value);
		} else {
			context.putVariable(var, value);
		}
	}

}