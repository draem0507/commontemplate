package org.commontemplate.standard.directive.iteration;

import java.util.List;

import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.config.MiddleBlockDirectiveHandler;
import org.commontemplate.core.Context;
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
public class ForeachElseDirectiveHandler implements MiddleBlockDirectiveHandler {
	
	private static final long serialVersionUID = 1L;
	
	public void doRender(Context context, String directiveName,
			Object param, List innerElements)
			throws Exception {
		Assert.assertNotNull(context.getSuperLocalContext().getStatus(ForeachDirectiveHandler.FOR_STATUS), "without 的嵌套关系错误, 必需在 for 后面!");
		if (! ((Boolean)context.getSuperLocalContext().getStatus(ForeachDirectiveHandler.FOR_STATUS)).booleanValue()) 
			DirectiveUtils.renderAll(innerElements, context);
		context.getSuperLocalContext().removeStatus(ForeachDirectiveHandler.FOR_STATUS);
	}

}
