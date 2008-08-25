package org.commontemplate.standard.directive.condition;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.directive.MiddleBlockDirectiveHandlerSupport;
import org.commontemplate.util.Assert;

/**
 * 条件指令"否则"处理器
 *
 * @author liangfei0201@163.com
 *
 */
public class ElseDirectiveHandler extends MiddleBlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	protected void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		Assert.assertNotNull(context.getParentLocalContext().getStatus(IfDirectiveHandler.IF_STATUS), "ElseDirectiveHandler.location.error");
		if (! ((Boolean)context.getParentLocalContext().getStatus(IfDirectiveHandler.IF_STATUS)).booleanValue())
			DirectiveUtils.renderAll(innerElements, context);
		context.getParentLocalContext().removeStatus(IfDirectiveHandler.IF_STATUS);
	}

}
