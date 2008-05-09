package org.commontemplate.standard.directive.macro;

import java.util.List;
import java.util.Map;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.standard.directive.ParameterUtils;

public class InnerDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;
	
	public static final String INNER_BLOCK = "inner_block";

	public void doRender(Context context, String directiveName, Object param)
			throws Exception {
		List inner = (List)context.lookupObject(INNER_BLOCK);
		if (inner != null) {
			Map model = ParameterUtils.getParameters(param);
			context.defineAllVariables(model);
			DirectiveUtils.renderAll(inner, context);
		}
	}

}
