package org.commontemplate.standard.directive.debug;

import org.commontemplate.util.TypeUtils;
import org.commontemplate.config.DirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;

/**
 * 停止解析指令, 调试时使用.
 *
 * @author liangfei0201@163.com
 *
 */
public class StopDirectiveHandler extends DirectiveHandler {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, Directive directive) throws Exception {
		if (directive.getExpression() == null) // 无条件停止
			throw new StopException();
		Object param = directive.getExpression().evaluate(context);
		if (TypeUtils.isTrue(param))
			throw new StopException();
	}

}