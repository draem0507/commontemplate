package org.commontemplate.standard.directive.filter;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.filter.BufferedFilter;
import org.commontemplate.util.Assert;

/**
 * 捕获内部块输出，保存到指定的变量中。如：
 * $capture{"xxx"}
 *     ...
 * $end
 *
 * @author liangfei0201@163.com
 *
 */
public class CaptureDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		Assert.assertTrue(param instanceof String);
		String var = (String)param;
		BufferedFilter bufferedFilter = new BufferedFilter();
		context.setOutputFilter(bufferedFilter);
		DirectiveUtils.renderAll(innerElements, context);
		context.removeOutputFilter();
		String value = bufferedFilter.getBuffered();
		context.getSuperLocalContext().putVariable(var, value);
	}

}
