package org.commontemplate.config;

import org.commontemplate.core.Context;

/**
 * 普通行指令处理器, 如: out, include, break等. (实现类应保证线程安全，及友好的序列化)
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface LineDirectiveHandler extends DirectiveHandler {

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
	public abstract void doRender(Context context, String directiveName,
			Object param) throws Exception;

}
