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

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		Assert.assertNotNull(context.getSuperLocalContext().getStatus(IfDirectiveHandler.IF_STATUS), "else 的嵌套关系错误, 必需在 if 或 elseif 后面!");
		if (! ((Boolean)context.getSuperLocalContext().getStatus(IfDirectiveHandler.IF_STATUS)).booleanValue())
			DirectiveUtils.renderAll(innerElements, context);
		context.getSuperLocalContext().removeStatus(IfDirectiveHandler.IF_STATUS);
	}

}
