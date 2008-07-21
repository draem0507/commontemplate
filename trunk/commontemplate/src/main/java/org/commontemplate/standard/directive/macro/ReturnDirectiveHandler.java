package org.commontemplate.standard.directive.macro;

import org.commontemplate.config.DirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;
import org.commontemplate.util.TypeUtils;

public class ReturnDirectiveHandler extends DirectiveHandler {

	private static final long serialVersionUID = 1L;

	public void doRender(Context context, Directive directive) throws Exception {
		if (directive.getExpression() == null) // 无条件中断
			throw new ReturnException();
		Object param = directive.getExpression().evaluate(context);
		if (TypeUtils.isTrue(param))
			throw new ReturnException();
	}

}