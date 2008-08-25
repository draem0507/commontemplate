package org.commontemplate.standard.directive.condition;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.directive.MiddleBlockDirectiveHandlerSupport;
import org.commontemplate.util.Assert;
import org.commontemplate.util.TypeUtils;

/**
 * 条件指令"否则如果"处理器
 *
 * @author liangfei0201@163.com
 *
 */
public class ElseIfDirectiveHandler extends MiddleBlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		Assert.assertNotNull(context.getParentLocalContext().getStatus(IfDirectiveHandler.IF_STATUS), "ElseIfDirectiveHandler.location.error");
		if (! ((Boolean)context.getParentLocalContext().getStatus(IfDirectiveHandler.IF_STATUS)).booleanValue()
				&& TypeUtils.isTrue(param)) {
			DirectiveUtils.renderAll(innerElements, context);
			context.getParentLocalContext().setStatus(IfDirectiveHandler.IF_STATUS, Boolean.TRUE);
		}
	}

	protected boolean isExpressionRequired() {
		return true;
	}

}
