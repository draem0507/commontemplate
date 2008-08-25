package org.commontemplate.standard.directive;

import org.commontemplate.config.DirectiveHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;
import org.commontemplate.util.I18nExceptionFactory;

public abstract class DirectiveHandlerSupport extends DirectiveHandler {

	public void doRender(Context context, Directive directive) throws Exception {
		if (directive.getExpression() == null && isExpressionRequired())
			throw I18nExceptionFactory.createIllegalStateException("DirectiveHandlerSupport.expression.is.null", new Object[]{directive.getName()});
		doRender(context, directive.getName(),
				directive.getExpression() == null
					? null : directive.getExpression().evaluate(context));
	}

	/**
	 * 行指令渲染处理
	 *
	 * @param context
	 *            上下文
	 * @param directiveName
	 *            指令名
	 * @param param
	 *            表达式参数
	 * @throws Exception
	 *             处理过程中的任意异常都应向上抛出, 由引擎统一处理
	 */
	protected abstract void doRender(Context context, String directiveName,
			Object param) throws Exception;

	protected boolean isExpressionRequired() {
		return false;
	}

}
