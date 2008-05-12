package org.commontemplate.standard.directive.iteration;

import org.commontemplate.config.DirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;
import org.commontemplate.util.TypeUtils;

/**
 * 循环中断指令, 用于<code>ForeachDirectiveHandler<code>内部
 *
 * @author liangfei0201@163.com
 *
 */
public class BreakDirectiveHandler extends DirectiveHandler {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, Directive directive) throws Exception {
		if (directive.getExpression() == null) // 无条件中断
			throw new BreakException();
		Object param = directive.getExpression().evaluate(context);
		if (TypeUtils.isTrue(param))
			throw new BreakException();
	}

}