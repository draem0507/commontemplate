package org.commontemplate.standard.directive.template;

import org.commontemplate.config.DirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Expression;
import org.commontemplate.util.TypeUtils;

/**
 * 停止解析指令, 调试时使用.
 *
 * @author liangfei0201@163.com
 *
 */
public class StopDirectiveHandler extends DirectiveHandler {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, Directive directive) throws Exception {
		Expression expression = directive.getExpression();
		if (expression == null) // 无条件停止
			throw new StopException();
		Object param = expression.evaluate(context);
		if (TypeUtils.isTrue(param))
			throw new StopException();
	}

}