package org.commontemplate.standard.directive;

import java.util.List;

import org.commontemplate.config.MiddleBlockDirectiveHandler;
import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Context;

public abstract class MiddleBlockDirectiveHandlerSupport extends MiddleBlockDirectiveHandler {

	private static final long serialVersionUID = 1L;
	
	public void doRender(Context context, BlockDirective directive) throws Exception {
		doRender(context, directive.getName(),
				directive.getExpression() == null
					? null : directive.getExpression().evaluate(context),
				directive.getElements());
	}

	/**
	 * 块指令渲染处理
	 *
	 * @param context
	 *            上下文
	 * @param directiveName
	 *            指令名
	 * @param param
	 *            表达式参数
	 * @param innerElements
	 *            块指令内部元素列表
	 * @throws Exception
	 *             处理过程中的任意异常都应向上抛出, 由引擎统一处理
	 */
	public abstract void doRender(Context context, String directiveName,
			Object param, List innerElements) throws Exception;

}
