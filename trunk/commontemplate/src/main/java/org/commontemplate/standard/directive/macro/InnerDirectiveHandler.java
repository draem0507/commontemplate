package org.commontemplate.standard.directive.macro;

import java.util.ArrayList;
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
		List inner = (List)context.getProperty(INNER_BLOCK);
		if (inner != null && inner.size() > 0) {
			List parentInner = (List)context.getParentLocalContext().getProperty(INNER_BLOCK);
			Map model = ParameterUtils.getParameters(param);
			context.pushLocalContext(model);
            try {
	            context.putProperty(INNER_BLOCK, parentInner == null ? new ArrayList(0) : parentInner);
    	        DirectiveUtils.renderAll(inner, context);
	        } finally {
	            context.popLocalContext();
	        }
		}
	}

}
