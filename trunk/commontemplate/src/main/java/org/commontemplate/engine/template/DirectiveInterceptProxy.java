package org.commontemplate.engine.template;

import org.commontemplate.core.Context;
import org.commontemplate.core.RenderingException;

/**
 * 行指令拦截代理
 *
 * @author liangfei0201@163.com
 *
 */
class DirectiveInterceptProxy extends DirectiveProxy {

	private static final long serialVersionUID = 2605840464184974573L;

	private final DirectiveImpl directive;

	DirectiveInterceptProxy(DirectiveImpl directive) {
		super(directive);
		this.directive = directive;
	}

	public void render(Context context) throws RenderingException {
		directive.doRender(context); // 绕过拦截器
	}

}
