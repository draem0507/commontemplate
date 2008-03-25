package org.commontemplate.standard.directive.block;

import java.util.List;

import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.config.LineDirectiveHandler;
import org.commontemplate.core.Context;

public class ShowBlockDirectiveHandler implements LineDirectiveHandler {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param) throws Exception {
		if (param == null)
			throw new java.lang.IllegalArgumentException("$show 指令必需指定引用块名称! 如: $show{\"blockName\"}");
		String blockName = (String)param;
		DirectiveUtils.renderAll(((List)context.lookupObject(BlockDefineDirectiveHandler.BLOCK_TYPE, blockName)), context);
	}

}