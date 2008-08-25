package org.commontemplate.standard.directive.eval;

import org.commontemplate.standard.directive.DirectiveHandlerSupport;
import org.commontemplate.core.Context;

/**
 * 直接将字符串作为模板解析指令.如: $exec{"$if{xxx}aaa$end"}
 *
 * @author liangfei0201@163.com
 *
 */
public class ExecuteDirectiveHandler extends DirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	protected void doRender(Context context, String directiveName, Object param) throws Exception {
		if (param != null)
			context.parseTemplate((String)param).render(context);
	}

	protected boolean isExpressionRequired() {
		return true;
	}

}