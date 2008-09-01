package org.commontemplate.standard.directive.block;

import java.util.List;

import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.util.Assert;
import org.commontemplate.core.Context;

public class ShowBlockDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		Assert.assertNotNull(param, "ShowBlockDirectiveHandler.parameter.required");
		Assert.assertTrue(param instanceof String, "ShowBlockDirectiveHandler.parameter.type.error");
		String blockName = (String)param;
		DirectiveUtils.renderAll(((List)context.getProperty(BlockDefineDirectiveHandler.BLOCK_TYPE, blockName)), context);
	}

	public boolean isExpressionRequired() {
		return true;
	}

	public boolean isExpressionNamed() {
		return true;
	}

}