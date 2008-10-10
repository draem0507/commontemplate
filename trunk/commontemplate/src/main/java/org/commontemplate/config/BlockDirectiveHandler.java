package org.commontemplate.config;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;

/**
 * 起始嵌套指令处理器, 如: if, for等. (实现类应保证线程安全，及友好的序列化)
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class BlockDirectiveHandler extends DirectiveHandler {

	private static final long serialVersionUID = 1L;
	
	// 接口适配
	public final void doRender(Context context, Directive directive) throws Exception {
		doRender(context, (BlockDirective)directive);
	}

	/**
	 * 块指令渲染处理
	 *
	 * @param context
	 *            上下文
	 * @param directive
	 *            指令
	 * @throws Exception
	 *             处理过程中的任意异常都应向上抛出, 由引擎统一处理
	 */
	public abstract void doRender(Context context, BlockDirective directive) throws Exception;

}
