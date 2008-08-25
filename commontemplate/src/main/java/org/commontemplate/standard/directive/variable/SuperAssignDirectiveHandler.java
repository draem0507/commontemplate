package org.commontemplate.standard.directive.variable;

import java.util.Map.Entry;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;

public class SuperAssignDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		Entry model = (Entry)param;
		String var = String.valueOf(model.getKey());
		Object value = model.getValue();
		context.getParentLocalContext().putVariable(var, value);
	}

	protected boolean isExpressionRequired() {
		return true;
	}

}