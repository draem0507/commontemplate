package org.commontemplate.standard.directive.debug;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.core.LocalContext;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.operator.string.NamePair;
import org.commontemplate.util.Assert;

/**
 * 性能测试指令, 计算其内部块的执行时间.
 *
 * @author liangfei0201@163.com
 *
 */
public class TimeDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		long beginTime = System.currentTimeMillis();
		DirectiveUtils.renderAll(innerElements, context);
		Long value = new Long(System.currentTimeMillis() - beginTime);
		if (param instanceof String)
			context.getParentLocalContext().putVariable((String)param, value);
		else if (param instanceof NamePair) {
			NamePair pair = (NamePair)param;
			String scope = pair.getLeftName();
			String var = pair.getRightName();
			if ("global".equals(scope)) {
				context.getGlobalContext().putVariable(var, value);
			} else if ("root".equals(scope)) {
				context.getRootLocalContext().putVariable(var, value);
			} else if ("super".equals(scope)) {
				context.getParentLocalContext().getParentLocalContext().putVariable(var, value);
			} else if ("local".equals(scope) || "this".equals(scope)) {
				context.getParentLocalContext().putVariable(var, value);
			} else {
				LocalContext localContext = context.findLocalContext(scope);
				if (localContext != null) {
					localContext.putVariable(var, value);
				} else {
					context.getParentLocalContext().putVariable(var, value);
				}
			}
		} else {
			Assert.fail("$time指令参数错误, 参数示例: $time{xxx} 或者 $time{global -> xxx}"); // TODO 未国际化
		}
	}

	public boolean isExpressionNamed() {
		return true;
	}

}
