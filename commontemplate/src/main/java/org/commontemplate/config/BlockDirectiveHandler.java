package org.commontemplate.config;

import java.util.List;

import org.commontemplate.core.Context;

/**
 * 起始嵌套指令处理器, 如: if, for等. (实现类应保证线程安全，及友好的序列化)
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface BlockDirectiveHandler extends DirectiveHandler {

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
