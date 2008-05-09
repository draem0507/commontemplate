package org.commontemplate.engine.template;

import org.commontemplate.core.Context;
import org.commontemplate.core.RenderingException;

/**
 * 块指令拦截代理
 *
 * @author liangfei0201@163.com
 *
 */
class BlockDirectiveInterceptProxy extends BlockDirectiveProxy {

	private static final long serialVersionUID = 3260814916076018094L;

	private final BlockDirectiveImpl blockDirective;

	BlockDirectiveInterceptProxy(BlockDirectiveImpl blockDirective) {
		super(blockDirective);
		this.blockDirective = blockDirective;
	}

	public void render(Context context) throws RenderingException {
		blockDirective.doRender(context); // 绕过拦截器
	}

}
