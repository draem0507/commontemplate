package org.commontemplate.config;

import java.io.Serializable;

import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;

/**
 * 指令处理器基接口
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class DirectiveHandler implements Serializable {

	/**
	 * 行指令渲染处理
	 *
	 * @param context
	 *            上下文
	 * @param directive
	 *            指令
	 * @throws Exception
	 *             处理过程中的任意异常都应向上抛出, 由引擎统一处理
	 */
	public abstract void doRender(Context context, Directive directive) throws Exception;

}
