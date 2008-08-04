package org.commontemplate.standard.directive.iteration;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.directive.MiddleBlockDirectiveHandlerSupport;
import org.commontemplate.util.Assert;

/**
 * 空集合循环处理指令.
 * <p/>
 * 如：
 * <code>
 * for {item : items}
 *     ...
 * forelse
 *     have no items...
 * end
 * </code>
 *
 * @author liangfei0201@163.com
 *
 */
public class ForeachElseDirectiveHandler extends MiddleBlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName,
			Object param, List innerElements)
			throws Exception {
		Assert.assertNotNull(context.getParentLocalContext().getStatus(ForeachDirectiveHandler.FOR_STATUS), "ForeachElseDirectiveHandler.location.error");
		if (! ((Boolean)context.getParentLocalContext().getStatus(ForeachDirectiveHandler.FOR_STATUS)).booleanValue())
			DirectiveUtils.renderAll(innerElements, context);
		context.getParentLocalContext().removeStatus(ForeachDirectiveHandler.FOR_STATUS);
	}

}
