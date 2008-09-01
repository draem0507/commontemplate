package org.commontemplate.standard.directive.debug;

import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.directive.VariableScopeUtils;
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
		if (param instanceof String) {
			context.getParentLocalContext().putVariable((String)param, value);
		} else if (param instanceof NamePair) {
			VariableScopeUtils.putVariable(context, true, (NamePair)param, value);
		} else {
			Assert.fail("$block指令参数错误, 参数示例: $block{xxx} 或者 $block{global -> xxx}"); // TODO 未国际化
		}
	}

	public boolean isExpressionRequired() {
		return true;
	}

	public boolean isExpressionNamed() {
		return true;
	}

}
