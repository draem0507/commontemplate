package com.xxx.yyy;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.directive.DirectiveUtils;

public class RoleDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param,
			List innerElements) throws Exception {
		String role = (String)context.getVariable("role");
		if (role != null &&
				((param instanceof String && ((String)param).equalsIgnoreCase(role))
				|| (param instanceof List && ((List)param).contains(role)))) {
			DirectiveUtils.renderAll(innerElements, context);
		}
	}

}
