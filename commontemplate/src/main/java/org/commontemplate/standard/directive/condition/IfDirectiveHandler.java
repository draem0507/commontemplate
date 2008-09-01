package org.commontemplate.standard.directive.condition;


import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.core.LocalContext;
import org.commontemplate.standard.directive.BlockDirectiveHandlerSupport;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.util.TypeUtils;

/**
 * 条件指令"如果"处理器
 *
 * @author liangfei0201@163.com
 *
 */
public class IfDirectiveHandler extends BlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	// 条件状态位，用于传递整个If链是否已经为true
	public static final String IF_STATUS = "if.status";

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		boolean condition = TypeUtils.isTrue(param);
		if (condition)
			DirectiveUtils.renderAll(innerElements, context);
		LocalContext superLocalContext = context.getParentLocalContext();
		superLocalContext.setStatus(IF_STATUS, Boolean.valueOf(condition));
	}

	public boolean isExpressionRequired() {
		return true;
	}

}
