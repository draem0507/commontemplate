package org.commontemplate.standard.directive.filter;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.directive.VariableScopeUtils;
import org.commontemplate.standard.filter.BufferedFilter;
import org.commontemplate.standard.operator.string.NamePair;
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

	protected void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		Assert.assertTrue(param instanceof String);
		BufferedFilter bufferedFilter = new BufferedFilter();
		context.setOutputFilter(bufferedFilter);
		DirectiveUtils.renderAll(innerElements, context);
		context.removeOutputFilter();
		String value = bufferedFilter.getBuffered();
		if (param instanceof String) {
			context.getParentLocalContext().putVariable((String)param, value);
		} else if (param instanceof NamePair) {
			VariableScopeUtils.putVariable(context, true, (NamePair)param, value);
		} else {
			Assert.fail("$capture指令参数错误, 参数示例: $capture{xxx} 或者 $capture{global -> xxx}"); // TODO 未国际化
		}
	}

	protected boolean isExpressionRequired() {
		return true;
	}

	public boolean isExpressionNamed() {
		return true;
	}

}
